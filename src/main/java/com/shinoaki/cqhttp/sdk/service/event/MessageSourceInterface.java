package com.shinoaki.cqhttp.sdk.service.event;

import com.shinoaki.cqhttp.sdk.bot.event.message.GroupMessage;
import com.shinoaki.cqhttp.sdk.bot.event.message.PrivateMessage;

/**
 * @author Xun
 * @date 2023/5/3 20:48 星期三
 */
public interface MessageSourceInterface {
    void message(GroupMessage message);

    void message(PrivateMessage message);
}
