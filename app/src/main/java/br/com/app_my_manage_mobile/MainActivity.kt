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
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() =  this
    private var disciplinas = listOf<Disciplina>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        //Menu superior
        supportActionBar?.setTitle("Bem vindo!")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configuraMenuLateral()

        // configurar cardview
        recyclerDisciplinas?.layoutManager = LinearLayoutManager(context)
        recyclerDisciplinas?.itemAnimator = DefaultItemAnimator()
        recyclerDisciplinas?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskDisciplinas()
    }

    fun taskDisciplinas() {
        Thread {
            this.disciplinas = DisciplinaService.getDisciplinas(context)
            runOnUiThread {
                // atualizar lista
                recyclerDisciplinas?.adapter = DisciplinaAdapter(disciplinas) { onClickDisciplina(it) }
            }
        }.start()
    }

    // tratamento do evento de clicar em uma disciplina
    fun onClickDisciplina(disciplina: Disciplina) {
        Toast.makeText(context, "Clicou disciplina ${disciplina.nome}", Toast.LENGTH_SHORT).show()
    }

    // configuração do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    // método que deve ser implementado quando a activity implementa a interface NavigationView.OnNavigationItemSelectedListener
    // para tratar os eventos de clique no menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dashboard -> {
                var i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }

            R.id.nav_listar_alunos -> {
                var i = Intent(this, ListAlunosActivity::class.java)
                startActivity(i)
            }

            R.id.nav_adicionar_aluno -> {
                var i = Intent(this, PessoaActivity::class.java)
                startActivity(i)
            }

            R.id.nav_adicionar_avaliacao -> {
                var i = Intent(this, FichaMedicaActivity::class.java)
                startActivity(i)
            }

            R.id.nav_sair -> {
                var editor: SharedPreferences.Editor =
                    getSharedPreferences("pref", Context.MODE_PRIVATE).edit()
                editor.remove("login")
                editor.remove("senha")
                editor.commit()

                finish()
                var i = Intent(this, LoginActivity::class.java )
                startActivity(i)
            }
        }

        // fecha menu depois de tratar o evento
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
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
