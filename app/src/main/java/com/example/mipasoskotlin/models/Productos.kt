package com.example.mipasoskotlin.models

class Productos constructor(nombre:String, precio:Double,codigo: Int) {

    private var nombre:String = nombre
    private var precio:Double = precio
    private var codigo: Int=codigo
//get
fun getnombre(): String {
    return nombre
}
    fun getprecio(): Double {
        return precio
    }

fun getcodigo():Int{
    return codigo
}
    //set

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }
    fun setPrecio(precio: Double) {
        this.precio = precio
    }
    fun setCodigo(codigo: Int) {
        this.codigo = codigo
    }


   /* fun calIVA(iva:Double): Double{
        val total:Double =precio * iva
        return total
    }*/


}