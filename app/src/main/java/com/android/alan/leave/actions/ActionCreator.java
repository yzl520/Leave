package com.android.alan.leave.actions;

import com.android.alan.leave.dispatcher.Dispatcher;

/**
 * Created by yzl on 16/4/25.
 */
public class ActionCreator {

    private static ActionCreator instance;
    final Dispatcher dispatcher;

    public ActionCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionCreator get(Dispatcher dispatcher){
        if(instance == null){
            instance = new ActionCreator(dispatcher);
        }
        return instance;
    }

    public void sendMessage(String message){
        dispatcher.dispatch(new MessageAction(MessageAction.ACTION_NEW_MESSAGE, message));
    }

}
