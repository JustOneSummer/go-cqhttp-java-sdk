package com.shinoaki.cqhttp.sdk.bot.model.sender;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 消息发送者信息
 *
 * @author Xun
 * @date 2022/12/4 22:06 星期日
 */
public abstract class MessageSender {
    protected long userId;
    protected String nickName;
    protected String sex;
    protected int age;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MessageSender.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("nickName='" + nickName + "'")
                .add("sex='" + sex + "'")
                .add("age=" + age)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageSender that)) return false;
        return getUserId() == that.getUserId() && getAge() == that.getAge() && Objects.equals(getNickName(), that.getNickName()) && Objects.equals(getSex(), that.getSex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getNickName(), getSex(), getAge());
    }
}
