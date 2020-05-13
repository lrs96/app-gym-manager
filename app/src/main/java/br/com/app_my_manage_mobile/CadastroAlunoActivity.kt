package br.com.app_my_manage_mobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_list_alunos.layoutMenuLateral
import kotlinx.android.synthetic.main.activity_list_alunos.menu_lateral
import kotlinx.android.synthetic.main.activity_pessoa.*
import kotlinx.android.synthetic.main.toolbar.*

class CadastroAlunoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context: Context get() =  this
    private var alunos = listOf<Aluno>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pessoa)
        setSupportActionBar(toolbar)
        //Menu superior
        supportActionBar?.setTitle("Cadastro de Alunos")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configuraMenuLateral()

        buttonCadastrar.setOnClickListener {
            val aluno = Aluno()
            aluno.nome = campoNome.text.toString()
            aluno.idade = campoIdade.text.toString()
            aluno.cpf = campoCpf.text.toString()
            aluno.foto = campoFoto.text.toString()
            taskAtualizar(aluno)
        }

    }

    private fun taskAtualizar(aluno: Aluno) {
        // Thread para salvar a discilpina
        Thread {
            AlunoService.save(aluno)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
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


}
