package com.beauty.usercenter.client.enums;

/**
 */
public enum OutType {
    A("资讯过来的，activityCenter过来的"),
    S("sns自己的");
    private String desc;

    OutType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getOutId(long id) {
        return name() + id;
    }
}