package com.android.alan.leave.stores;

import com.android.alan.leave.actions.Action;
import com.android.alan.leave.actions.MessageAction;
import com.android.alan.leave.model.Message;
import com.squareup.otto.Subscribe;

/**
 * Created by yzl on 16/4/25.
 */
public class MessageStore extends Store {

    private static MessageStore singleton;
    private Message mMessage = new Message();

    public MessageStore(){
        super();
    }

    public String getMessage(){
        return mMessage.getMessage();
    }

    @Override
    public StoreChangeEvent changeEvent() {
        return new StoreChangeEvent();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()){
            case MessageAction.ACTION_NEW_MESSAGE:
                mMessage.setMessage((String)action.getData());
                break;
            default:
        }

        emitStoreChange();
    }

}
