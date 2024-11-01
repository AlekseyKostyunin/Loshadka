package com.alekseykostyunin.loshadka

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

val receiver = LoshadkaReceiver()

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.MyTextView)

        val filter = IntentFilter("Loshadka!")

        registerReceiver(receiver, filter, RECEIVER_EXPORTED)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onResume() {
        super.onResume()
        updateCounters()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var textView: TextView
        @SuppressLint("SetTextI18n")
        fun updateCounters() {
            if (::textView.isInitialized) {
                textView.text = "Loshadka heard Yezhik " + receiver.callsCounter + " times"
            }
        }
    }
}