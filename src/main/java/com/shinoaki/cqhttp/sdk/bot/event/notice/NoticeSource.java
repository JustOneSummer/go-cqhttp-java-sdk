package com.shinoaki.cqhttp.sdk.bot.event.notice;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.shinoaki.cqhttp.sdk.bot.event.MetaSource;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 通知数据
 *
 * @author Xun
 * @date 2022/12/3 18:08 星期六
 */
public abstract class NoticeSource extends MetaSource {
    /**
     * <a href="https://docs.go-cqhttp.org/reference/data_struct/#Post_Notice_Type">通知类型</a>
     */
    @JsonAlias({"notice_type"})
    protected String noticeType;

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NoticeSource.class.getSimpleName() + "[", "]")
                .add("noticeType='" + noticeType + "'")
                .add("time=" + time)
                .add("selfId=" + selfId)
                .add("postType='" + postType + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoticeSource that)) return false;
        return Objects.equals(getNoticeType(), that.getNoticeType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoticeType());
    }
}
