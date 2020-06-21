package com.deepdream.dmall.product.service.impl;

import com.deepdream.common.to.SkuReductionTo;
import com.deepdream.common.to.SpuBoundTo;
import com.deepdream.common.to.es.SkuEsModel;
import com.deepdream.common.utils.R;
import com.deepdream.dmall.product.entity.*;
import com.deepdream.dmall.product.feign.CouponFeignService;
import com.deepdream.dmall.product.service.*;
import com.deepdream.dmall.product.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepdream.common.utils.PageUtils;
import com.deepdream.common.utils.Query;

import com.deepdream.dmall.product.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private SpuImagesService spuImagesService;

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private CouponFeignService couponFeignService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        // 1、保存spu基本信息 pms_spu_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(vo,spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        this.saveBaseSpuInfo(spuInfoEntity);

        // 2、保存 Spu 的描述图片 pms_spu_info_desc
        List<String> descript = vo.getDecript();
        SpuInfoDescEntity descEntity = new SpuInfoDescEntity();
        descEntity.setSpuId(spuInfoEntity.getId());
        descEntity.setDecript(String.join(",",descript));
        spuInfoDescService.saveSpuInfoDesc(descEntity);

        // 3、保存 Spu 的图片集 pms_spu_images
        List<String> images = vo.getImages();
        spuImagesService.saveImages(spuInfoEntity.getId(),images);

        // 4、保存 Spu 的规格参数：pms_product_attr_value
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValueEntity> productAttrValueEntities = baseAttrs.stream().map(attr -> {
            ProductAttrValueEntity valueEntity = new ProductAttrValueEntity();
            valueEntity.setAttrId(attr.getAttrId());
            AttrEntity id = attrService.getById(attr.getAttrId());
            valueEntity.setAttrName(id.getAttrName());
            valueEntity.setAttrValue(attr.getAttrValues());
            valueEntity.setQuickShow(attr.getShowDesc());
            valueEntity.setSpuId(spuInfoEntity.getId());
            return valueEntity;
        }).collect(Collectors.toList());
        productAttrValueService.saveProductAttr(productAttrValueEntities);

        // 5、保存 Spu 的积分信息： dmall_sms.sms_spu_bonuds
        Bounds bounds = vo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds,spuBoundTo);
        spuBoundTo.setSpuId(spuInfoEntity.getId());
        R r1 = couponFeignService.saveSpuBounds(spuBoundTo);
        if(r1.getCode() != 0){
            log.error("远程保存 Spu 优惠信息失败");
        }

        // 6、保存当前 Spu 对应的所有 sku 信息
        // 6、1）、sku 基本信息 pms_sku_info
       List<Skus> skus = vo.getSkus();
       if(skus != null && skus.size() > 0){
           skus.forEach(item -> {
               String defaultImg = "";
               for (Images image:
                    item.getImages()) {
                   if(image.getDefaultImg() == 1){
                       defaultImg = image.getImgUrl();
                   }
               }
               SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
               BeanUtils.copyProperties(item,skuInfoEntity);
               skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
               skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
               skuInfoEntity.setSaleCount(0L);
               skuInfoEntity.setSpuId(spuInfoEntity.getId());
               skuInfoEntity.setSkuDefaultImg(defaultImg);
               skuInfoService.saveSkuInfo(skuInfoEntity);

               Long skuId = skuInfoEntity.getSkuId();

               List<SkuImagesEntity> skuImagesEntities = item.getImages().stream().map(img -> {
                   SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                   skuImagesEntity.setSkuId(skuId);
                   skuImagesEntity.setImgUrl(img.getImgUrl());
                   skuImagesEntity.setDefaultImg(img.getDefaultImg());
                   return skuImagesEntity;
               }).filter(entity-> !StringUtils.isEmpty(entity.getImgUrl())).collect(Collectors.toList());

               // 6、2）、sku 的图片信息 pms_sku_images
               skuImagesService.saveBatch(skuImagesEntities);

               // 6、3）、sku 的销售属性值 pms_sku_sale_attr_value
               List<Attr> attr = item.getAttr();
               List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                   SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                   BeanUtils.copyProperties(a, skuSaleAttrValueEntity);
                   skuSaleAttrValueEntity.setSkuId(skuId);
                   return skuSaleAttrValueEntity;
               }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

               // 6、4）、sku 的优惠、满减信息
               //      dmall_sms.sms_sku_ladder,
               //      dmall_sms.sms_sku_full_reduction,
               //      dmall_sms.sms_member_price
               SkuReductionTo skuReductionTo = new SkuReductionTo();
               BeanUtils.copyProperties(item,skuReductionTo);
               skuReductionTo.setSkuId(skuId);
               if(skuReductionTo.getFullCount() > 0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal(0)) > 0){
                   R r = couponFeignService.saveSkuReduction(skuReductionTo);
                   if(r.getCode() != 0){
                       log.error("远程保存 Sku 优惠信息失败");
                   }
               }


           });
       }





    }

    /**
     * 保存spu基本信息 pms_spu_info
     */
    @Override
    public void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity) {
        this.baseMapper.insert(spuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((w)->{
               w.eq("id",key).or().likeRight("spu_name",key);
            });
        }
        String status = (String) params.get("status");
        if(!StringUtils.isEmpty(status)){
            queryWrapper.eq("publish_status",status);
        }
        String brandId = (String) params.get("brandId");
        if(!StringUtils.isEmpty(brandId) && !"0".equals(brandId)){
            queryWrapper.eq("brand_id",brandId);
        }
        String catalogId = (String) params.get("catelogId");
        if(!StringUtils.isEmpty(catalogId) && !"0".equals(catalogId)){
            queryWrapper.eq("catalog_id",catalogId);
        }

        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {
        List<SkuEsModel> upProducts = new ArrayList<>();
        // 1.组装需要的数据
        SkuEsModel esModel = new SkuEsModel();
        List<SkuInfoEntity> skus = skuInfoService.getSkuBySpuId(spuId);
        // 2.封装需要的数据
        List<SkuEsModel> collect = skus.stream().map(sku -> {
            SkuEsModel skuEsModel = new SkuEsModel();
            BeanUtils.copyProperties(sku,skuEsModel);
            skuEsModel.setSkuPrice(sku.getPrice());
            skuEsModel.setSkuImg(sku.getSkuDefaultImg());
            // TODO 1. 发送远程调用，查询是否有库存 hasStock

            // TODO 2. 热度评分 hotScore 默认 0
            // TODO 3. 查询 brandName，brandImg
            BrandEntity byId = brandService.getById(sku.getBrandId());

            skuEsModel.setBrandName(byId.getName());
            //
            // catelogName
            // Attrs


            return skuEsModel;
        }).collect(Collectors.toList());
    }


}