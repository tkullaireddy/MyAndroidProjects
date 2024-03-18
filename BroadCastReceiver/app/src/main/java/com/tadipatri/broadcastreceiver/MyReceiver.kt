package com.tadipatri.broadcastreceiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.widget.Toast

class MyReceiver (val txt:TextView): BroadcastReceiver() {

    @SuppressLint("SetTextI18n")
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
//        TODO("MyReceiver.onReceive() is not implemented")

        Toast.makeText(context, "Action: " + intent.action.toString(), Toast.LENGTH_LONG).show();

        when(intent.action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val isAirplaneModeOn = intent.getBooleanExtra("state", false)
                txt.text = "${intent.action.toString()} = ${isAirplaneModeOn}"
            }
            Intent.ACTION_HEADSET_PLUG -> {
                txt.text = " HEADSET = ${intent.action.toString()} "
            }
            Intent.ACTION_CALL -> {
                txt.text = " call = ${intent.action.toString()} "
            }
            Intent.ACTION_ANSWER ->{
                val answer = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
                txt.text = " call = ${intent.action.toString()} , call from =${answer} "
            }
            Intent.ACTION_DIAL->{
                txt.text = " dial = ${intent.action.toString()} "
            }
            else -> {
                txt.text = "${intent.action.toString()} "
            }
        }

    }
}