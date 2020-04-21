package com.deepdream.dmall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepdream.dmall.product.entity.BrandEntity;
import com.deepdream.dmall.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class DmallProductApplicationTests {
    @Autowired
    BrandService brandService;
    @Test
    void contextLoads() {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setBrandId(1L);
//        brandEntity.setDescript("爱国精品");
//        brandEntity.setName("华为");
//        brandService.save(brandEntity);
//        log.info("保存成功");
//        brandService.updateById(brandEntity);
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
        list.forEach((item)->{
            log.info(item.toString());
        });
    }

}
