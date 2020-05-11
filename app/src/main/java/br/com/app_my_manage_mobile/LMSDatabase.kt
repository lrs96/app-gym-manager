package br.com.app_my_manage_mobile

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Aluno::class), version = 1)
abstract class LMSDatabase: RoomDatabase() {
    abstract fun alunoDAO(): AlunoDAO
}