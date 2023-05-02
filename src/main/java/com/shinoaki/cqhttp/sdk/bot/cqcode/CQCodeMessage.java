package com.shinoaki.cqhttp.sdk.bot.cqcode;

import com.shinoaki.cqhttp.sdk.bot.cqcode.type.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Xun
 * @date 2023/5/2 21:41 星期二
 */
public class CQCodeMessage implements MetaMessage {
    private final Type type;
    private final Map<String, String> params = new HashMap<>();

    /**
     * 对数据进行解码
     *
     * @param data 编码后的数据
     */
    public CQCodeMessage(String data) {
        String[] split = data.replace("[CQ:", "").replace("]", "").split(",");
        this.type = Type.search(split[0]);
        for (int i = 1; i < split.length; i++) {
            String[] temp = split[i].split("=");
            params.put(temp[0], MetaMessage.escapeDecode(temp[1]));
        }
    }

    public CQCodeMessage(Type type) {
        this.type = type;
    }


    public String toCQ() {
        StringBuilder builder = new StringBuilder();
        builder.append("[CQ:").append(type.name()).append(",");
        params.forEach((k, v) -> builder.append(k).append("=").append(MetaMessage.escapeEncode(v)).append(","));
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[CQ:").append(type.name()).append(",");
        params.forEach((k, v) -> builder.append(k).append("=").append(v).append(","));
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }


    public Type getType() {
        return type;
    }

    public String getParams(String key) {
        return params.getOrDefault(key, "");
    }

    /**
     * @param key   key
     * @param value 会自动编码
     */
    public void setParams(String key, String value) {
        this.params.put(key, MetaMessage.escapeEncode(value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CQCodeMessage cqCodeMessage)) return false;
        return type == cqCodeMessage.type && Objects.equals(params, cqCodeMessage.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, params);
    }
}
