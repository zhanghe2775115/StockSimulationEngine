package com.zhanghe.stockSimulating.Util.Enum;

/**
 * Created by Drake on 2018/3/11.
 */
public enum OrderEnum {
    BUYINTO(0, "BUYINTO"),
    SELL(1, "SELL"),
    MAX(2,"MAX");
    private int id;
    private String type;

    OrderEnum(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
