package com.shinoaki.cqhttp.sdk.bot.event;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Xun
 * @date 2022/12/3 18:09 星期六
 */
public abstract class MetaEventSource extends MetaSource {
    /**
     * <a href="https://docs.go-cqhttp.org/reference/data_struct/#Post_MetaEvent_Type">元数据类型</a>
     */
    @JsonAlias(value = {"meta_event_type"})
    protected String metaEventType;

    public String getMetaEventType() {
        return metaEventType;
    }

    public void setMetaEventType(String metaEventType) {
        this.metaEventType = metaEventType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MetaEventSource.class.getSimpleName() + "[", "]")
                .add("metaEventType='" + metaEventType + "'")
                .add("time=" + time)
                .add("selfId=" + selfId)
                .add("postType='" + postType + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetaEventSource that)) return false;
        return Objects.equals(getMetaEventType(), that.getMetaEventType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMetaEventType());
    }
}
