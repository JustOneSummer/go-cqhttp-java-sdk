package com.shinoaki.cqhttp.sdk.bot.model.type;

/**
 * 传输使用字符串, 表示系统通知的子类型
 *
 * @author Xun
 * @date 2022/12/4 22:38 星期日
 */
public enum PostNoticeNotifySubType {
    HONOR,
    POKE,
    LUCKY_KING;

    public static PostNoticeNotifySubType query(String s) {
        for (PostNoticeNotifySubType type : PostNoticeNotifySubType.values()) {
            if (type.name().equalsIgnoreCase(s)) {
                return type;
            }
        }
        throw new NullPointerException("PostRequestType 匹配不到对应值={}" + s);
    }
}
