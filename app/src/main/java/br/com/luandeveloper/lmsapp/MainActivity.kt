package br.com.luandeveloper.lmsapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.view.*
import br.com.luandeveloper.lmsapp.TelaInicialActivity as TelaInicialActivity

class MainActivity : DebugActivity() {
    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Carregar a imagem em tempo de execução ----- img_login é o ID da imagem no layoyt
        // img_login.setImageResource(r.drawable.bg_woman_login);

        buttonLogin.setOnClickListener { fazerLogin() }
    }

    fun fazerLogin() {
        var textUsuario = campoUser.text.toString()
        val textPassword = campoPassword.text.toString()


        var intent = Intent(context, TelaInicialActivity::class.java)
        var param = Bundle()
        param.putString("nome_usuario", textUsuario)
        param.putInt("numero", 10)
        param.putIntegerArrayList("array_nros", arrayListOf(1, 2, 3, 4))
        intent.putExtras(param)
        startActivity(intent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }

}
