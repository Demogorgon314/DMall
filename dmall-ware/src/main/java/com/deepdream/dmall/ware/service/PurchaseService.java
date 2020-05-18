package com.deepdream.dmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepdream.common.utils.PageUtils;
import com.deepdream.dmall.ware.entity.PurchaseEntity;
import com.deepdream.dmall.ware.vo.MergeVo;
import com.deepdream.dmall.ware.vo.PurchaseDoneVo;

import java.util.Map;

/**
 * 采购信息
 *
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 14:01:20
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceivedPurchase(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);

    void done(PurchaseDoneVo doneVo);
}

