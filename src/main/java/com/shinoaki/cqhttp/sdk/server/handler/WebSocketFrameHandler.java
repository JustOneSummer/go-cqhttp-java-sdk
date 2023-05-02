package com.shinoaki.cqhttp.sdk.server.handler;

import com.shinoaki.cqhttp.sdk.message.WebSocketMessageServiceInterface;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接管理和消息处理器
 *
 * @author Xun
 * @date 2023/5/2 16:33 星期二
 */
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private final ChannelGroup channelGroup;
    private final WebSocketMessageServiceInterface webSocketMessageServiceInterface;

    private static final Logger log = LoggerFactory.getLogger(WebSocketFrameHandler.class);

    public WebSocketFrameHandler(ChannelGroup channelGroup, WebSocketMessageServiceInterface webSocketMessageServiceInterface) {
        this.channelGroup = channelGroup;
        this.webSocketMessageServiceInterface = webSocketMessageServiceInterface;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        this.channelGroup.add(ctx.channel());
        super.channelRegistered(ctx);
        log.info("{} 建立连接...", ctx.channel().id());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        this.channelGroup.remove(ctx.channel());
        super.channelRegistered(ctx);
        log.info("{} 断开连接...", ctx.channel().id());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame webSocketFrame) throws Exception {
        switch (webSocketFrame) {
            case TextWebSocketFrame msg -> this.webSocketMessageServiceInterface.message(msg);
            case BinaryWebSocketFrame msg -> this.webSocketMessageServiceInterface.message(msg);
            case PingWebSocketFrame msg -> this.webSocketMessageServiceInterface.message(msg);
            case PongWebSocketFrame msg -> this.webSocketMessageServiceInterface.message(msg);
            default -> throw new UnsupportedOperationException("unsupported frame type: " + webSocketFrame.getClass().getName());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("websocket异常!", cause);
    }
}
