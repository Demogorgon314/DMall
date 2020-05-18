package com.deepdream.dmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepdream.common.utils.PageUtils;
import com.deepdream.dmall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 10:30:27
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(BrandEntity brand);
}

