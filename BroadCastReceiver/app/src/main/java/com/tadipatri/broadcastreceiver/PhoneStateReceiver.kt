package com.tadipatri.broadcastreceiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.TextView
import android.widget.Toast


class PhoneStateReceiver(val txt: TextView) : BroadcastReceiver() {


    companion object{
        val CUSTOM_BROADCAST:String = "com.tadipatri.CUSTOM_BROADCAST"
    }

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        // TODO("PhoneStateReceiver.onReceive() is not implemented")




        try {
            txt.text = intent.action.toString()
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                Toast.makeText(context, "Ringing State Number is - $incomingNumber", Toast.LENGTH_SHORT)
                    .show()
            } else if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))) {
                Toast.makeText(context, "Received State $intent.action.toString()", Toast.LENGTH_SHORT)
                    .show()
            } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Toast.makeText(context, "Idle State $intent.action.toString()", Toast.LENGTH_SHORT)
                    .show()
            } else
                Toast.makeText(context, " Receiver start $intent.action.toString()", Toast.LENGTH_SHORT)
                    .show()

        } catch(e:Exception) {
            Toast.makeText(context, e.message.toString(), Toast.LENGTH_LONG)
                .show()
            // handler
        } finally {
            Toast.makeText(context, "okay", Toast.LENGTH_SHORT)
                .show()
            // optional finally block
        }



    }

}