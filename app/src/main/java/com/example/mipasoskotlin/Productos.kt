package com.example.mipasoskotlin

import android.content.ContentValues
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mipasoskotlin.db.AdminSQLiteOpenHelper
import java.time.LocalDate

class Productos : AppCompatActivity() {
    lateinit var btnRegistroPro:Button
    lateinit var txtNom:EditText
    lateinit var txtPrecio:EditText
    lateinit var txtCodigo:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_productos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()
        estadoBoton()
    }

    fun cargarR(){
        btnRegistroPro=findViewById(R.id.btnRegistroProducto)
        txtNom = findViewById(R.id.txtNombre)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtCodigo = findViewById(R.id.txtCodigoProducto)
    }

    fun estadoBoton(){
        btnRegistroPro.setOnClickListener{

            val productoCodigo = txtCodigo.text.toString().toInt()
            val productoNombre = txtNom.text.toString()
            val productoPrecio = txtPrecio.text.toString().toDouble()

            // Registrar producto
            registro.put("id_producto", productoCodigo)
            registro.put("nombre", productoNombre)
            registro.put("precio", productoPrecio)
            val db
            db.insert("productos", null,btnRegistroPro)

            // Insertar en tabla venta
            val ventaRegistro = ContentValues()
            ventaRegistro.put("id_producto", productoCodigo)
            ventaRegistro.put("cantidad", 1) // Cantidad ejemplo, puedes modificarlo
            ventaRegistro.put("total", productoPrecio) // Total ejemplo
            ventaRegistro.put("fecha",LocalDate.now().toString())
            db.insert("venta", null, ventaRegistro)

            db.close()
            txtCodigo.setText("")
            txtNom.setText("")
            txtPrecio.setText("")
            Toast.makeText(this, "Se cargaron los datos del producto", Toast.LENGTH_SHORT).show()

        }
    }
}