package cimdata.android.dez2017.smsreceiver.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import cimdata.android.dez2017.smsreceiver.activities.DisplayActivity;

public class SMSReceiver extends BroadcastReceiver {

    public static final String INTENT_EXTRA_TEL_NUMBER = "extra.tel.number";
    public static final String INTENT_EXTRA_SMS_BODY = "extra.sms.body";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "SMS angekommen!", Toast.LENGTH_SHORT).show();

        // Zuerst holen wir uns die Extras als Bundle
        // Das ist noch nicht SMS-spezifisch
        Bundle data = intent.getExtras();

        // Dann holen wir uns aus dem Extra
        // das pdus-Array, das einArray von Objekten ist
        Object[] objects = (Object[]) data.get("pdus");

        // Hier müssen wir ein neues Array anlegen, damit wir die Messages aus dem pdus-Array
        // in ein SmsMessage-Array verwandeln
        SmsMessage[] messages = new SmsMessage[objects.length];

        for (int i = 0; i < objects.length; i++) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i], data.getString("format"));
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
            }
        }

        // Hier holen wir uns die aktuelle Nachricht
        SmsMessage currenMessage = messages[0];

        // Hier holen wir uns die Daten aus der Nachricht
        String telNumber = currenMessage.getOriginatingAddress();
        String body = currenMessage.getMessageBody();

        // Hier übertragen wir die Daten zu der Ansicht
        // -- -- Toast.makeText(context, body, Toast.LENGTH_SHORT).show();
        Intent displayIntent = new Intent(context, DisplayActivity.class);
        displayIntent.putExtra(INTENT_EXTRA_TEL_NUMBER, telNumber);
        displayIntent.putExtra(INTENT_EXTRA_SMS_BODY, body);

        context.startActivity(displayIntent);

    }
}
