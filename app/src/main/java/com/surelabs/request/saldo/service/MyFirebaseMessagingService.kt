package com.surelabs.request.saldo.service

import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage?) {
        super.onMessageReceived(p0)

        //check apakah pesan yang datang berisi notifikasi or  node data (node:kumpulan)

        if(p0?.notification != null){
            //berarti yg dikirimkan type notification
            val body = p0.notification?.body
            Toast.makeText(this, body, Toast.LENGTH_SHORT).show()
        }else{
            // berarti yg dikirimkan berupa data payload
        }
    }


    //ketika token berubah, ini yang akan antisipasi
    override fun onNewToken(p0: String?) {
        super.onNewToken(p0)
        //harus ada proses untuk update token ke database server berdasarkan user yg login
    }
}