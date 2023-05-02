package com.shinoaki.cqhttp.sdk.bot.cqcode;

/**
 * @author Xun
 * @date 2023/5/2 22:04 星期二
 */
public interface MetaMessage {
    public static String escapeDecode(String value) {
        return value.replace("&amp;", "&").replace("&#91;", "[").replace("&#93;", "]").replace("&#44;", ",");
    }

    public static String escapeEncode(String value) {
        return value.replace("&", "&amp;").replace("[", "&#91;").replace("]", "&#93;").replace(",", "&#44;");
    }
}
