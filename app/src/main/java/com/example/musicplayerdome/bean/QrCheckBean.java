package com.example.musicplayerdome.bean;

/**
 * {
 *     "code": 800,
 *     "message": "二维码不存在或已过期",
 *     "cookie": ""
 * }
 */
public class QrCheckBean {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    private int code;
    private String message;
    private String cookie;
}
