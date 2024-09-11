package com.example.mipasoskotlin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mipasoskotlin.db.AdminSQLiteOpenHelper
import com.example.mipasoskotlin.models.Productos

class MainActivity : AppCompatActivity() {
    lateinit var btnCalcular: Button
    lateinit var btnBuscar: Button
    lateinit var txtPrecio: EditText
    lateinit var txtNombre:EditText
    lateinit var tvResul: TextView
    lateinit var spLista:Spinner
    lateinit var listPro:ListView

    lateinit var productosList:MutableList<String>
    lateinit var adapterListView:ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //codigo
        cargarR()
        estadoOnclick()
        cargarListaProducto()

        //cargar lista de datos en spinner
        val listaPaises = arrayOf("USA", "BOL", "ESP")
        val adaptador1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaPaises)
        spLista.adapter = adaptador1


    }
    fun cargarR (){
       // btnCalcular = findViewById(R.id.btnCalcularIVA)
       // txtPrecio = findViewById(R.id.txtProducto)
        tvResul = findViewById(R.id.tvResultado)
        //spLista = findViewById(R.id.spPaises)
       // listPro = findViewById(R.id.listaProductos)
        txtNombre = findViewById(R.id.txtNombre)
       // btnBuscar = findViewById(R.id.btnbuscarProducto)
    }

    fun estadoOnclick(){
        btnCalcular.setOnClickListener(){

            val laptop = Productos(txtNombre.text.toString(), txtPrecio.text.toString().toDouble())
            //val datosRe : Double = laptop.calIVA()

            when(spLista.selectedItem.toString()){
                "USA"-> productosList.add(laptop.getnombre()+ ", " +laptop.getprecio()+ "IVA: "+laptop.calIVA(0.03).toString())
                "BOL"-> productosList.add(laptop.getnombre() + ", " +laptop.getprecio()+ "IVA: "+laptop.calIVA(0.13).toString())
                "ESP"-> productosList.add(laptop.getnombre()+ ", " +laptop.getprecio()+ "IVA: "+laptop.calIVA(0.05).toString())
            }
            listPro.adapter=adapterListView

        }

        btnBuscar.setOnClickListener{
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val query = "SELECT nombre,precio FROM productos WHERE id_productos = ?"
            val fila = bd.rawQuery( query,arrayOf(txtNombre.text.toString()))
            if (fila.moveToFirst()) {
                txtNombre.setText(fila.getString(0))
                txtPrecio.setText(fila.getString(1))
            } else {
                Toast.makeText(this, "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show()
            }
                bd.close()
        }
    }

    fun cargarListaProducto(){
        val adminsql = AdminSQLiteOpenHelper(this,"administracion",null,1)
        val db = adminsql.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM venta", null)
        val listaVentas = mutableListOf<String>()

        if (cursor.moveToFirst()) {
            do {
                val venta = cursor.getString(1) + " - " + cursor.getString(2) + " - " + cursor.getString(3) + " - " + cursor.getString(4)
                listaVentas.add(venta)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        adapterListView = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaVentas)
        listPro.adapter = adapterListView
    }