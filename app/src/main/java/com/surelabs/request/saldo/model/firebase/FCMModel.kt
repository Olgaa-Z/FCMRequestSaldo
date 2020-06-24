package com.surelabs.request.saldo.model.firebase

class FCMModel {
    var to: String? = null
    var notification: NotificationItem? = null
    var data: DataPayload? = null
}

class NotificationItem {
    var title: String? = null
    var body: String? = null
    var image: String? = null
}

class DataPayload {
    //add your custom data here
}