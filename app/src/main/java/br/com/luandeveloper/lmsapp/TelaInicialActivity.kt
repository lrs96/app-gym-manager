package br.com.luandeveloper.lmsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        setSupportActionBar(toolbar)

        //Mudar o título da página
        supportActionBar?.title = "Cia Life"

        // Colocar a seta de voltar a página inicial
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var params = intent.extras
        val nome_usuario = params?.getString("nome_usuario")
        var numero = params?.getInt("numero")

        Toast.makeText(
            this,
            "Bem vindo $nome_usuario, você é um aluno nota $numero",
            Toast.LENGTH_LONG
        ).show()

        btnLogoutApp.setOnClickListener { cliqueLogout() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == R.id.action_buscar) {
            Toast.makeText(this, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if ( id == R.id.action_atualizar) {
            Toast.makeText(this, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if ( id == R.id.action_config) {
            Toast.makeText(this, "Botão de configuração", Toast.LENGTH_LONG).show()
        } else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun cliqueLogout() {
        val returnIntent = Intent();
        returnIntent.putExtra("Result", "Saindo do Aplicativo")
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}
