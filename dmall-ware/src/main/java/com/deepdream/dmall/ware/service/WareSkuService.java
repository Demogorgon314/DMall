package com.deepdream.dmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepdream.common.utils.PageUtils;
import com.deepdream.dmall.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 14:01:20
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);
}

