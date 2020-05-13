package br.com.app_my_manage_mobile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import br.com.app_my_manage_mobile.bo.validarCamposLogin
import br.com.app_my_manage_mobile.validation.LoginValidation
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        var preferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        var login = preferences.getString("login", null)
        var senha = preferences.getString("senha", null)


        edtLogin.findViewById<EditText>(R.id.edtLogin)
        edtSenha.findViewById<EditText>(R.id.edtSenha)

        btnLogar?.setOnClickListener{ onClickLogin() }

        // procurar pelas preferências, se pediu para guardar usuário e senha
        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome  = Prefs.getString("lembrarNome")
            var lembrarSenha  = Prefs.getString("lembrarSenha")
            edtLogin.setText(lembrarNome)
            edtSenha.setText(lembrarSenha)
            checkBoxLogin.isChecked = lembrar

        }

    }

    fun onClickLogin() {

        var login = edtLogin.text.toString()
        var senha = edtSenha.text.toString()

        // armazenar valor do checkbox
        Prefs.setBoolean("lembrar", checkBoxLogin.isChecked)
        // verificar se é para pembrar nome e senha
        if (checkBoxLogin.isChecked) {
            Prefs.setString("lembrarNome", login)
            Prefs.setString("lembrarSenha", senha)
        } else{
            Prefs.setString("lembrarNome", "")
            Prefs.setString("lembrarSenha", "")
        }

        var validation: LoginValidation =
            LoginValidation(login, senha, edtLogin, edtSenha, this)

        var isValido = validarCamposLogin(validation)

        if (isValido) {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()

        }

    }


}
