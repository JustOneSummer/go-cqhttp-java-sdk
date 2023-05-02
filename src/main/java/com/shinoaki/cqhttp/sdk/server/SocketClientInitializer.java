package com.shinoaki.cqhttp.sdk.server;

import com.shinoaki.cqhttp.sdk.message.WebSocketMessageServiceInterface;
import com.shinoaki.cqhttp.sdk.server.handler.WebSocketClientHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.ssl.SslContext;

import java.net.URI;

/**
 * @author Xun
 * @date 2023/5/2 17:24 星期二
 */
public class SocketClientInitializer extends ChannelInitializer<SocketChannel> {
    private static final int MAX_CONTENT_LENGTH = 65536;
    private final SslContext sslContext;
    private final int maxContentLength;
    private final URI uri;
    private final WebSocketClientHandshaker handshaker;
    private final WebSocketMessageServiceInterface webSocketMessageServiceInterface;
    private final WebSocketClientHandler webSocketClientHandler;

    public SocketClientInitializer(SslContext sslContext, URI uri, WebSocketMessageServiceInterface webSocketMessageServiceInterface) {
        this.sslContext = sslContext;
        this.uri = uri;
        this.maxContentLength = MAX_CONTENT_LENGTH;
        // 添加 WebSocket 协议支持
        this.handshaker = WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13, null, true, new DefaultHttpHeaders());
        this.webSocketMessageServiceInterface = webSocketMessageServiceInterface;
        this.webSocketClientHandler = new WebSocketClientHandler(this.handshaker, this.webSocketMessageServiceInterface);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (sslContext != null) {
            // 添加 SSL 处理器
            pipeline.addLast(sslContext.newHandler(ch.alloc(), uri.getHost(), uri.getPort()));
        }
        // 添加 HTTP 协议解码器
        pipeline.addLast(new HttpResponseDecoder());
        // 添加 HTTP 消息聚合器
        pipeline.addLast(new HttpObjectAggregator(this.maxContentLength));
        // 添加 HTTP 请求编码器
        pipeline.addLast(new HttpRequestEncoder());
        //处理通道
        pipeline.addLast();
    }

    public ChannelFuture handshakeFuture() {
        return this.webSocketClientHandler.handshakeFuture();
    }
}
