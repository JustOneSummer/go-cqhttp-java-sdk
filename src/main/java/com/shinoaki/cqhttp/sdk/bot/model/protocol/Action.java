package com.shinoaki.cqhttp.sdk.bot.model.protocol;

/**
 * @author Xun
 * @date 2023/5/2 21:24 星期二
 */
public enum Action {
    /**
     * 发送群聊消息
     */
    send_group_msg,
    /**
     * 发送私聊消息
     */
    send_private_msg,
    /**
     * 发送消息
     */
    send_msg;
}
