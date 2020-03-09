package br.com.luandeveloper.lmsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Carregar a imagem em tempo de execução ----- img_login é o ID da imagem no layoyt
        // img_login.setImageResource(r.drawable.bg_woman_login);
    }
}
