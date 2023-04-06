package co.tiagoaguiar.fitnesstracker

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class ImcActivity : AppCompatActivity() {

    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        editWeight = findViewById(R.id.edit_imc_weight)
        editHeight = findViewById(R.id.edit_imc_height)

        val btnSend: Button = findViewById(R.id.btn_imc_send)

        btnSend.setOnClickListener {
            if (!validate()){
                Toast.makeText(this, "todos os campos ser maior q zero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val weight = editWeight.text.toString().toInt()
            val height = editHeight.text.toString().toInt()

            val result = calculateIMC(weight, height)
            Log.d("Teste", "resultado: $result")

            val imcResponseId = imcResponse(result)
            //para aparecer um PopUp pequeno e simples
            //Toast.makeText(this, imcResponseId, Toast.LENGTH_SHORT).show()

            //criando Dialogs
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.imc_response, result))
                .setMessage(imcResponseId)
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                //aqui vai rodar depois do click
            }
            .create()
            .show()
        }
    }

    private fun imcResponse(imc: Double): Int {
        return when {
            imc < 15.0 -> R.string.imc_severely_low_weight
            imc < 16.0 -> R.string.imc_very_low_weight
            imc < 18.5 -> R.string.imc_low_weight
            imc < 25.0 -> R.string.normal
            imc < 30.0 -> R.string.imc_high_weight
            imc < 35.0 -> R.string.imc_so_high_weight
            imc < 40.0 -> R.string.imc_severely_high_weight
            else -> R.string.imc_extreme_weight
        }
    }

    private fun calculateIMC(weight: Int, height:Int): Double{
        //peso / (altura * altura)
        return weight/ ((height / 100.0) * (height / 100.0))
    }

    private fun validate(): Boolean {
        //nao pode inserir valores nulos / vazio
        //nao pode inserir/comecar com 0

        //true && true = true
        //true && false = false
        //false && true = false
        //false && false = false

        return (editWeight.text.toString().isNotEmpty()
                && editWeight.toString().isNotEmpty()
                && !editHeight.text.toString().startsWith("0")
                && !editWeight.text.toString().startsWith("0"))

        //É o mesmo que:

        //if (editWeight.text.toString().isNotEmpty()
        //    && editHeight.text.toString().isNotEmpty()
        //!é para negar a lógica, então ele vai ser verdadeiro quando ele NÃO começar com 0
        //    && !editHeight.text.toString().startsWith("0")
        //    && !editWeight.text.toString().startsWith("0")) {
        //    return true
        //} else {
        //    return false
        //}
    }

}