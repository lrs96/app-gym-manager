package br.com.app_my_manage_mobile

import java.io.Serializable

class Disciplina : Serializable {

    var id:Long = 0
    var nome = ""
    var ementa = ""
    var foto = ""
    var professor = ""

    override fun toString(): String {
        return "Disciplina(nome='$nome')"
    }
}