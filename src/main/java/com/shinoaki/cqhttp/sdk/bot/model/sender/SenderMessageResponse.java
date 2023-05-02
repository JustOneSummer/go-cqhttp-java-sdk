package com.shinoaki.cqhttp.sdk.bot.model.sender;

import java.util.StringJoiner;

/**
 * @author Xun
 * @date 2023/5/1 21:44 星期一
 */
public class SenderMessageResponse {
    private String status;
    private int retcode;
    private String msg;
    private String wording;
    private Object data;
    private String echo;

    @Override
    public String toString() {
        return new StringJoiner(", ", SenderMessageResponse.class.getSimpleName() + "[", "]")
                .add("status='" + status + "'")
                .add("retcode=" + retcode)
                .add("msg='" + msg + "'")
                .add("wording='" + wording + "'")
                .add("data=" + data)
                .add("echo='" + echo + "'")
                .toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getEcho() {
        return echo;
    }

    public void setEcho(String echo) {
        this.echo = echo;
    }
}
