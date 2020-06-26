package com.surelabs.request.saldo.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import com.surelabs.request.saldo.R
import kotlinx.android.synthetic.main.activity_admin.*

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        subscribeTopics.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic("admin")
                .addOnSuccessListener {
                    Toast.makeText(this@AdminActivity, "Topic Berhasil diSubscribe", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this@AdminActivity, "Something Wrong", Toast.LENGTH_SHORT).show()
                    Log.e("addOnFailureListener:",it.message.toString())
                }
        }
    }
}
