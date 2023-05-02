package com.shinoaki.cqhttp.sdk.bot.cqcode;

import java.util.Objects;

/**
 * @author Xun
 * @date 2023/5/2 22:05 星期二
 */
public class TextMessage implements MetaMessage {
    private final String text;

    /**
     * 如果是编码后的数据请在初始化前解码
     *
     * @param text 解码后的数据
     */
    public TextMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public String toCQ() {
        return MetaMessage.escapeEncode(this.text);
    }

    @Override
    public String toString() {
        return MetaMessage.escapeDecode(this.text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextMessage that)) return false;
        return Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText());
    }
}
