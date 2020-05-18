package com.deepdream.dmall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.deepdream.dmall.product.entity.AttrEntity;
import com.deepdream.dmall.product.service.AttrAttrgroupRelationService;
import com.deepdream.dmall.product.service.AttrService;
import com.deepdream.dmall.product.service.CategoryService;
import com.deepdream.dmall.product.vo.AttrGroupRelationVo;
import com.deepdream.dmall.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.deepdream.dmall.product.entity.AttrGroupEntity;
import com.deepdream.dmall.product.service.AttrGroupService;
import com.deepdream.common.utils.PageUtils;
import com.deepdream.common.utils.R;



/**
 * 属性分组
 *
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 10:51:45
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    // /product/attrgroup/{attrgroupId}/attr/relation
    @GetMapping("{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrEntity> entityList = attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data",entityList);
    }
    // /product/attrgroup/{attrgroupId}/noattr/relation
    @GetMapping("{attrgroupId}/noattr/relation")
    public R noattrRelation(@RequestParam Map<String, Object> params,
                            @PathVariable("attrgroupId") Long attrgroupId){
        PageUtils pageUtils = attrService.getNoRelationAttr(params,attrgroupId);
        return R.ok().put("page",pageUtils);
    }
    // /product/attrgroup/attr/relation/delete
    @PostMapping("attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos){
        attrService.deleteRelation(vos);
        return R.ok();
    }
    @PostMapping("attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos){
        attrAttrgroupRelationService.saveBatch(vos);
        return R.ok();
    }
    // /product/attrgroup/{catelogId}/withattr
    @GetMapping("{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId") Long catelogId){
        // 1、 查出当前分类下的所有属性分组
        // 2、查出所有属性分组的所有属性
        List<AttrGroupWithAttrsVo> vos = attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data",vos);

    }
    /**
     * 列表
     */
    @RequestMapping("/list/{catalogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("catalogId") Long catalogId){
        PageUtils page = attrGroupService.queryPage(params,catalogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
		Long catelogId = attrGroup.getCatelogId();
        Long[] path = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogPath(path);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
