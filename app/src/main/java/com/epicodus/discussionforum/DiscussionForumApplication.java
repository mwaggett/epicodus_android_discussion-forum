package com.epicodus.discussionforum;

import android.app.Application;

import com.parse.Parse;

public class DiscussionForumApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "zkkGVISYqGbHJypbWq6jvbg4yqIcbRV22tu3IDRb", "0CmHN7xgrBNqjP85heMn4vlFPtPzHTth0MfnDF88");
    }
}
