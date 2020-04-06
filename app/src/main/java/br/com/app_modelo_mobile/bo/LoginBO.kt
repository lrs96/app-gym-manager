package br.com.app_modelo_mobile.bo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import br.com.app_modelo_mobile.LoginActivity
import br.com.app_modelo_mobile.util.showMsgToast
import br.com.app_modelo_mobile.validation.LoginValidation

fun validarCamposLogin(validation: LoginValidation): Boolean {
    var result: Boolean = true

    if (validation.login.equals(null) || validation.login.isEmpty()) {
        validation.edtLogin.setError("Campo Obrigatório!")
        result = false
    }

    if (validation.senha.equals(null) || validation.senha.isEmpty()) {
        validation.edtSenha.setError("Campo Obrigatório!")
        result = false
    }

    if (result) {

        if (!validation.login.equals("admin").or(!validation.senha.equals("admin"))) {
            result = if (!validation.login.contains("aluno.faculdadeimpacta.com.br")) {
                validation.edtLogin.setError("Usuario invalido!")
                false
            }else{
                showMsgToast(validation.activity as AppCompatActivity, "Login/Senha inválidos!")
                false
            }

        }else{
            var editor: SharedPreferences.Editor = validation.activity.getSharedPreferences("pref", Context.MODE_PRIVATE).edit()
            editor.putString("login", validation.login)
            editor.putString("senha", validation.senha)
            editor.commit()
        }
    }



    return result
}

fun deslogar(){

}
