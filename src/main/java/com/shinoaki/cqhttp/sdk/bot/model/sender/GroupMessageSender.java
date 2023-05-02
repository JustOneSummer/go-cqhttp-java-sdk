package com.shinoaki.cqhttp.sdk.bot.model.sender;

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
}
