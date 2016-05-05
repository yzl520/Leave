package com.android.alan.leave.actions;

/**
 * Created by yzl on 16/4/25.
 */
public class Action<T> {

    private final String type;
    private final T data;

    Action(String type, T data){
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }
}
