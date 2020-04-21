package com.deepdream.dmall.member.feign;

import com.deepdream.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "dmall-coupon",path = "/coupon/coupon")
public interface CouponFeignService {
    @RequestMapping("/test")
    R test();
}
