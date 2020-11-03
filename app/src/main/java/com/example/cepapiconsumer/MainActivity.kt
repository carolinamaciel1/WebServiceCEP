package com.example.cepapiconsumer

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutionException

class MainActivity : AppCompatActivity() {

    lateinit var btnBuscarCep: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnBuscarCep = findViewById(R.id.btnMain_buscarCep)
        btnBuscarCep.setOnClickListener { buscarCep() }
    }

    private fun buscarCep() {

        lateinit var cep: EditText
        lateinit var resposta: TextView

        cep = findViewById(R.id.etMain_cep)
        resposta = findViewById(R.id.etMain_resposta)

        try {
            val retorno = HttpService(cep.getText().toString()).execute().get()
            if (retorno != null) {
                resposta.setText(retorno.toString())
            } else {
                Toast.makeText(this, "Um erro ocorreu!", Toast.LENGTH_LONG).show()
            }


        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }
}