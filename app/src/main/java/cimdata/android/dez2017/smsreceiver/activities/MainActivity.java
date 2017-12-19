package cimdata.android.dez2017.smsreceiver.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cimdata.android.dez2017.smsreceiver.R;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSIONS_SMS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hier fragen wir die Rechte ab und bitten wir sie, sie
        // anzuschalten, falls sie noch nicht an sind

        boolean hasSMSPermissions = checkSmsPermissions();

        if(!hasSMSPermissions) {
            requestSmsPermissions();
        }

    }

    // Hier fragen wir ab, ob die notwendigen Permissions vorliegen.
    private boolean checkSmsPermissions() {

        int hasReceivePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        int hasReadPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        Toast.makeText(this, "hasReceivePermission: " + hasReceivePermission, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "hasReadPermission: " + hasReadPermission, Toast.LENGTH_SHORT).show();

        return
                hasReceivePermission == PackageManager.PERMISSION_GRANTED &&
                hasReadPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestSmsPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[] {
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.READ_SMS
                },
                REQUEST_CODE_PERMISSIONS_SMS
        );
    }

}
