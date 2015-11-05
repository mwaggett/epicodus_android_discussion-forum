package models;

import android.app.AlertDialog;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Message {

    private String mId;
    private String mContent;
    private ParseUser mAuthor;
    private Date mCreatedAt;
    private static List<Message> mAllMessages;

    public Message(ParseObject message) {
        mId = message.getObjectId();
        mContent = message.getString("content");
        mAuthor = message.getParseUser("author");
        mCreatedAt = message.getCreatedAt();
    }

    public Message(String content) {
        mContent = content;
        mAuthor = ParseUser.getCurrentUser();

        final ParseObject newMessage = new ParseObject("Message");
        newMessage.put("content", mContent);
        newMessage.put("author", mAuthor);
        newMessage.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    mId = newMessage.getObjectId();
                    mCreatedAt = newMessage.getCreatedAt();
                }
            }
        });
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public ParseUser getAuthor() {
        return mAuthor;
    }

    public void setAuthor(ParseUser author) {
        mAuthor = author;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }

    public String getFormattedCreatedAt() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
        return formatter.format(mCreatedAt);
    }

    public static void all(final Runnable runnable) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject object : objects) {
                        Message newMessage = new Message(object);
                        mAllMessages.add(newMessage);
                    }
                    runnable.run();
                }
            }
        });
    }

    public static List<Message> getAllMessages() {
        return mAllMessages;
    }
}
