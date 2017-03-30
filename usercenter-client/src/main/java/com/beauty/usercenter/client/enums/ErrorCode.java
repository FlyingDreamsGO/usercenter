package com.beauty.usercenter.client.enums;

/**
 * Created by 海浩 on 2015/3/29.
 *
 */
public enum ErrorCode {
    SUBJECT_NOT_FOUND("subject不存在"),
    TOPIC_NOT_FOUND("topic不存在"),
    TOPIC_NOT_MATCH("topic格式不符合##"),
    USER_NOT_FOUND("用户不存在"),
    TEXT_AND_PIC_IS_EMPTY("发帖内容为空"),
    PARAM_ERROR("参数错误"),
    SYSTEM_ERROR("系统错误"),
    READ_DB_ERROR("读取数据库错误"),
    WRITE_DB_ERROR("写入数据库错误"),
    UPDATE_USER_EXT_DATA("更新用户扩展数据出错");
    private String desc;

    ErrorCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
