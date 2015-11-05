package com.epicodus.discussionforum.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.adapters.MessageAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.epicodus.discussionforum.models.Message;

public class MainActivity extends ListActivity {

    private ParseUser mUser;
    private ArrayList<Message> mMessages;
    private MessageAdapter mAdapter;

    @Bind(R.id.newMessageButton) Button mNewMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (ParseUser.getCurrentUser() != null) {
            Message.all(new Runnable() {
                @Override
                public void run() {
                    mMessages = (ArrayList<Message>) Message.getAllMessages();
                    mAdapter = new MessageAdapter(MainActivity.this, mMessages);
                    setListAdapter(mAdapter);
                }
            });
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        mNewMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewMessageActivity.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
