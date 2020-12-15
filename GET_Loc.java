package com.thesis2020.monitoringapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class GET_Loc extends BroadcastReceiver {


        private static EditText editText;
        public void setEditText(EditText editText)
        {
            GET_Loc.editText = editText;
        }

        @Override
        public void onReceive(Context context, Intent intent)
        {
            SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

            for (SmsMessage sms : messages)
            {
                String message = sms.getMessageBody(); //google link will be extracted
                //String otp = message.split(": ")[1];

                String VIPloc = message;
                editText.setText(VIPloc); //google link in the VIPloc string



                Intent i = new Intent(context,LocateWithMap.class);
                i.putExtra("linkreceived",VIPloc);
                i.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(i);
            }



        }//onReceive
}

