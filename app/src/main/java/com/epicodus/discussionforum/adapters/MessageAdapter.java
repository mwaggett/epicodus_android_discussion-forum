package com.epicodus.discussionforum.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epicodus.discussionforum.R;

import java.util.ArrayList;

import com.epicodus.discussionforum.models.Message;

public class MessageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Message> mMessages;

    public MessageAdapter(Context context, ArrayList<Message> messages) {
        mContext = context;
        mMessages = messages;
    }


    @Override
    public int getCount() {
        return mMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return mMessages.get(position);
    }

    //not needed rn
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.message_view, null);
            holder = new ViewHolder();
            holder.mMessageLabel = (TextView) convertView.findViewById(R.id.messageContentLabel);
            holder.mUserLabel = (TextView) convertView.findViewById(R.id.userLabel);
            holder.mCreatedAtLabel = (TextView) convertView.findViewById(R.id.createdAtLabel);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Message message = mMessages.get(position);

        holder.mMessageLabel.setText(message.getContent());
        holder.mUserLabel.setText(message.getAuthor().getUsername());
        holder.mCreatedAtLabel.setText(message.getFormattedCreatedAt());

        return convertView;
    }

    public static class ViewHolder {
        TextView mMessageLabel;
        TextView mUserLabel;
        TextView mCreatedAtLabel;
    }
}
