package com.shinoaki.cqhttp.sdk.bot.event.request;


import com.shinoaki.wows.bot.wowsbot.bot.event.MetaSource;

/**
 * @author Xun
 * @date 2022/12/3 18:07 星期六
 */
public abstract class RequestSource extends MetaSource {
    /**
     * <a href="https://docs.go-cqhttp.org/reference/data_struct/#Post_Request_Type">请求类型</a>
     */
    protected String requestType;
}
