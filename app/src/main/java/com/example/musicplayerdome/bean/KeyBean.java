package com.example.musicplayerdome.bean;

/**
 * {
 *     "data": {
 *         "code": 200,
 *         "unikey": "7ec404e7-7398-4511-beaa-aebb800e9871"
 *     },
 *     "code": 200
 * }
 */
public class KeyBean {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    private int code;
    private DataBean data;
    public class DataBean {
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getUnikey() {
            return unikey;
        }

        public void setUnikey(String unikey) {
            this.unikey = unikey;
        }

        private int code;
        private String unikey;
    }
}
