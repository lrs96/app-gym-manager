package br.com.app_my_manage_mobile

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlunoDAO {

        @Query("SELECT * FROM aluno WHERE id = :id")
    fun getById(id: Long): Aluno?

    @Query("Select * FROM aluno")
    fun findAll(): List<Aluno>

    @Insert
    fun insert(aluno: Aluno)
    @Delete
    fun delete(aluno: Aluno)
}