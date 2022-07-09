package com.br.univesp.tcc.comporta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class Monitoring : AppCompatActivity() {

    private lateinit var statusAtivo : TextView
    private lateinit var statusInativo : TextView

    private lateinit var activity : TextView
    private lateinit var notifications : TextView

    private lateinit var imgNotDesativadeOk : ImageView
    private lateinit var imgNotActivadeOk : ImageView
    private lateinit var imagemNotError : ImageView
    private lateinit var imagemMiddleLevel : ImageView
    private lateinit var imagemHightLevel : ImageView
    private lateinit var imagemDangerLevel : ImageView


    private lateinit var imgSysDesactive : ImageView
    private lateinit var imgSysActive : ImageView
    private lateinit var imagemSystemError : ImageView

    private lateinit var textAux1 : TextView
    private lateinit var textAux2 : TextView

    private lateinit var level : String
//    private lateinit var levelIncrement : Number

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.monitoring)

        val actionButton = findViewById<Button>(R.id.actButton)
        val returnButton = findViewById<Button>(R.id.exitButton)

        statusAtivo = findViewById<TextView>(R.id.textActive)
        statusInativo = findViewById<TextView>(R.id.textInative)

        activity = findViewById<TextView>(R.id.textActivity)
        notifications = findViewById<TextView>(R.id.notifications)

        imgNotDesativadeOk = findViewById<ImageView>(R.id.imgNotifDes)
        imgNotActivadeOk = findViewById<ImageView>(R.id.imgNotifAct)
        imagemNotError = findViewById<ImageView>(R.id.imageNotError)
        imagemMiddleLevel = findViewById<ImageView>(R.id.imgMiddleLevel)
        imagemHightLevel = findViewById<ImageView>(R.id.imgHightLevel)
        imagemDangerLevel = findViewById<ImageView>(R.id.imgDanger)

        imgSysDesactive = findViewById<ImageView>(R.id.imgSysDes)
        imgSysActive = findViewById<ImageView>(R.id.imgSysAct)
        imagemSystemError = findViewById<ImageView>(R.id.imageSystemError)


        textAux1 = findViewById<TextView>(R.id.textAux1)
        textAux2 = findViewById<TextView>(R.id.textAux2)

        level = "0"

        verifyStatus()

        actionButton.setOnClickListener{
//            increment()
            verifyStatus()
        }

        returnButton.setOnClickListener{
            returnMain()
        }

    }

    private fun returnMain() {
        val mainReturn = Intent(this, MainActivity::class.java)
        startActivity(mainReturn)
    }




    // comporta ativada sem água detectada
    private fun initialStatus(){

        val msgSys = "Atividade: monitoramento ativo"
        val msgNot = "Não foram detectados níveis perigosos de água"

        statusAtivo.visibility = View.VISIBLE
        statusInativo.visibility = View.INVISIBLE

        imgNotDesativadeOk.visibility = View.VISIBLE
        imgNotActivadeOk.visibility = View.INVISIBLE
        imagemNotError.visibility = View.INVISIBLE

        imgSysDesactive.visibility = View.VISIBLE
        imgSysActive.visibility = View.INVISIBLE
        imagemSystemError.visibility = View.INVISIBLE
        imagemMiddleLevel.visibility = View.INVISIBLE
        imagemHightLevel.visibility = View.INVISIBLE
        imagemDangerLevel.visibility = View.INVISIBLE

        activity.text = msgSys
        activity.visibility = View.VISIBLE

        notifications.text = msgNot
        notifications.visibility = View.VISIBLE

        textAux1.visibility = View.INVISIBLE
        textAux2.visibility = View.INVISIBLE

    }

    //comporta com água pela metade EXTERNO
    private fun middleLevel(){
        val msgSys = "Atividade: sistema de monitoramento ativo"
        val msgNot = "O nível externo de água na comporta é de 50%"

        statusAtivo.visibility = View.VISIBLE
        statusInativo.visibility = View.INVISIBLE

        imgNotDesativadeOk.visibility = View.INVISIBLE
        imgNotActivadeOk.visibility = View.VISIBLE
        imagemNotError.visibility = View.INVISIBLE

        imgSysDesactive.visibility = View.INVISIBLE
        imgSysActive.visibility = View.INVISIBLE
        imagemSystemError.visibility = View.INVISIBLE
        imagemMiddleLevel.visibility = View.VISIBLE
        imagemHightLevel.visibility = View.INVISIBLE
        imagemDangerLevel.visibility = View.INVISIBLE

        activity.text = msgSys
        activity.visibility = View.VISIBLE

        notifications.text = msgNot
        notifications.visibility = View.VISIBLE

        textAux1.visibility = View.INVISIBLE
        textAux2.visibility = View.INVISIBLE
    }

