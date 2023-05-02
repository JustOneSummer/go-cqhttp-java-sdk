package com.shinoaki.cqhttp.sdk.websocket.message;

import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author Xun
 * @date 2023/5/2 16:53 星期二
 */
public interface WebSocketMessageServiceInterface {
    void message(TextWebSocketFrame frame);

    void message(BinaryWebSocketFrame frame);

    void message(PingWebSocketFrame frame);

    void message(PongWebSocketFrame frame);
}
