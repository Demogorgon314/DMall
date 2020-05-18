package com.deepdream.common.constant;

public class WareConstant {
    public enum PurchaseStatusEnum{
        CREATED(0,"新建"),
        ASSIGNED(1,"已分配"),
        RECEIVED(2,"已领取"),
        FINISH(3,"已完成"),
        ERROR(4,"有异常")



        ;
        private int code;
        private String msg;

        PurchaseStatusEnum(int code, String msg){
            this.code = code;
            this.msg = msg;
        }
        public int getCode(){
            return this.code;
        }

    }

    public enum PurchaseDetailEnum{
        CREATED(0,"新建"),
        ASSIGNED(1,"已分配"),
        BUYING(2,"正在采购"),
        FINISH(3,"已完成"),
        ERROR(4,"采购失败")



        ;
        private int code;
        private String msg;

        PurchaseDetailEnum(int code, String msg){
            this.code = code;
            this.msg = msg;
        }
        public int getCode(){
            return this.code;
        }

    }
}
