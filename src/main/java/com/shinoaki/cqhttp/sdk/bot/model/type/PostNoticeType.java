package com.shinoaki.cqhttp.sdk.bot.model.type;

/**
 * 传输使用字符串, 表示通知类型.
 *
 * @author Xun
 * @date 2022/12/4 22:33 星期日
 */
public enum PostNoticeType {
    /**
     * 群文件上传
     */
    GROUP_UPLOAD,
    /**
     * 群管理员变更
     */
    GROUP_ADMIN,
    /**
     * 群成员减少
     */
    GROUP_DECREASE,
    /**
     * 群成员增加
     */
    GROUP_INCREASE,
    /**
     * 群成员禁言
     */
    GROUP_BAN,
    /**
     * 好友添加
     */
    FRIEND_ADD,
    /**
     * 群消息撤回
     */
    GROUP_RECALL,
    /**
     * 好友消息撤回
     */
    FRIEND_RECALL,
    /**
     * 群名片变更
     */
    GROUP_CARD,
    /**
     * 离线文件上传
     */
    OFFLINE_FILE,
    /**
     * 客户端状态变更
     */
    CLIENT_STATUS,
    /**
     * 精华消息
     */
    ESSENCE,
    /**
     * 系统通知
     */
    NOTIFY;

    public static PostNoticeType query(String s) {
        for (PostNoticeType type : PostNoticeType.values()) {
            if (type.name().equalsIgnoreCase(s)) {
                return type;
            }
        }
        throw new NullPointerException("PostRequestType 匹配不到对应值={}" + s);
    }
}
