package com.shinoaki.cqhttp.sdk.bot.cqcode.type;

/**
 * @author Xun
 * @date 2023/5/2 21:54 星期二
 */
public enum Type {
    /**
     * QQ表情
     * https://docs.go-cqhttp.org/cqcode/#qq-%E8%A1%A8%E6%83%85
     */
    face,
    /**
     * 语音
     * https://docs.go-cqhttp.org/cqcode/#%E8%AF%AD%E9%9F%B3
     */
    record,
    /**
     * 短视频
     * https://docs.go-cqhttp.org/cqcode/#%E7%9F%AD%E8%A7%86%E9%A2%91
     */
    video,
    /**
     * at
     * https://docs.go-cqhttp.org/cqcode/#%E6%9F%90%E4%BA%BA
     */
    at,


    /**
     * 窗口抖动或者是戳一戳
     * https://docs.go-cqhttp.org/cqcode/#%E7%AA%97%E5%8F%A3%E6%8A%96%E5%8A%A8-%E6%88%B3%E4%B8%80%E6%88%B3
     */
    shake,
    /**
     * 链接分享
     * https://docs.go-cqhttp.org/cqcode/#%E9%93%BE%E6%8E%A5%E5%88%86%E4%BA%AB
     */
    share,


    /**
     * 图片
     * https://docs.go-cqhttp.org/cqcode/#%E5%9B%BE%E7%89%87
     */
    image,
    /**
     * 回复
     * https://docs.go-cqhttp.org/cqcode/#%E5%9B%9E%E5%A4%8D
     */
    reply,
    /**
     * json消息
     * https://docs.go-cqhttp.org/cqcode/#xml-%E6%B6%88%E6%81%AF
     */
    json,
    /**
     * 未识别的
     */
    NONE;

    public static Type search(String text) {
        for (Type type : Type.values()) {
            if (text.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        return Type.NONE;
    }
}
