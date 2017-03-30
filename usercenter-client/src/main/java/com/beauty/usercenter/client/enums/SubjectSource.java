package com.beauty.usercenter.client.enums;

/**
 */
public enum SubjectSource {
    SNS("健康圈",0),
    WALK("走一走",1),
    PLAN("健康计划",2),
    ACT("健康资讯",3);
    private String desc;
    private int type;

    SubjectSource(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static SubjectSource getByType(int type) {
        for (SubjectSource userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static SubjectSource getByName(String name) {
        if (name == null) {
            return null;
        }
        for (SubjectSource userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
    public static SubjectSource getByTypeWithDefault(int type) {
        for (SubjectSource userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return SNS;
    }

    public static SubjectSource getByNameWithDefault(String name) {
        if (name == null) {
            return SNS;
        }
        for (SubjectSource userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return SNS;
    }
}