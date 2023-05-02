package com.shinoaki.cqhttp.sdk.bot.model.type;

/**
 * 一个枚举, 传输使用字符串, 表示消息子类型.
 *
 * @author Xun
 * @date 2022/12/4 22:17 星期日
 */
public enum PostMessageSubType {
    /**
     * 好友
     */
    FRIEND,
    /**
     * 群临时会话
     */
    GROUP,
    /**
     * 群中自身发送
     */
    GROUP_SELF,
    /**
     * 正常群聊消息
     */
    NORMAL,
    /**
     * 匿名消息
     */
    ANONYMOUS,
    /**
     * 系统提示 ( 如「管理员已禁止群内匿名聊天」 )
     */
    NOTICE;
}
