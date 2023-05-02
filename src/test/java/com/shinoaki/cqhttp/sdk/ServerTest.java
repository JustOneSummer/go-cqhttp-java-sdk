package com.shinoaki.cqhttp.sdk;

import com.shinoaki.cqhttp.sdk.message.WebSocketMessageServiceInterface;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author Xun
 * @date 2023/5/2 17:58 星期二
 */
public class ServerTest {
    public static void main(String[] args) throws InterruptedException {
        WebSocketServer server = new WebSocketServer(new WebSocketMessageServiceInterface() {
            @Override
            public void message(TextWebSocketFrame frame) {
                System.out.println("收到消息:" + frame.text());
            }

            @Override
            public void message(BinaryWebSocketFrame frame) {

            }

            @Override
            public void message(PingWebSocketFrame frame) {

            }

            @Override
            public void message(PongWebSocketFrame frame) {

            }
        });
        server.start(8899);
    }
}
