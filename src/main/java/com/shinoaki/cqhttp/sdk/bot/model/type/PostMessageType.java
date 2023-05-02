package com.shinoaki.cqhttp.sdk.bot.model.type;

/**
 * 传输使用字符串, 表示消息类型.
 * @author Xun
 * @date 2022/12/4 22:10 星期日
 */
public enum PostMessageType {
    /**
     * 私聊消息
     */
    PRIVATE,
    /**
     * 群消息
     */
    GROUP;

    public static PostMessageType query(String s) {
        for (PostMessageType type : PostMessageType.values()) {
            if (type.name().equalsIgnoreCase(s)) {
                return type;
            }
        }
        throw new NullPointerException("PostMessageType 查询为空! =" + s);
    }
}
