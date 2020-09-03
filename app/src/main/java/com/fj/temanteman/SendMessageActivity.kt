package com.fj.temanteman

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fj.temanteman.data.Const
import kotlinx.android.synthetic.main.activity_send_message.*
import kotlinx.android.synthetic.main.content_send_message.*

class SendMessageActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var smsManager: SmsManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val sendSMSPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
        if (sendSMSPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1
            )
        }

        val msg = "android.telephony.SmsManager.STATUS_ON_ICC_SENT"
        pendingIntent = PendingIntent.getBroadcast(this, 0, Intent(msg), 0)

        val getPhone = intent.getStringExtra(Const.KEY_PHONE)
        edt_phone.setText(getPhone)

        smsManager = SmsManager.getDefault()
        fab.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {
                val phone = edt_phone.text.toString()
                val message = edt_message.text.toString()

                when {
                    message.isEmpty() -> edt_message.error = getString(R.string.must_be_filled)
                    else -> {
                        smsManager.sendTextMessage(
                                phone,
                                null,
                                message,
                                pendingIntent, null)

                        Toast.makeText(this, "SMS Send", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
