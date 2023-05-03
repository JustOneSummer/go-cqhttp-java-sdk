package com.shinoaki.cqhttp.sdk.bot.event.message;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 群消息
 *
 * @author Xun
 * @date 2022/12/4 22:00 星期日
 */
public class GroupMessage extends MessageSource {
    /**
     * 群ID
     */
    @JsonAlias(value = {"group_id"})
    private Long groupId;

    /**
     * 匿名信息, 如果不是匿名消息则为 null
     */
    @JsonAlias(value = {"anonymous"})
    private Object anonymous;


    private record Message(long group_id, String message, boolean auto_escape) {
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GroupMessage.class.getSimpleName() + "[", "]")
                .add("groupId=" + groupId)
                .add("anonymous=" + anonymous)
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Object getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Object anonymous) {
        this.anonymous = anonymous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupMessage that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getGroupId(), that.getGroupId()) && Objects.equals(getAnonymous(), that.getAnonymous());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGroupId(), getAnonymous());
    }
}
