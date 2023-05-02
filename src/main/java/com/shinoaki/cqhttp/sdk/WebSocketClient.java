package com.shinoaki.cqhttp.sdk;

import com.shinoaki.cqhttp.sdk.message.WebSocketMessageServiceInterface;
import com.shinoaki.cqhttp.sdk.server.SocketClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import java.net.URI;

/**
 * @author Xun
 * @date 2023/5/2 17:20 星期二
 */
public class WebSocketClient {
    private static final Logger log = LoggerFactory.getLogger(WebSocketClient.class);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private SocketClientInitializer socketClientInitializer;
    private Channel channel;
    private final WebSocketMessageServiceInterface webSocketMessageServiceInterface;

    public WebSocketClient(WebSocketMessageServiceInterface webSocketMessageServiceInterface) {
        this.webSocketMessageServiceInterface = webSocketMessageServiceInterface;
    }

    public void start(URI uri) throws InterruptedException, SSLException {
        if (this.socketClientInitializer == null) {
            // 初始化 SSL 上下文
            SslContext sslCtx = null;
            if ("https".equalsIgnoreCase(uri.getScheme())) {
                sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
            }
            this.socketClientInitializer = new SocketClientInitializer(sslCtx, uri, this.webSocketMessageServiceInterface);
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(this.socketClientInitializer);
            this.channel = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();
            this.socketClientInitializer.handshakeFuture().sync();
        }
    }

    public void sendTextMessage(String message) {
        this.channel.writeAndFlush(new TextWebSocketFrame(message));
    }

    public void closeConnect() throws InterruptedException {
        this.channel.writeAndFlush(new CloseWebSocketFrame());
        this.channel.closeFuture().sync();
    }

    public void close() {
        this.workerGroup.shutdownGracefully();
    }
}
