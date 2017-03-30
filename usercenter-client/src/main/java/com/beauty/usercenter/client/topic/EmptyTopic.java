package com.beauty.usercenter.client.topic;

/**
 * Created by luyichen on 14-7-31.
 *
 */
public enum EmptyTopic {

    CREATE(EmptyTopic.TOPIC,"CREATE","创建");

    public static final String TOPIC = "EMPTY";
    /**
     * 主题
     */
    private String topic;

    /**
     * 标签
     */
    private String tags;

    /**
     * 描述
     */
    private String desc;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private EmptyTopic(String topic, String tags, String desc) {
        this.topic = topic;
        this.tags = tags;
        this.desc = desc;
    }

}
