package com.deepdream.dmall.product.vo;

import lombok.Data;

@Data
public class AttrRespVo extends AttrVo{
    /**
     * 所属分类名字
     */
    private String catelogName;
    /**
     * 所属分组名字
     */
    private String groupName;

    /**
     * 分类完整路径
     */
    private Long[] catelogPath;
}
