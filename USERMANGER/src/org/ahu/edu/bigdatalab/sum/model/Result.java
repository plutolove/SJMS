package org.ahu.edu.bigdatalab.sum.model;

/**
 * 状态码
 * 0.正确
 * 1.错误
 * 2.新增情况
 */
public class Result {

    private String result;
    private String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result(String result, String message) {
        this.result = result;
        this.message = message;
    }
}
