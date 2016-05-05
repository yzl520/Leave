package com.android.alan.leave.dispatcher;

import com.android.alan.leave.actions.Action;
import com.android.alan.leave.stores.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzl on 16/4/25.
 */
public class Dispatcher {

    private static Dispatcher instance;
    private final List<Store> stores = new ArrayList<>();

    public static Dispatcher get(){
        if(instance == null){
            instance = new Dispatcher();
        }

        return instance;
    }

    Dispatcher(){}

    public void register(final Store store){
        stores.add(store);
    }

    public void unregister(final Store store){
        stores.remove(store);
    }

    public void dispatch(Action action){
        post(action);
    }

    private void post(final Action action){
        for(Store store : stores){
            store.onAction(action);
        }
    }

}
