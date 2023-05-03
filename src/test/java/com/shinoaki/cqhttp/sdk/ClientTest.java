package com.shinoaki.cqhttp.sdk;

import com.shinoaki.cqhttp.sdk.websocket.WebSocketClient;
import com.shinoaki.cqhttp.sdk.websocket.WebSocketServer;
import com.shinoaki.cqhttp.sdk.websocket.message.WebSocketMessageServiceInterface;
import io.netty.channel.ChannelId;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.junit.Test;

import javax.net.ssl.SSLException;
import java.net.URI;

/**
 * @author Xun
 * @date 2023/5/2 18:04 星期二
 */
public class ClientTest {
    @Test
    public void test() throws InterruptedException, SSLException {
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
        WebSocketClient client = new WebSocketClient(new WebSocketMessageServiceInterface() {
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
        client.start(URI.create("ws://127.0.0.1:8899"));
        client.sendTextMessage("超级测试2222333");
        Thread.sleep(1000);
        client.closeConnect();
        client.close();
        server.close();
    }
}
