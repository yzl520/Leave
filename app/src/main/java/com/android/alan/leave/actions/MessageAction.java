package com.android.alan.leave.actions;

/**
 * Created by yzl on 16/4/25.
 */
public class MessageAction extends Action<String>{

    public static final String ACTION_NEW_MESSAGE = "new_message";

    MessageAction(String type, String data) {
        super(type, data);
    }

}
