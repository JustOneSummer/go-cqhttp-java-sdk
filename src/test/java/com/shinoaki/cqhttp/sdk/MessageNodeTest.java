package com.shinoaki.cqhttp.sdk;

import com.shinoaki.cqhttp.sdk.bot.cqcode.MessageNode;
import org.junit.Test;

/**
 * @author Xun
 * @date 2023/5/2 23:54 星期二
 */
public class MessageNodeTest {
    @Test
    public void de() {
        String raw = "早上好[CQ:at,qq=1145&#44;14]啊[CQ:at,qq=1145&#44;14][CQ:at,qq=1145&#44;14][CQ:at,qq=1145&#44;14][CQ:at,qq=1145&#44;14]";
        MessageNode analyze = MessageNode.analyze(raw);
        System.out.println(analyze.toCQ());
        System.out.println(analyze.toString());
    }
}
