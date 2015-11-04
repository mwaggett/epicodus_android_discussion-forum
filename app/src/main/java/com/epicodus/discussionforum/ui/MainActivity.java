package com.epicodus.discussionforum.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.adapters.MessageAdapter;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import models.Message;

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

        mUser = new ParseUser();
        mUser.setUsername("test-user");
        mUser.setPassword("secret_password");
        mUser.setEmail("user@example.com");
        mUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this, "hey " + mUser.getUsername(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });

        if (ParseUser.getCurrentUser() != null) {
            Message testMessage = new Message("test message");
            mMessages = (ArrayList<Message>) Message.all();
            mAdapter = new MessageAdapter(this, mMessages);
            setListAdapter(mAdapter);
        } else {
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
            Toast.makeText(MainActivity.this, "No one is logged in.", Toast.LENGTH_LONG).show();
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
