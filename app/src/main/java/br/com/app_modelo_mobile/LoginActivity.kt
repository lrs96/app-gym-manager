package br.com.app_modelo_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.com.app_modelo_mobile.bo.validarCamposLogin
import br.com.app_modelo_mobile.util.showMsgToast
import br.com.app_modelo_mobile.validation.LoginValidation
import kotlinx.android.synthetic.main.activity_login.*
import java.util.prefs.Preferences
import br.com.app_modelo_mobile.MainActivity as MainActivity1

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        var preferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        var login = preferences.getString("login", null)
        var senha = preferences.getString("senha", null)

        if (!login.equals(null).and(!senha.equals(null))) {
            var i = Intent(this, MainActivity1::class.java)
            startActivity(i)
            finish()
        }

        edtLogin.findViewById<EditText>(R.id.edtLogin)
        edtSenha.findViewById<EditText>(R.id.edtSenha)

        btnLogar.setOnClickListener(View.OnClickListener {

            var login = edtLogin.text.toString()
            var senha = edtSenha.text.toString()

            var validation: LoginValidation =
                LoginValidation(login, senha, edtLogin, edtSenha, this)

            var isValido = validarCamposLogin(validation)

            if (isValido) {
                var i = Intent(this, MainActivity1::class.java)
                startActivity(i)
                finish()

            }

        })
    }


}
