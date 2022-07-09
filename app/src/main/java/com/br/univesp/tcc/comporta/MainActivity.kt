package com.br.univesp.tcc.comporta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startButton)

        val finishButton = findViewById<Button>(R.id.finishButton)

        startButton.setOnClickListener{
            startFun()
        }

        finishButton.setOnClickListener{
            finishFun()
        }
    }

    private fun startFun() {
        val monitoring = Intent(this, Monitoring::class.java)
        startActivity(monitoring)
    }

    private fun finishFun() {
        finishAffinity()
    }

}