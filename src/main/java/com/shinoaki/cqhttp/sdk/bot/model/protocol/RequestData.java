package com.shinoaki.cqhttp.sdk.bot.model.protocol;

/**
 * @author Xun
 * @date 2023/5/2 21:24 星期二
 */
public record RequestData<T>(Action action, T params, String echo) {
}
