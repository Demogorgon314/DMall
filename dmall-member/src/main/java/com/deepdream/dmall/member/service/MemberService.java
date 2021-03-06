package com.deepdream.dmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepdream.common.utils.PageUtils;
import com.deepdream.dmall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author wangkai
 * @email wangkai744567028@gmail.com
 * @date 2020-04-21 11:23:48
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

