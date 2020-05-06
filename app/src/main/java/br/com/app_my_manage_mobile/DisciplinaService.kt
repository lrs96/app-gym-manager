package br.com.app_my_manage_mobile

import android.content.Context
import android.util.Log
import br.com.fernandosousa.lmsapp.HttpHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object DisciplinaService {
    val host = "https://fesousa.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getDisciplinas (context: Context): List<Disciplina> {
        if( AndroidUtils.isInternetDisponivel(context) ) {
            val url = "$host/disciplinas"
            var json =  HttpHelper.get(url)

            Log.d(TAG, json)

            return parserJson<List<Disciplina>>(json)
        } else {
            return ArrayList()
        }
    }
    fun save(disciplina: Disciplina): Response {
        val json = HttpHelper.post("$host/disciplinas", disciplina.toJson())
        return parserJson(json)
    }

    fun delete(disciplina: Disciplina): Response {
        Log.d(TAG, disciplina.id.toString())
        val url = "$host/disciplinas/${disciplina.id}"
        val json = HttpHelper.delete(url)
        Log.d(TAG, json)
        return parserJson(json)
    }



    inline fun<reified T> parserJson(json: String) : T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}