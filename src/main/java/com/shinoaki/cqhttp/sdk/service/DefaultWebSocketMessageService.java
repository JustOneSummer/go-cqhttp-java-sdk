package com.shinoaki.cqhttp.sdk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.shinoaki.cqhttp.sdk.bot.event.message.GroupMessage;
import com.shinoaki.cqhttp.sdk.bot.event.message.PrivateMessage;
import com.shinoaki.cqhttp.sdk.bot.model.type.PostMessageType;
import com.shinoaki.cqhttp.sdk.bot.model.type.PostType;
import com.shinoaki.cqhttp.sdk.service.event.MessageSourceInterface;
import com.shinoaki.cqhttp.sdk.utils.JsonUtils;
import com.shinoaki.cqhttp.sdk.websocket.message.WebSocketMessageServiceInterface;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xun
 * @date 2023/5/3 16:03 星期三
 */
public class DefaultWebSocketMessageService implements WebSocketMessageServiceInterface {
    public static final Logger log = LoggerFactory.getLogger(DefaultWebSocketMessageService.class);
    private final MessageSourceInterface messageSourceInterface;

    public DefaultWebSocketMessageService(MessageSourceInterface messageSourceInterface) {
        this.messageSourceInterface = messageSourceInterface;
    }

    @Override
    public void message(ChannelId channelId, TextWebSocketFrame frame) {
        try {
            JsonUtils jsonUtils = new JsonUtils();
            JsonNode node = jsonUtils.parse(frame.text());
            JsonNode postType = node.get("post_type");
            if (postType == null) {
                JsonNode status = node.get("status");
                if (status != null) {
                    log.info("收到服务器响应 data={}", node);
                }
                return;
            }
            switch (PostType.query(postType.asText())) {
                case MESSAGE -> {
                    switch (PostMessageType.query(node.get("message_type").asText())) {
                        case GROUP -> {
                            GroupMessage groupMessage = jsonUtils.parse(node, GroupMessage.class);
                            groupMessage.setChannelId(channelId);
                            this.messageSourceInterface.message(groupMessage);
                        }
                        case PRIVATE -> {
                            PrivateMessage privateMessage = jsonUtils.parse(node, PrivateMessage.class);
                            privateMessage.setChannelId(channelId);
                            this.messageSourceInterface.message(privateMessage);
                        }
                        default -> log.error("无法识别的消息类型->{}", node.get("message_type").asText());
                    }
                }
                case REQUEST -> {
                }
                case NOTICE -> {
                }
                case META_EVENT -> {
                }
                default -> {
                    log.warn("{}-收到文本消息-未知消息类型:{}", channelId.asLongText(), frame.text());
                }
            }
        } catch (JsonProcessingException e) {
            log.info("websocket 序列化消息异常! data={}", frame.text(), e);
        }
    }

    @Override
    public void message(ChannelId channelId, BinaryWebSocketFrame frame) {
        log.info("{}-收到二进制消息:{}", channelId.asLongText(), Hex.encodeHexString(frame.content().array()));
    }

    @Override
    public void message(ChannelId channelId, PingWebSocketFrame frame) {
        log.info("{}-收到ping消息:{}", channelId.asLongText(), Hex.encodeHexString(frame.content().array()));
    }

    @Override
    public void message(ChannelId channelId, PongWebSocketFrame frame) {
        log.info("{}-收到pong消息:{}", channelId.asLongText(), Hex.encodeHexString(frame.content().array()));
    }
}
