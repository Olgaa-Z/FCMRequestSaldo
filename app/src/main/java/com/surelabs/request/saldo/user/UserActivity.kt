package com.surelabs.request.saldo.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.surelabs.request.saldo.R
import com.surelabs.request.saldo.model.firebase.FCMModel
import com.surelabs.request.saldo.model.firebase.NotificationItem
import com.surelabs.request.saldo.network.NetworkModule
import kotlinx.android.synthetic.main.activity_user.*
import me.abhinay.input.CurrencySymbols
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.ThreadLocalRandom

class UserActivity : AppCompatActivity() {

    //? -> harus ada nilai terlebih dahulu ex: null, "", "as"
    //lateinit -> kalau mau gak ada nilai
    private var token : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        jumlahSaldo.setDecimals(false)
        jumlahSaldo.setCurrency(CurrencySymbols.INDONESIA)
        jumlahSaldo.setSeparator(".")
        jumlahSaldo.setDelimiter(false)

        btnKirimRequest.setOnClickListener {
            when {
                jumlahSaldo.text?.isEmpty()== true -> Toast.makeText(this@UserActivity, "Harus Diisi", Toast.LENGTH_SHORT).show()
                jumlahSaldo.text.toString().toInt() < 25000 -> Toast.makeText(this@UserActivity, "Nominal request minimal adalah Rp. 25.000", Toast.LENGTH_SHORT).show()
                else -> requestSaldo()
            }
        }

        //generate token user
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener{
            token = it.token //alias ->It->InstanceIdResult
            Log.d("TOKEN", token.toString())
        }
    }



    private fun requestSaldo() {
        val random = ThreadLocalRandom.current().nextInt(100,999)
//        val totalRequest = jumlahSaldo.text.toString().toInt().plus(random) error awalnya karna semua karakter include Rp,., diambil juga
        val totalRequest = jumlahSaldo.cleanIntValue.plus(random)

        //Build the notifications
        val notificationItem = NotificationItem()
        notificationItem.body = "User meminta pengisian saldo sebesar $totalRequest"
        notificationItem.title = "Permintaan Pengisian Saldo"

        //Build the FCM Request Model
        val fcmModel = FCMModel()
//        fcmModel.to = token
        fcmModel.to = "/topics/admin"
        fcmModel.notification = notificationItem

        //Send fcm data to firebase Server
        NetworkModule.getFcmService().sendFcmData(fcmModel)
            .enqueue(object : retrofit2.Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@UserActivity, t.message.toString(),Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(this@UserActivity, "Berhasil", Toast.LENGTH_SHORT).show()
                    }else{
                        Log.d("onResponseFail:", response.message())
                    }
                }
            })

    }
}
