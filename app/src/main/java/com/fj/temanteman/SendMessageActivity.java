package com.fj.temanteman;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fj.temanteman.data.Consts;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtPhone, edtMessage;
    private SmsManager smsManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int sendSMSPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(sendSMSPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.SEND_SMS
                    }, 1
            );
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        edtPhone = findViewById(R.id.edt_phone);
        edtMessage = findViewById(R.id.edt_message);

        String msg = "android.telephony.SmsManager.STATUS_ON_ICC_SENT";
        pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(msg), 0);

        String getPhone = getIntent().getStringExtra(Consts.KEY_PHONE);
        edtPhone.setText(getPhone);

        smsManager = SmsManager.getDefault();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                String phone = edtPhone.getText().toString();
                String message = edtMessage.getText().toString();

                if (message.isEmpty()) {
                    edtMessage.setError(getString(R.string.must_be_filled));
                } else {
                    smsManager.sendTextMessage(
                            phone,
                            null,
                            message,
                            pendingIntent,
                            null);

                    Toast.makeText(this, "SMS Send", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
