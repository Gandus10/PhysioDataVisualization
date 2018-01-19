package com.example.laurentgander.physiodatavisualization;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.Date;

/**
 * Created by laurent.gander on 09.01.2018.
 */

public abstract class PhoneSMSReceiver extends BroadcastReceiver {
    private final String   ACTION_RECEIVE_SMS  = "android.intent.action.NEW_SMS_INPUT";

    protected void onIncomingSMS(Context ctx, Date start){}


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_RECEIVE_SMS)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");

                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if (messages.length > -1) {
                    Date date = new Date();
                    onIncomingSMS(context, date);
                }
            }
        }
    }
}
