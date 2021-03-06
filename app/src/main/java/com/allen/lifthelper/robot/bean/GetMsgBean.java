package com.allen.lifthelper.robot.bean;

/**
 * Created by Allen on 2016/1/21.
 */
public class GetMsgBean {

    /**
     * reason : 成功的返回
     * result : {"code":100000,"text":"您好，我是图灵机器人，有什么可以帮您的吗？"}
     * error_code : 0
     */

    private String reason;
    /**
     * code : 100000
     * text : 您好，我是图灵机器人，有什么可以帮您的吗？
     */

    private ResultEntity result;
    private int error_code;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public ResultEntity getResult() {
        return result;
    }

    public int getError_code() {
        return error_code;
    }

    public static class ResultEntity {
        private int code;
        private String text;

        public void setCode(int code) {
            this.code = code;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getCode() {
            return code;
        }

        public String getText() {
            return text;
        }
    }
}
