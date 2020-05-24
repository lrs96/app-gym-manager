package br.com.app_my_manage_mobile

import androidx.room.Room

object DatabaseManager {

    private var dbInstace: LMSDatabase
    init {
        val appContext = LMSAplication.getInstance().applicationContext
        dbInstace = Room.databaseBuilder(
            appContext,
            LMSDatabase::class.java,
            "LMS.sqlite"
        ).build()
    }

    fun getAlunoDAO():AlunoDAO {
        return dbInstace.alunoDAO()
    }
}