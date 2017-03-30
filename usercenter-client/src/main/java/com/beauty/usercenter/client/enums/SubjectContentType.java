package com.beauty.usercenter.client.enums;

/**
 * 动态或者直播
 * @autor sunji180 on 2015/8/28.
 */
public enum SubjectContentType {
    DYNAMIC("动态",1),
    LIVE("直播",2);
    private String desc;
    private int type;

    SubjectContentType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static SubjectContentType getByType(int type) {
        for (SubjectContentType subjectAsyncPushType : values()) {
            if (subjectAsyncPushType.getType() == type) {
                return subjectAsyncPushType;
            }
        }
        return null;
    }

    public static SubjectContentType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (SubjectContentType subjectAsyncPushType : values()) {
            if (subjectAsyncPushType.name().equals(name)) {
                return subjectAsyncPushType;
            }
        }
        return null;
    }
}
