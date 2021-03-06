package com.example.blog.enums;

/**
 * @author example
 */
public enum LogTypeEnum {

    /**
     * 操作
     */
    OPERATION("operation"),

    /**
     * 登录
     */
    LOGIN("login"),

    /**
     * 违规记录
     */
    BAN("ban");

    private String value;

    LogTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
