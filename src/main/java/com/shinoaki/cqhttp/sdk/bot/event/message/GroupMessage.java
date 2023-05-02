package com.shinoaki.cqhttp.sdk.bot.event.message;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.shinoaki.wows.api.utils.DateUtils;
import com.shinoaki.wows.bot.wowsbot.bot.model.sender.SenderMessage;

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


    public void sendMessageCq(String cqMessage) {
        Message m = new Message(groupId, cqMessage, true);
        SenderMessage<Message> message = new SenderMessage<>("send_group_msg"
                , m, String.valueOf(DateUtils.toEpochMilli()));
        this.sendMessage(message);
    }

    public void sendMessageText(String text) {
        Message m = new Message(groupId, text, false);
        SenderMessage<Message> message = new SenderMessage<>("send_group_msg"
                , m, String.valueOf(DateUtils.toEpochMilli()));
        this.sendMessage(message);
    }

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
}
