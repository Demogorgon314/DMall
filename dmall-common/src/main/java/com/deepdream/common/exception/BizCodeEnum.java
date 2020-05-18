package com.deepdream.common.exception;

/**
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为 5 为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：1000010. 通用 001: 系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 * 错误码列表：
 *  10: 通用
 *   001: 参数格式校验
 *  11: 商品
 *  12: 订单
 *  13: 购物车
 *  14: 物流
 * @author wangkai
 */
public enum BizCodeEnum {
    /**
     * 系统未知异常
     */
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    /**
     * 参数格式校验失败
     */
    VALID_EXCEPTION(10001,"参数格式校验失败");

    private final Integer code;
    private final String msg;

    BizCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
