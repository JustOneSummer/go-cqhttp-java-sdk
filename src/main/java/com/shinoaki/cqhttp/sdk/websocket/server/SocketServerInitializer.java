package com.shinoaki.cqhttp.sdk.websocket.server;

import com.shinoaki.cqhttp.sdk.websocket.message.WebSocketMessageServiceInterface;
import com.shinoaki.cqhttp.sdk.websocket.server.handler.WebSocketFrameHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolConfig;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 初始化
 *
 * @author Xun
 * @date 2023/5/2 16:25 星期二
 */
public class SocketServerInitializer extends ChannelInitializer<SocketChannel> {

    private static final int MAX_CONTENT_LENGTH = 65536;
    private final SslContext sslContext;
    private final int maxContentLength;
    private final String[] path;
    private final ChannelGroup channelGroup;
    private final WebSocketMessageServiceInterface webSocketMessageServiceInterface;


    public SocketServerInitializer(WebSocketMessageServiceInterface webSocketMessageServiceInterface, String... path) {
        this.webSocketMessageServiceInterface = webSocketMessageServiceInterface;
        this.sslContext = null;
        this.maxContentLength = MAX_CONTENT_LENGTH;
        this.path = path;
        this.channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    public SocketServerInitializer(WebSocketMessageServiceInterface webSocketMessageServiceInterface, SslContext sslContext, String... path) {
        this.webSocketMessageServiceInterface = webSocketMessageServiceInterface;
        this.sslContext = sslContext;
        this.maxContentLength = MAX_CONTENT_LENGTH;
        this.path = path;
        this.channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    public SocketServerInitializer(WebSocketMessageServiceInterface webSocketMessageServiceInterface, SslContext sslContext, int maxContentLength,
                                   String[] path) {
        this.webSocketMessageServiceInterface = webSocketMessageServiceInterface;
        this.sslContext = sslContext;
        this.maxContentLength = maxContentLength;
        this.path = path;
        this.channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        if (sslContext != null) {
            pipeline.addLast(sslContext.newHandler(socketChannel.alloc()));
        }
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(maxContentLength));
        for (String p : path) {
            pipeline.addLast(new WebSocketServerProtocolHandler(WebSocketServerProtocolConfig.newBuilder().websocketPath(p).build()));
        }
        pipeline.addLast(new WebSocketFrameHandler(channelGroup, webSocketMessageServiceInterface));
    }

    /**
     * 发送文本消息
     *
     * @param id      通道id
     * @param message 消息文本
     * @return true表示找到id并且发送了, 否则表示id找不到
     */
    public boolean sendTextMessage(ChannelId id, String message) {
        Channel channel = this.channelGroup.find(id);
        if (channel != null) {
            channel.writeAndFlush(new TextWebSocketFrame(message));
            return true;
        }
        return false;
    }

    public ChannelGroup getChannelGroup() {
        return channelGroup;
    }
}
