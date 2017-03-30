package com.beauty.usercenter.client.enums;

/**
 */
public enum Gender {
    MALE("男性",1),
    FEMALE("女性",2),
    INVALID_GENDER("未确认",0);
    private String desc;
    private int type;

    Gender(String desc, int type) {
        this.desc = desc;
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public int getType() {
        return type;
    }

    public static Gender getByType(int type) {
        for (Gender userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return null;
    }

    public static Gender getByName(String name) {
        if (name == null) {
            return null;
        }
        for (Gender userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return null;
    }
    public static Gender getByTypeWithDefault(int type) {
        for (Gender userType : values()) {
            if (userType.getType() == type) {
                return userType;
            }
        }
        return MALE;
    }

    public static Gender getByNameWithDefault(String name) {
        if (name == null) {
            return MALE;
        }
        for (Gender userType : values()) {
            if (userType.name().equals(name)) {
                return userType;
            }
        }
        return MALE;
    }
}