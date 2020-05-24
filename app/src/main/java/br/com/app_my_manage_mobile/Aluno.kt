package br.com.app_my_manage_mobile

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable


@Entity(tableName="aluno")
class Aluno : Serializable {

    @PrimaryKey
    var id:Long = 0
    var nome = ""
    var idade = ""
    var foto = ""
    var cpf = ""

    override fun toString(): String {
        return "Aluno(nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}