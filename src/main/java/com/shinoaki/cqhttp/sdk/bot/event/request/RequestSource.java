package com.shinoaki.cqhttp.sdk.bot.event.request;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.shinoaki.cqhttp.sdk.bot.event.MetaSource;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Xun
 * @date 2022/12/3 18:07 星期六
 */
public abstract class RequestSource extends MetaSource {
    /**
     * <a href="https://docs.go-cqhttp.org/reference/data_struct/#Post_Request_Type">请求类型</a>
     */
    @JsonAlias({"request_type"})
    protected String requestType;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RequestSource.class.getSimpleName() + "[", "]")
                .add("requestType='" + requestType + "'")
                .add("time=" + time)
                .add("selfId=" + selfId)
                .add("postType='" + postType + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestSource that)) return false;
        return Objects.equals(getRequestType(), that.getRequestType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRequestType());
    }
}
