package com.deepdream.dmall.order.dao;

import com.deepdream.dmall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 13:56:24
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
