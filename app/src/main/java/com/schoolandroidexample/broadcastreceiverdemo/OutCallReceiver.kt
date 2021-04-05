package com.schoolandroidexample.broadcastreceiverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class OutCallReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val outNum = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
        Toast.makeText(context,outNum, Toast.LENGTH_LONG).show()
    }

}