package com.shinoaki.cqhttp.sdk.bot.model.type;

/**
 * 传输使用字符串, 表示请求类型.
 *
 * @author Xun
 * @date 2022/12/4 22:28 星期日
 */
public enum PostRequestType {
    /**
     * 好友请求
     */
    FRIEND,
    /**
     * 群请求
     */
    GROUP;

    public static PostRequestType query(String s) {
        for (PostRequestType type : PostRequestType.values()) {
            if (type.name().equalsIgnoreCase(s)) {
                return type;
            }
        }
        throw new NullPointerException("PostRequestType 匹配不到对应值={}" + s);
    }
}
