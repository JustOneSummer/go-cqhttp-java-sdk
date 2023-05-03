package com.shinoaki.cqhttp.sdk.bot.event.message;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 私聊消息
 *
 * @author Xun
 * @date 2022/12/4 21:58 星期日
 */
public class PrivateMessage extends MessageSource {
    /**
     * 临时会话来源
     */
    @JsonAlias({"temp_source"})
    private int tempSource;

    @Override
    public String toString() {
        return new StringJoiner(", ", PrivateMessage.class.getSimpleName() + "[", "]")
                .add("tempSource=" + tempSource)
                .add("messageType='" + messageType + "'")
                .add("subType='" + subType + "'")
                .add("messageId=" + messageId)
                .add("userId=" + userId)
                .add("rawMessage='" + rawMessage + "'")
                .add("font=" + font)
                .add("sender=" + sender)
                .add("time=" + time)
                .add("selfId=" + selfId)
                .add("postType='" + postType + "'")
                .toString();
    }

    public int getTempSource() {
        return tempSource;
    }

    public void setTempSource(int tempSource) {
        this.tempSource = tempSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrivateMessage that)) return false;
        if (!super.equals(o)) return false;
        return getTempSource() == that.getTempSource();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTempSource());
    }
}
