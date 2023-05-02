package com.shinoaki.cqhttp.sdk.bot.event.message;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.shinoaki.cqhttp.sdk.bot.event.MetaSource;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 消息数据
 *
 * @author Xun
 * @date 2022/12/3 17:59 星期六
 */
public abstract class MessageSource extends MetaSource {
    /**
     * <a href="https://docs.go-cqhttp.org/reference/data_struct/#Post_Message_Type">消息类型</a>
     */
    @JsonAlias(value = {"message_type"})
    protected String messageType;
    /**
     * <a href="https://docs.go-cqhttp.org/reference/data_struct/#Post_Message_SubType">子消息类型</a>
     */
    @JsonAlias(value = {"sub_type"})
    protected String subType;
    /**
     * 消息ID
     */
    @JsonAlias(value = {"message_id"})
    protected Long messageId;
    /**
     * 发送者ID
     */
    @JsonAlias(value = {"user_id"})
    protected Long userId;
    /**
     * 类型 message 一个消息
     */
    @JsonAlias(value = {"message"})
    private Object message;
    /**
     * <a href="https://docs.go-cqhttp.org/cqcode">CQ码格式消息</a>
     */
    @JsonAlias(value = {"raw_message"})
    protected String rawMessage;

    /**
     * 字体
     */
    @JsonAlias(value = {"font"})
    protected int font;

    /**
     * <a href="https://docs.go-cqhttp.org/reference/data_struct/#Post_Message_MessageSender">发送者信息</a>
     */
    @JsonAlias(value = {"sender"})
    protected Object sender;

    @Override
    public String toString() {
        return new StringJoiner(", ", MessageSource.class.getSimpleName() + "[", "]")
                .add("messageType='" + messageType + "'")
                .add("subType='" + subType + "'")
                .add("messageId=" + messageId)
                .add("userId=" + userId)
                .add("message=" + message)
                .add("rawMessage='" + rawMessage + "'")
                .add("font=" + font)
                .add("sender=" + sender)
                .add("time=" + time)
                .add("selfId=" + selfId)
                .add("postType='" + postType + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageSource that)) return false;
        return getFont() == that.getFont() && Objects.equals(getMessageType(), that.getMessageType()) && Objects.equals(getSubType(), that.getSubType()) && Objects.equals(getMessageId(), that.getMessageId()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getRawMessage(), that.getRawMessage()) && Objects.equals(getSender(), that.getSender());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessageType(), getSubType(), getMessageId(), getUserId(), getMessage(), getRawMessage(), getFont(), getSender());
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    public int getFont() {
        return font;
    }

    public void setFont(int font) {
        this.font = font;
    }

    public Object getSender() {
        return sender;
    }

    public void setSender(Object sender) {
        this.sender = sender;
    }
}
