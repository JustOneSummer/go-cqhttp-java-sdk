package com.shinoaki.cqhttp.sdk;

import com.shinoaki.cqhttp.sdk.websocket.WebSocketServer;
import com.shinoaki.cqhttp.sdk.websocket.message.WebSocketMessageServiceInterface;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.junit.Test;

/**
 * @author Xun
 * @date 2023/5/2 17:58 星期二
 */
public class ServerTest {
    @Test
    public void serverTest() throws InterruptedException {
        WebSocketServer server = new WebSocketServer(new WebSocketMessageServiceInterface() {
            @Override
            public void message(ChannelId channelId, TextWebSocketFrame frame) {
                System.out.println("收到消息:" + frame.text());
            }

            @Override
            public void message(ChannelId channelId, BinaryWebSocketFrame frame) {

            }

            @Override
            public void message(ChannelId channelId, PingWebSocketFrame frame) {

            }

            @Override
            public void message(ChannelId channelId, PongWebSocketFrame frame) {

            }
        });
        server.start(8899);
        server.close();
    }
}
