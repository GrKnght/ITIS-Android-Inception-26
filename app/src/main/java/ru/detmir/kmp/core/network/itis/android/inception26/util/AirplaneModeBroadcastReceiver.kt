package ru.detmir.kmp.core.network.itis.android.inception26.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AirplaneModeBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(ctx: Context?, intent: Intent) {
        if (intent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isEnabled = intent.getBooleanExtra("state", false)
            println("TEST TAG - isAirplaneEnabled: $isEnabled")
        }
    }
}