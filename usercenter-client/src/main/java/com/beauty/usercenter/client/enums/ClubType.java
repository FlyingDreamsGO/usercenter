package com.beauty.usercenter.client.enums;

/**
 * 活动状态
 */
public enum ClubType {
    SIGN("签到",1),
    SIGNED("已签到",2),
    JOIN("加入",3),
    JOINED("已加入",4);
    private String desc;
    private int type;

    ClubType(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static ClubType getByType(int type) {
        for (ClubType userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static ClubType getByName(String name) {
        if (name == null) {
            return null;
        }
        for (ClubType userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
}