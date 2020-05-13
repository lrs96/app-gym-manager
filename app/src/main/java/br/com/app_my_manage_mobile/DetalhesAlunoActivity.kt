package br.com.app_my_manage_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes_aluno.*
import kotlinx.android.synthetic.main.activity_detalhes_aluno.layoutMenuLateral
import kotlinx.android.synthetic.main.activity_detalhes_aluno.menu_lateral
import kotlinx.android.synthetic.main.toolbar.*

class DetalhesAlunoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() = this
    var aluno: Aluno? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_aluno)

        // recuperar onjeto de Disciplina da Intent
        aluno = intent.getSerializableExtra("aluno") as Aluno

        // configurar título com nome da Disciplina e botão de voltar da Toobar
        // colocar toolbar
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = aluno?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()
        // atualizar dados da disciplina
        nomeAluno.text = aluno?.nome
        Picasso.with(this).load(aluno?.foto).fit().into(imagemAluno,
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {}

                override fun onError() { }
            })
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
                var i = Intent(this, CadastroAlunoActivity::class.java)
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

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_disciplina, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado
        // remover a disciplina no WS
        if  (id == R.id.action_remover) {
            // alerta para confirmar a remeção
            // só remove se houver confirmação positiva
            AlertDialog.Builder(this)
                .setTitle(aluno?.nome)
                .setMessage("Deseja excluir o Aluno")
                .setPositiveButton("Sim") {
                        dialog, which ->
                    dialog.dismiss()
                    taskExcluir()
                }.setNegativeButton("Não") {
                        dialog, which -> dialog.dismiss()
                }.create().show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.aluno != null && this.aluno is Aluno) {
            // Thread para remover a disciplina
            Thread {
                AlunoService.delete(this.aluno as Aluno)
                runOnUiThread {
                    // após remover, voltar para activity anterior
                    finish()
                }
            }.start()
        }
    }

}
