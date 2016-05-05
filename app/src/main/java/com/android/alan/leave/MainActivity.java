package com.android.alan.leave;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.android.alan.leave.actions.ActionCreator;
import com.android.alan.leave.dispatcher.Dispatcher;
import com.android.alan.leave.stores.MessageStore;
import com.android.alan.leave.stores.Store;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fabBtn;
    @BindView(R.id.et_input)
    EditText inputEt;
    @BindView(R.id.tv_text)
    TextView textTv;

    private Dispatcher dispatcher;
    private ActionCreator actionCreator;
    private MessageStore messageStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        dispatcher = Dispatcher.get();
        actionCreator = ActionCreator.get(dispatcher);
        messageStore = new MessageStore();
        dispatcher.register(messageStore);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        messageStore.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        messageStore.unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispatcher.unregister(messageStore);
    }

    @OnClick(R.id.fab)
    public void fabClick(){
        String msg = inputEt.getText().toString();
        actionCreator.sendMessage(msg);
    }

    private void render(MessageStore store){
        textTv.setText(store.getMessage());
    }

    @Subscribe
    public void onStoreChange(Store.StoreChangeEvent storeChangeEvent){
        render(messageStore);
    }

}
