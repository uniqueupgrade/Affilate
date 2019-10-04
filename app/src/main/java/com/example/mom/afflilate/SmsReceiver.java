package com.example.mom.afflilate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mom.afflilate.interfaces.SmsListener;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

public class SmsReceiver extends BroadcastReceiver {
    private static SmsListener mListener;
    String strOTPValue = "";

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            Status status = null;
            if (extras != null) {
                status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
            }

            if (status != null) {
                switch (status.getStatusCode()) {
                    case CommonStatusCodes.SUCCESS:
                        // Get SMS message contents
                        String message = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                        strOTPValue = message.replaceAll("[^0-9]", "");
                        mListener.messageReceived(strOTPValue);
                        break;
                    case CommonStatusCodes.TIMEOUT:
                        break;
                }
            }
        }
    }
}