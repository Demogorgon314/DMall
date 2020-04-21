package com.deepdream.dmall.coupon.dao;

import com.deepdream.dmall.coupon.entity.CouponSpuRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联
 * 
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 11:12:15
 */
@Mapper
public interface CouponSpuRelationDao extends BaseMapper<CouponSpuRelationEntity> {
	
}
