package com.deepdream.dmall.coupon.dao;

import com.deepdream.dmall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 11:12:15
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
