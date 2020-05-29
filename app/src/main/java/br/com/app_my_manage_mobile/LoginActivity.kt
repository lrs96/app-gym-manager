package br.com.app_my_manage_mobile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import br.com.app_my_manage_mobile.bo.validarCamposLogin
import br.com.app_my_manage_mobile.validation.LoginValidation
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.Executor
import androidx.biometric.BiometricConstants
import androidx.biometric.BiometricPrompt

class LoginActivity : AppCompatActivity() {
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private val context: Context get() =  this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        var preferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        var login = preferences.getString("login", null)
        var senha = preferences.getString("senha", null)


        edtLogin.findViewById<EditText>(R.id.edtLogin)
        edtSenha.findViewById<EditText>(R.id.edtSenha)

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = androidx.biometric.BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Erro de autenticação: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Autenticação feita com sucesso", Toast.LENGTH_SHORT)
                        .show()
                    var i = Intent(context, MainActivity::class.java)
                    startActivity(i)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Falha na Autenticação",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometria")
            .setSubtitle("Utilize sua biometria para fazer login")
            .setNegativeButtonText("Usar dados da conta")
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.

        // procurar pelas preferências, se pediu para guardar usuário e senha
        var lembrar = Prefs.getBoolean("lembrar")
        if (lembrar) {
            var lembrarNome  = Prefs.getString("lembrarNome")
            var lembrarSenha  = Prefs.getString("lembrarSenha")
            edtLogin.setText(lembrarNome)
            edtSenha.setText(lembrarSenha)
            checkBoxLogin.isChecked = lembrar

        }
        biometricPrompt.authenticate(promptInfo)
        btnLogar?.setOnClickListener{
            onClickLogin()
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
