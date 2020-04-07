package br.com.app_my_manage_mobile

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Menu superior
        var progress = 0
        progressBarId.visibility = View.INVISIBLE
        supportActionBar?.setTitle("Bem vindo!")
    }

    fun clickBtnAlertErro(){
        showMsgAlerta(this, "Titulo","Esta é a mensagem ... ", TipoMsg.erro)
    }

    fun clickBtnAlertSucess(){
        showMsgAlerta(this, "Titulo","Esta é a mensagem ... ", TipoMsg.sucesso)
    }

    fun clickBtnEvolucaoFisica(view: View){
        var i = Intent(this, EvolucaoFisicaActivity::class.java)
        startActivity(i)

    }

    fun clickBtnaFichaMedica(view: View){
        var i = Intent(this, FichaMedicaActivity::class.java)
        startActivity(i)

    }

    fun clickBtnaRelatorioMensal(view: View){
        var i = Intent(this, RelatorioMensalActivity::class.java)
        startActivity(i)

    }

    fun clickBtnEnviar(view: View) {
        showMsgAlerta(this, "Titulo", "Esta é a mensagem...", TipoMsg.info)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_login, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun TreadProgressBar( view: View) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_sair -> {
                var editor: SharedPreferences.Editor =
                    getSharedPreferences("pref", Context.MODE_PRIVATE).edit()
                editor.remove("login")
                editor.remove("senha")
                editor.commit()

                finish()
                var i = Intent(this, LoginActivity::class.java)
                startActivity(i)
            }
            R.id.menu_adicionar -> {

                var i = Intent(this, PessoaActivity::class.java)
                startActivity(i)
            }
            R.id.menu_config -> {

                var i = Intent(this, ConfiguracaoActivity::class.java)
                startActivity(i)

            }
            R.id.menu_atualizar -> {
                // showMsgToast(this,"Atualizando...")
                progressBarId.visibility = View.VISIBLE
                var progressStatus = 0;
                // Initialize a new Handler instance
                val handler: Handler = Handler()
                Thread(Runnable {
                    while (progressStatus < 100) {
                        // Update the progress status
                        progressStatus += 1
                        // Try to sleep the thread for 50 milliseconds
                        try {
                            Thread.sleep(10000)

                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        // Update the progress bar
                        handler.post(Runnable {
                            progressBarId.visibility = View.INVISIBLE
                        })
                    }
                }).start()
            }


        }
        return super.onOptionsItemSelected(item)
    }
}
