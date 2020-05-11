package br.com.app_my_manage_mobile

import android.content.Context
import android.util.Log
import br.com.fernandosousa.lmsapp.HttpHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AlunoService {
    val host = "https://fesousa.pythonanywhere.com"
    val TAG = "WS_LMSApp"

    fun getDisciplinas (context: Context): List<Aluno> {
        if( AndroidUtils.isInternetDisponivel(context) ) {
            val url = "$host/disciplinas"
            var json =  HttpHelper.get(url)

            var alunos = parserJson<List<Aluno>>(json)

            for (a in alunos) {
                saveOffile(a)
            }
            Log.d(TAG, json)

            return alunos
        } else {
            var dao = DatabaseManager.getAlunoDAO()
            return dao.findAll()
        }
    }

    fun saveOffile(aluno: Aluno) : Boolean {
        val dao = DatabaseManager.getAlunoDAO()

        if(! existeAluno(aluno)) {
            dao.insert(aluno)
        }
        return true

    }

    fun existeAluno(aluno: Aluno): Boolean {
        val dao = DatabaseManager.getAlunoDAO()
        return  dao.getById(aluno.id) != null
    }


    fun save(disciplina: Aluno): Response {
        val json = HttpHelper.post("$host/disciplinas", disciplina.toJson())
        return parserJson(json)
    }

    fun delete(disciplina: Aluno): Response {
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