package br.com.app_my_manage_mobile

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService: FirebaseMessagingService() {
    val TAG = "Firebase_LMS"

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        Log.d(TAG, "Novo Token: $token")

        Prefs.setString("FB_TOKEN", token!!)
    }

    override fun onMessageReceived(mensagemRemota: RemoteMessage?) {
        super.onMessageReceived(mensagemRemota)
        Log.d(TAG, "onMessageReceived")

        if(mensagemRemota?.notification != null) {
            val titulo = mensagemRemota?.notification?.title
            var corpo = mensagemRemota?.notification?.body
            Log.d(TAG, "Corpo: $corpo")

            if(mensagemRemota?.data.isNotEmpty()) {
                val alunoId = mensagemRemota.data.get("alunoID")
                corpo = "$corpo ($alunoId)"
            }

            val intent = Intent(this, ListAlunosActivity::class.java)
            NotificationUtil.create(this,1, intent, titulo!!, corpo!!)
        }
    }
}
