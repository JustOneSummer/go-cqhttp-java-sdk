package com.shinoaki.cqhttp.sdk.bot.event;

/**
 * @author Xun
 * @date 2022/12/3 18:09 星期六
 */
public abstract class MetaEventSource extends MetaSource {
    /**
     * <a href="https://docs.go-cqhttp.org/reference/data_struct/#Post_MetaEvent_Type">元数据类型</a>
     */
    protected String metaEventType;
}
