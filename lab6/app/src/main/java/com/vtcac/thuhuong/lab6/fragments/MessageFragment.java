package com.vtcac.thuhuong.lab6.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.vtcac.thuhuong.lab6.R;
import com.vtcac.thuhuong.lab6.activities.SendSMSEmailActivity;
import com.vtcac.thuhuong.lab6.adapter.MessageAdapter;
import com.vtcac.thuhuong.lab6.beans.BeanMessage;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class MessageFragment extends Fragment {
    public static final int REQUEST_CODE_SEND_SMS = 11;
    public static final int REQUEST_CODE_SEND_EMAIL = 12;
    private ArrayList<BeanMessage> messages = new ArrayList<BeanMessage>();
    private MessageAdapter messageAdapter;
    private ListView lvMessages;
    private ImageView ivSendMsg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Intent intent = new Intent(getContext(), SendSMSEmailActivity.class);
        lvMessages = getActivity().findViewById(R.id.lvMessages);
        messages.add(new BeanMessage("Mr. C", "Hello, how are you ?", "sms"));
        messages.add(new BeanMessage("Mr. B", "Can you give me some money ?", "email"));
        messages.add(new BeanMessage("Mr. E", "Yes, I am", "sms"));
        messageAdapter = new MessageAdapter(messages, getContext());
        lvMessages.setAdapter(messageAdapter);


        // send msg activity
        ivSendMsg = getActivity().findViewById(R.id.ivSendMsg);
        ivSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getContext(), ivSendMsg);
                popupMenu.getMenuInflater().inflate(R.menu.menu_send_msg, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mniSendSMS:
                                intent.putExtra("action", "send_sms");
                                startActivityForResult(intent, REQUEST_CODE_SEND_SMS);
                                break;
                            case R.id.mniSendEmail:
                                intent.putExtra("action", "send_email");
                                startActivityForResult(intent, REQUEST_CODE_SEND_EMAIL);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SEND_SMS:
                    break;
                case REQUEST_CODE_SEND_EMAIL:
                    break;
            }
        }
    }
}
