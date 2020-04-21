package com.deepdream.dmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepdream.common.utils.PageUtils;
import com.deepdream.dmall.product.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 10:30:27
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

