package br.com.app_modelo_mobile.validation

import android.app.Activity
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginValidation(
    login: String,
    senha: String,
    edtLogin: EditText,
    edtSenha: EditText,
    activity: AppCompatActivity
) {

    var login: String = login

    var senha: String = senha

    var edtLogin: EditText = edtLogin

    var edtSenha: EditText = edtSenha

    var activity: Activity = activity


}