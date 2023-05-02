package com.shinoaki.cqhttp.sdk.bot.model.type;

/**
 * 一个枚举, 传输使用 int32, 有以下值:
 *
 * @author Xun
 * @date 2022/12/4 22:22 星期日
 */
public enum PostMessageTempSource {
    TYPE_0(0, "群聊"),
    TYPE_1(1, "QQ咨询"),

    TYPE_2(2, "查找"),
    TYPE_3(3, "QQ电影"),
    TYPE_4(4, "QQ电影"),
    TYPE_5(5, "热聊"),
    TYPE_6(6, "验证消息"),
    TYPE_7(7, "多人聊天"),
    TYPE_8(8, "约会"),
    TYPE_9(9, "通讯录");

    public static PostMessageTempSource query(int s) {
        for (PostMessageTempSource source : PostMessageTempSource.values()) {
            if (source.type() == s) {
                return source;
            }
        }
        throw new NullPointerException("PostMessageTempSource未找到! =" + s);
    }


    private int type;
    private String source;

    public int type() {
        return type;
    }

    public String source() {
        return source;
    }

    PostMessageTempSource(int type, String source) {
        this.type = type;
        this.source = source;
    }
}
