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

        if (login !=null && senha!= null) {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        edtLogin.findViewById<EditText>(R.id.edtLogin)
        edtSenha.findViewById<EditText>(R.id.edtSenha)

        btnLogar?.setOnClickListener(View.OnClickListener {

            var login = edtLogin.text.toString()
            var senha = edtSenha.text.toString()

            var validation: LoginValidation =
                LoginValidation(login, senha, edtLogin, edtSenha, this)

            var isValido = validarCamposLogin(validation)

            if (isValido) {
                var i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()

            }

        })
    }


}
