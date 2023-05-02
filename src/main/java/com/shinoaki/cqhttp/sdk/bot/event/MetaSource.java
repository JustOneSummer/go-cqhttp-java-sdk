package com.shinoaki.cqhttp.sdk.bot.event;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.netty.channel.ChannelId;

/**
 * @author Xun
 * @date 2022/12/3 17:56 星期六
 */
public abstract class MetaSource {
    private ChannelId channelId;
    /**
     * 事件发生的时间戳
     */
    @JsonAlias(value = {"time"})
    protected long time;
    /**
     * 收到事件的机器人的 QQ 号
     */
    @JsonAlias(value = {"self_id"})
    protected long selfId;
    /**
     * 事件上报类型
     * message, request, notice,meta_event
     */
    @JsonAlias(value = {"post_type"})
    protected String postType;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getSelfId() {
        return selfId;
    }

    public void setSelfId(long selfId) {
        this.selfId = selfId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public ChannelId getChannelId() {
        return channelId;
    }

    public void setChannelId(ChannelId channelId) {
        this.channelId = channelId;
    }
}
