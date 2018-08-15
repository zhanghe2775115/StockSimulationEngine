package com.zhanghe.stockSimulating.Enum;

/**
 * Created by Drake on 2018/3/25.
 */
public enum WorkState {
    Working(true, "working"),
    Closing(false, "closing");
    private boolean value;
    private String state;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    WorkState(boolean value, String state) {

        this.value = value;
        this.state = state;
    }
}
