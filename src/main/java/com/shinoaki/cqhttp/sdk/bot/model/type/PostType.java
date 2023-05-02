package com.shinoaki.cqhttp.sdk.bot.model.type;

/**
 * 一个枚举, 传输使用字符串, 表示上报类型
 *
 * @author Xun
 * @date 2022/12/4 22:03 星期日
 */
public enum PostType {
    /**
     * 消息, 例如, 群聊消息
     */
    MESSAGE,
    /**
     * 请求, 例如, 好友申请
     */
    REQUEST,
    /**
     * 通知, 例如, 群成员增加
     */
    NOTICE,
    /**
     * 元事件, 例如, go-cqhttp 心跳包
     */
    META_EVENT;


    public static PostType query(String s) {
        for (PostType type : PostType.values()) {
            if (type.name().equalsIgnoreCase(s)) {
                return type;
            }
        }
        throw new NullPointerException("PostType 匹配不到对应值={}" + s);
    }
}