// comporta com nível alto EXTERNO
    private fun highLevel(){
        val msgSys = "Atividade: sistema de monitoramento ativo"
        val msgNot = "O nível externo de água na comporta é de 90%"

        statusAtivo.visibility = View.VISIBLE
        statusInativo.visibility = View.INVISIBLE

        imgNotDesativadeOk.visibility = View.INVISIBLE
        imgNotActivadeOk.visibility = View.VISIBLE
        imagemNotError.visibility = View.INVISIBLE

        imgSysDesactive.visibility = View.INVISIBLE
        imgSysActive.visibility = View.INVISIBLE
        imagemSystemError.visibility = View.INVISIBLE
        imagemMiddleLevel.visibility = View.INVISIBLE
        imagemHightLevel.visibility = View.VISIBLE
        imagemDangerLevel.visibility = View.INVISIBLE

        activity.text = msgSys
        activity.visibility = View.VISIBLE

        notifications.text = msgNot
        notifications.visibility = View.VISIBLE

        textAux1.visibility = View.INVISIBLE
        textAux2.visibility = View.INVISIBLE
    }

    // detectada água no INTERIOR
    private fun internalLevel(){
        val msgSys = "Atividade: sistema de monitoramento ativo"
        val msgNot = "O nível da água ultrapassou a comporta, detectado água no interior do local"

        statusAtivo.visibility = View.VISIBLE
        statusInativo.visibility = View.INVISIBLE

        imgNotDesativadeOk.visibility = View.INVISIBLE
        imgNotActivadeOk.visibility = View.VISIBLE
        imagemNotError.visibility = View.INVISIBLE

        imgSysDesactive.visibility = View.INVISIBLE
        imgSysActive.visibility = View.INVISIBLE
        imagemSystemError.visibility = View.INVISIBLE
        imagemMiddleLevel.visibility = View.INVISIBLE
        imagemHightLevel.visibility = View.INVISIBLE
        imagemDangerLevel.visibility = View.VISIBLE

        activity.text = msgSys
        activity.visibility = View.VISIBLE

        notifications.text = msgNot
        notifications.visibility = View.VISIBLE

        textAux1.visibility = View.INVISIBLE
        textAux2.visibility = View.INVISIBLE
    }

//    private fun functionStatus(){
//
//    }
//
    private fun errorStatus(){
        val msgSys = "Atividade: Inoperante"
        val msgNot = "Sistema desligado ou sem conexão" + "\n" + "\n"+ "\n" + " SEM SINAL "

        statusAtivo.visibility = View.INVISIBLE
        statusInativo.visibility = View.VISIBLE

        imgNotDesativadeOk.visibility = View.INVISIBLE
        imgNotActivadeOk.visibility = View.INVISIBLE
        imagemNotError.visibility = View.VISIBLE

        imgSysDesactive.visibility = View.INVISIBLE
        imgSysActive.visibility = View.INVISIBLE
        imagemSystemError.visibility = View.VISIBLE
        imagemMiddleLevel.visibility = View.INVISIBLE
        imagemHightLevel.visibility = View.INVISIBLE
        imagemDangerLevel.visibility = View.INVISIBLE

        activity.text = msgSys
        activity.visibility = View.VISIBLE

        notifications.text = msgNot
        notifications.visibility = View.VISIBLE

        textAux1.visibility = View.VISIBLE
        textAux2.visibility = View.VISIBLE
    }

    // conectando no servidor e verificando status

    private fun verifyStatus() = Thread {
        // processo em paralelo

//        val url = URL("https://api.thingspeak.com/channels/1753394/feeds.json?results=500")

        val url = URL("http://victor.jrcassa.com.br/get")

        val conn = url.openConnection() as HttpURLConnection

        try {

            // captando o valor do json

            val data = conn.inputStream.bufferedReader().readText()

            val obj = JSONObject(data)

//            if (obj != null){
                val levelFinal = obj.getString("nivel")

                if(levelFinal == "100"){
                    level = "4"
                } else if(levelFinal == "90"){
                    level = "3"
                } else if(levelFinal == "50"){
                    level = "2"
                } else if(levelFinal == "0"){
                    level = "1"
                } else {
                    level = "0"
                }



////          executando apresentação do nível
            runOnUiThread {

                if (level == "0"){
                    errorStatus()
                } else if (level == "1"){
                    initialStatus()
                } else if (level == "2"){
                    middleLevel()
                } else if (level == "3"){
                    highLevel()
                } else if (level == "4"){
                    internalLevel()
                }
            }
        } finally {
            conn.disconnect()
        }
    }.start()

//    teste

//    private fun verifyStatus() {
//
//        if (level == "0") {
//            errorStatus()
//        } else if (level == "1") {
//            initialStatus()
//        } else if (level == "2") {
//            middleLevel()
//        } else if (level == "3") {
//            highLevel()
//        } else if (level == "4") {
//            internalLevel()
//        }
//    }
//
//    private fun increment(){
//
//        var levelIncrement = level.toInt()
//
//        if (levelIncrement < 4) {
//            levelIncrement++
//        } else {
//            levelIncrement = 0
//        }
//
//        level = levelIncrement.toString()
//    }
}
