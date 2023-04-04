package co.tiagoaguiar.fitnesstracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var btnImc: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnImc = findViewById(R.id.btn_imc)

        btnImc.setOnClickListener {
            //navegar para próxima tela
            val i = Intent(this, ImcActivity::class.java)
            //passar dois parametros, o contexto, para saber qual atividade que esta em execucao
            //e o segundo parametro é a classe que queremos inicializar
            //Esse é o código padrão para abrir uma nova atividade!!!!
            startActivity(i)
        }
    }
}