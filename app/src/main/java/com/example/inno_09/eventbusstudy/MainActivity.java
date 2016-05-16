package com.example.inno_09.eventbusstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter mAdapter;
    EditText editText;
    Button btnSend, btnMove;
    Server server;
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventBus = EventBus.getDefault();
        server = Server.getInstance();

        editText = (EditText)findViewById(R.id.editText);
        btnSend = (Button)findViewById(R.id.btnSend);
        btnMove = (Button)findViewById(R.id.btnMove);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editText.getText().toString();
                if(!TextUtils.isEmpty(item)) {
                    server.saveItem(item);
                    editText.setText("");
                }
            }
        });

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        eventBus.register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    @Subscribe
    public void onEvent(Update update) {
        mAdapter.fillList(update.updateItems);
    }
}
