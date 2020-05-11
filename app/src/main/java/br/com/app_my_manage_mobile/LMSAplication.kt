package br.com.app_my_manage_mobile

import android.app.Application
import java.lang.IllegalStateException

class LMSAplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: LMSAplication? = null
        
        fun getInstance(): LMSAplication {
            if(appInstance == null) {
                throw IllegalStateException("Configurar aplication no manifest")
            }
            return  appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}