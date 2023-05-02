package com.shinoaki.cqhttp.sdk.bot.model.sender;

/**
 * @author Xun
 * @date 2023/5/1 21:41 星期一
 */
public record SenderMessage<T>(String action, T params, String echo) {
}
