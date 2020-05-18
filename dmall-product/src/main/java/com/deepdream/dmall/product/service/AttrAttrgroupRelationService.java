package com.deepdream.dmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepdream.common.utils.PageUtils;
import com.deepdream.dmall.product.entity.AttrAttrgroupRelationEntity;
import com.deepdream.dmall.product.vo.AttrGroupRelationVo;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 10:30:27
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBatch(List<AttrGroupRelationVo> vos);
}

