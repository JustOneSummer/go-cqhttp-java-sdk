package com.shinoaki.cqhttp.sdk.bot.model.sender;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @author Xun
 * @date 2022/12/4 22:08 星期日
 */
public class GroupMessageSender extends MessageSender {
    /**
     * 群名片／备注
     */
    private String card;
    /**
     * 地区
     */
    private String area;
    /**
     * 成员等级
     */
    private String level;
    /**
     * 角色, owner 或 admin 或 member
     */
    private String role;
    /**
     * 专属头衔
     */
    private String title;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GroupMessageSender.class.getSimpleName() + "[", "]")
                .add("card='" + card + "'")
                .add("area='" + area + "'")
                .add("level='" + level + "'")
                .add("role='" + role + "'")
                .add("title='" + title + "'")
                .add("userId=" + userId)
                .add("nickName='" + nickName + "'")
                .add("sex='" + sex + "'")
                .add("age=" + age)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupMessageSender that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCard(), that.getCard()) && Objects.equals(getArea(), that.getArea()) && Objects.equals(getLevel(), that.getLevel()) && Objects.equals(getRole(), that.getRole()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCard(), getArea(), getLevel(), getRole(), getTitle());
    }
}
