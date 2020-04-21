package com.deepdream.dmall.product.dao;

import com.deepdream.dmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 10:30:27
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
