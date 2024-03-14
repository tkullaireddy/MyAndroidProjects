package com.tadipatri.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var txt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        txt = findViewById(R.id.info_txt)

        val filter: IntentFilter = IntentFilter()

        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_USAGE_SUMMARY)


        val filter1: IntentFilter = IntentFilter()
        filter1.addAction(Intent.ACTION_DIAL)
        filter1.addAction(Intent.ACTION_ANSWER)
        filter1.addAction(Intent.ACTION_CALL)
        filter1.addAction(Intent.ACTION_SCREEN_ON)
        //filter.addAction(Intent.Action_h)

        registerReceiver(MyReceiver(txt), filter)
        registerReceiver(PhoneStateReceiver(txt), filter1)


    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(MyReceiver(txt))
        unregisterReceiver(PhoneStateReceiver(txt))
    }

}