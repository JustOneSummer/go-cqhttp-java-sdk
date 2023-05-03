package com.shinoaki.cqhttp.sdk.websocket.server.handler;

import com.shinoaki.cqhttp.sdk.websocket.message.WebSocketMessageServiceInterface;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.UnexpectedException;

/**
 * @author Xun
 * @date 2023/5/2 17:31 星期二
 */
public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger log = LoggerFactory.getLogger(WebSocketClientHandler.class);
    private final WebSocketClientHandshaker handshaker;
    private final WebSocketMessageServiceInterface webSocketMessageServiceInterface;
    private ChannelPromise handshakeFuture;

    public WebSocketClientHandler(WebSocketClientHandshaker handshaker, WebSocketMessageServiceInterface webSocketMessageServiceInterface) {
        this.handshaker = handshaker;
        this.webSocketMessageServiceInterface = webSocketMessageServiceInterface;
    }

    public ChannelFuture handshakeFuture() {
        return handshakeFuture;
    }

    @Override
    public void handlerAdded(final ChannelHandlerContext ctx) throws Exception {
        handshakeFuture = ctx.newPromise();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        handshaker.handshake(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        final Channel ch = ctx.channel();
        if (!handshaker.isHandshakeComplete()) {
            // web socket client connected
            handshaker.finishHandshake(ch, (FullHttpResponse) msg);
            handshakeFuture.setSuccess();
        } else if (msg instanceof FullHttpResponse response) {
            throw new UnexpectedException("Unexpected FullHttpResponse (getStatus=" + response.status() + ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
        } else {
            WebSocketFrame frame = (WebSocketFrame) msg;
            switch (frame) {
                case TextWebSocketFrame data -> this.webSocketMessageServiceInterface.message(ctx.channel().id(), data);
                case BinaryWebSocketFrame data -> this.webSocketMessageServiceInterface.message(ctx.channel().id(), data);
                case PingWebSocketFrame data -> this.webSocketMessageServiceInterface.message(ctx.channel().id(), data);
                case PongWebSocketFrame data -> this.webSocketMessageServiceInterface.message(ctx.channel().id(), data);
                default -> throw new UnsupportedOperationException("unsupported frame type: " + frame.getClass().getName());
            }
        }
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        if (!handshakeFuture.isDone()) {
            handshakeFuture.setFailure(cause);
        }
        log.error("websocket异常!", cause);
        ctx.close();
    }
}
