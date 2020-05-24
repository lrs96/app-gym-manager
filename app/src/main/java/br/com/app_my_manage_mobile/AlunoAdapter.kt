package br.com.app_my_manage_mobile
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso

// define o construtor que recebe a lista de disciplinas e o evento de clique
class AlunoAdapter (
    val alunos: List<Aluno>,
    val onClick: (Aluno) -> Unit): RecyclerView.Adapter<AlunoAdapter.AlunosViewHolder>() {

    // ViewHolder com os elemetos da tela
    class AlunosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView

        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_alunos)

        }

    }

    // Quantidade de disciplinas na lista

    override fun getItemCount() = this.alunos.size

    // inflar layout do adapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunosViewHolder {
        // infla view no adapter
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_aluno, parent, false)

        // retornar ViewHolder
        val holder = AlunosViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados

    override fun onBindViewHolder(holder: AlunosViewHolder, position: Int) {
        val context = holder.itemView.context

        // recuperar objeto disciplina
        val aluno = alunos[position]

        // atualizar dados de disciplina

        holder.cardNome.text = aluno.nome
        holder.cardProgress.visibility = View.VISIBLE

        // download da imagem
        Picasso.with(context).load(aluno.foto).fit().into(holder.cardImg,
            object: com.squareup.picasso.Callback{
                override fun onSuccess() {
                    holder.cardProgress.visibility = View.GONE
                }

                override fun onError() {
                    holder.cardProgress.visibility = View.GONE
                }
            })

        // adiciona evento de clique
        holder.itemView.setOnClickListener {onClick(aluno)}
    }
}