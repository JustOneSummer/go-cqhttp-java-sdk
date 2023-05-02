package com.shinoaki.cqhttp.sdk.bot.cqcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xun
 * @date 2023/5/2 22:17 星期二
 */
public class MessageNode {
    private final List<MetaMessage> messageList = new ArrayList<>();

    /**
     * 获取解码后的raw消息文本
     *
     * @return 消息文本
     */
    public String getRawDecodeData() {
        return toString();
    }

    public static MessageNode analyze(String rawMessage) {
        String[] split = rawMessage.split("\\[");
        MessageNode node = new MessageNode();
        for (String s : split) {
            if (!s.isEmpty()) {
                node.node(s);
            }
        }
        return node;
    }

    public void add(MetaMessage message) {
        messageList.add(message);
    }

    private void node(String data) {
        int indexOf = data.indexOf("]");
        if (indexOf < 0) {
            add(new TextMessage(data));
        } else {
            //找到并且是最后一个
            if (indexOf == data.length() - 1) {
                add(new CQCodeMessage("[" + data));
            } else {
                add(new CQCodeMessage("[" + data.substring(0, indexOf) + "]"));
                add(new TextMessage(MetaMessage.escapeDecode(data.substring(indexOf + 1))));
            }
        }
    }

    public String toCQ() {
        StringBuilder builder = new StringBuilder();
        for (MetaMessage message : messageList) {
            if (message instanceof CQCodeMessage data) {
                builder.append(data.toCQ());
            } else {
                builder.append(((TextMessage) message).toCQ());
            }
        }
        return builder.toString();
    }

    /**
     * 解码后的数据
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (MetaMessage message : messageList) {
            if (message instanceof CQCodeMessage data) {
                builder.append(data);
            } else {
                builder.append(message);
            }
        }
        return builder.toString();
    }
}
