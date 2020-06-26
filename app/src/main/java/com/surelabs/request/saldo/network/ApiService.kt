package com.surelabs.request.saldo.network

import com.surelabs.request.saldo.model.firebase.FCMModel
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiService{
    //karna setiap apk yg request ke fcm, dia harus ada token, thats why need @Headers
    @Headers(
        "Content-Type:Application/json",
        "Authorization:key=AAAAVPOm88w:APA91bGcZYvq9TkCmMvjQEB9SNockEeuaHfGJMQdYD411vq7RIz32OUkZIh4f-lyn1-ySIPwHOaavPpFcicvieUNDdzsA2vwxQ5Eq7opjJn5y4u71oUlAh6Zz0-Mh2YVMJqgIVpBhPYl"
    )

    //post pasti selalu ada formurlencoded, jadi both selalu ada
//    @FormUrlEncoded
    //kalau pake @Body, ditambhakn @FormUrlEncoded, tapi kalau @Body-> @Field : baru pakai @FormUrlEncoded
    @POST("fcm/send")
    fun sendFcmData(@Body fcmModel: FCMModel): retrofit2.Call<ResponseBody>



}

