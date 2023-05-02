package com.shinoaki.cqhttp.sdk.bot.model.protocol;

/**
 * response
 * @author Xun
 * @date 2023/5/1 21:44 星期一
 */
public record ResponseData(String status, int retcode, String msg, String wording, Object data, String echo) {
}
