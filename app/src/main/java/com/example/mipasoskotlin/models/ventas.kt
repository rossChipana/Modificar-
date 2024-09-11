package com.example.mipasoskotlin.models

import java.time.LocalDate

class ventas (nombreProducto: String,cantidad: Int,precioUnitario: Double,fecha: LocalDate){
    private var nombreProducto :String=nombreProducto
    private var cantidad: Int=cantidad
    private var precioUnitario: Double=precioUnitario
    private var fecha:LocalDate=fecha



        // get
        fun getNombreProducto(): String {
            return nombreProducto
        }

        // set
        fun setNombreProducto(nombreProducto: String) {
            this.nombreProducto = nombreProducto
        }

        // get
        fun getCantidad(): Int {
            return cantidad
        }

        // set
        fun setCantidad(cantidad: Int) {
            this.cantidad = cantidad
        }

        // get
        fun getPrecioUnitario(): Double {
            return precioUnitario
        }

        // set
        fun setPrecioUnitario(precioUnitario: Double) {
            this.precioUnitario = precioUnitario
        }

        // get
        fun getFecha(): LocalDate {
            return fecha
        }

        // set
        fun setFecha(fecha: LocalDate) {
            this.fecha = fecha
        }
      fun calcularTotal(): Double {
        return cantidad * precioUnitario
       }

    }