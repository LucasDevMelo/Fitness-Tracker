package co.tiagoaguiar.fitnesstracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

//    private lateinit var btnImc: LinearLayout
    private lateinit var rvMain: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainItems = mutableListOf<MainItem>()
        mainItems.add(
            MainItem(
                id = 1,
                drawableId = R.drawable.baseline_wb_sunny_24,
                textStringId = R.string.label_imc,
                color = Color.GREEN
            )
        )
        mainItems.add(
            MainItem(
                id = 2,
                drawableId = R.drawable.sharp_wb_twilight_24,
                textStringId = R.string.tmb,
                color = Color.YELLOW
            )
        )

        // 1) o layout XML
        // 2) aonde a recyclerview vai aparecer (tela principal, tela cheia)
        // 3) logica -> conectar a xml dentro do recycleview + a sua quantidade de elementos dinamicos
        val adapter = MainAdapter(mainItems)
        rvMain = findViewById(R.id.rv_main)
        //informa ao sdk qual layout que a gente vai inflar
        rvMain.adapter = adapter
        //para informar que elemento sera colocado, onde sera colocado e como
        rvMain.layoutManager = LinearLayoutManager(this)

        //classe para administrar a recycleview e suas celulas(seus layouts de itens)
        //Adapter ->

//        btnImc = findViewById(R.id.btn_imc)
//
//        btnImc.setOnClickListener {
//            //navegar para próxima tela
//            val i = Intent(this, ImcActivity::class.java)
//            //passar dois parametros, o contexto, para saber qual atividade que esta em execucao
//            //e o segundo parametro é a classe que queremos inicializar
//            //Esse é o código padrão para abrir uma nova atividade!!!!
//            startActivity(i)
//        }
    }
    //é para dizer qual é a classe que administra o layout
    private inner class MainAdapter(private val mainItems: List<MainItem>) : RecyclerView.Adapter<MainViewHolder>(){

        //1 - Informa ao RecycleView qual é o layout XML da célula específica
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.main_item, parent, false)
            val viewHolder = MainViewHolder(view)
            return viewHolder
        }
        //2 - disparado toda vez que houver uma rolagem na tela e for necessario trocar o conteudo da celula
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val itemCurrent = mainItems[position]
            holder.bind(itemCurrent)
        }

        //3 - informar quantas células essa listagem terá
        override fun getItemCount(): Int {
            return mainItems.size
        }

    }

    //é a classe da celula em si!!
    private class MainViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(item: MainItem){
            val img: ImageView = itemView.findViewById(R.id.item_img_icon)
            val name: TextView = itemView.findViewById(R.id.item_txt_name)
            val container: LinearLayout = itemView as LinearLayout

            img.setImageResource(item.drawableId)
            //anexar texto string
            name.setText(item.textStringId)
            container.setBackgroundColor(item.color)
        }

    }
}