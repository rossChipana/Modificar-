package com.example.mipasoskotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Home : AppCompatActivity() {

    lateinit var btnIvaPro: CardView
    lateinit var btnProducto: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()

        estadoBoton()
    }

    fun cargarR(){
        btnIvaPro = findViewById(R.id.btnIvaProducto)
        btnProducto = findViewById(R.id.btnProducto)
    }

    fun estadoBoton(){
        btnIvaPro.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        btnProducto.setOnClickListener{
            val i = Intent(this, Productos::class.java)
            startActivity(i)
        }
    }
}