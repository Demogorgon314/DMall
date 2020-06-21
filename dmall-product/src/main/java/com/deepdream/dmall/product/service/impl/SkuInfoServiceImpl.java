package com.deepdream.dmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepdream.common.utils.PageUtils;
import com.deepdream.common.utils.Query;

import com.deepdream.dmall.product.dao.SkuInfoDao;
import com.deepdream.dmall.product.entity.SkuInfoEntity;
import com.deepdream.dmall.product.service.SkuInfoService;
import org.springframework.util.StringUtils;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuInfo(SkuInfoEntity skuInfoEntity) {
        this.baseMapper.insert(skuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        LambdaQueryWrapper<SkuInfoEntity> queryWrapper = Wrappers.lambdaQuery();
        params.get("key");
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((w)->{
                w.eq(SkuInfoEntity::getSkuId,key).or().likeRight(SkuInfoEntity::getSkuName,key);
            });
        }
        String catelogId = (String) params.get("catelogId");
        if(!StringUtils.isEmpty(catelogId) && !"0".equals(catelogId)){
            queryWrapper.eq(SkuInfoEntity::getCatalogId,catelogId);
        }
        String brandId = (String) params.get("brandId");
        if(!StringUtils.isEmpty(brandId) && !"0".equals(brandId)){
            queryWrapper.eq(SkuInfoEntity::getBrandId,brandId);
        }
        String min = (String) params.get("min");
        if(!StringUtils.isEmpty(min)){
            queryWrapper.ge(SkuInfoEntity::getPrice,min);
        }
        String max = (String) params.get("max");
        if(!StringUtils.isEmpty(max)){
            BigDecimal bigDecimal = new BigDecimal(max);
            if(bigDecimal.compareTo(new BigDecimal(0)) > 0){
                queryWrapper.le(SkuInfoEntity::getPrice,max);
            }

        }
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuInfoEntity> getSkuBySpuId(Long spuId) {
        return this.list(Wrappers
                .<SkuInfoEntity>lambdaQuery()
                .eq(SkuInfoEntity::getSkuId,spuId));
    }

}