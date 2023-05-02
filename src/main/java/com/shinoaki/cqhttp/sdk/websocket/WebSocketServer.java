package com.shinoaki.cqhttp.sdk.websocket;

import com.shinoaki.cqhttp.sdk.websocket.message.WebSocketMessageServiceInterface;
import com.shinoaki.cqhttp.sdk.websocket.server.SocketServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelId;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xun
 * @date 2023/5/2 17:05 星期二
 */
public class WebSocketServer {
    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private SocketServerInitializer socketServerInitializer;
    private final EventLoopGroup serverGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    private final WebSocketMessageServiceInterface webSocketMessageServiceInterface;

    public WebSocketServer(WebSocketMessageServiceInterface webSocketMessageServiceInterface) {
        this.webSocketMessageServiceInterface = webSocketMessageServiceInterface;
    }

    public void start(int port, String... path) throws InterruptedException {
        if (socketServerInitializer == null) {
            path = path.length == 0 ? new String[]{"/"} : path;
            this.socketServerInitializer = new SocketServerInitializer(this.webSocketMessageServiceInterface, path);
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(serverGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(this.socketServerInitializer).bind(port).sync();
            log.info("websocket服务器启动! local port={} path={}", port, path);
        }
    }

    public void sendTextMessage(ChannelId id, String msg) {
        this.socketServerInitializer.sendTextMessage(id, msg);
    }

    public void close() {
        serverGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
