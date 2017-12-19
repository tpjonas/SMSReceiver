package cimdata.android.dez2017.smsreceiver.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cimdata.android.dez2017.smsreceiver.R;
import cimdata.android.dez2017.smsreceiver.receivers.SMSReceiver;

public class DisplayActivity extends AppCompatActivity {

    TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        infoText = findViewById(R.id.txt_acdis_info);
        
        Intent intent = getIntent();
        
        displayData(intent, infoText);

        
    }

    private void displayData(Intent intent, TextView view) {

        String telnumber = intent.getStringExtra(SMSReceiver.INTENT_EXTRA_TEL_NUMBER);
        String body = intent.getStringExtra(SMSReceiver.INTENT_EXTRA_SMS_BODY);

        String msg = "";
        msg += telnumber + " - ";
        msg += body;
        msg += "\n\n";

        infoText.setText(msg);

    }
}
