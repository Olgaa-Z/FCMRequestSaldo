package com.surelabs.request.saldo.model.firebase

//class fcm model untuk kirim ke firebase
//nama varuiabel harus, gak boleh berubah
class FCMModel {
    var to: String? = null
    var notification: NotificationItem? = null
    var data: DataPayload? = null
}

//harus ini 3 yang dikirim
class NotificationItem {
    var title: String? = null
    var body: String? = null
    var image: String? = null
}

//kalau custom data
class DataPayload {
    //add your custom data here
}