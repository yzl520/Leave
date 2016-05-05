package com.android.alan.leave.stores;

import com.android.alan.leave.actions.Action;
import com.squareup.otto.Bus;

/**
 * Created by yzl on 16/4/25.
 */
public abstract class Store {

    private static final Bus bus = new Bus();

    public void register(final Object view){
        bus.register(view);
    }

    public void unregister(final Object view){
        bus.unregister(view);
    }

    void emitStoreChange(){
        bus.post(changeEvent());
    }

    public abstract StoreChangeEvent changeEvent();

    public abstract void onAction(Action action);

    public class StoreChangeEvent{}

}
