package com.beauty.usercenter.client.enums;

/**
 * 活动状态
 */
public enum ActivityType {
	ALL("全部",0),
    NOTSTART("未开始",1),
    FULL("已满员",2),
    START("报名中",3),
    END("已结束",4);
    private String desc;
    private int type;

    ActivityType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static ActivityType getByType(int type) {
        for (ActivityType userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static ActivityType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (ActivityType userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}