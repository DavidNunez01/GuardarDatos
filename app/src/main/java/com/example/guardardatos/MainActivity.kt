package com.example.guardardatos

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private val clave = "VALOR" //Variable global
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Variables Para trabajar los views en la app - Recuperar valores
        val rut = findViewById<EditText>(R.id.etxtRUT)
        val nombre = findViewById<EditText>(R.id.etxtNombre)
        val email = findViewById<EditText>(R.id.etxtEmail)
        val guardar = findViewById<Button>(R.id.btnGuardar)
        val buscar = findViewById<Button>(R.id.btnBuscar)

        //variable para guardar los valores
        //Debe usarse el nombre de la variable que se creo para guardar los valores
        //Recuperar prefencias del sistema
        val sp = getSharedPreferences(clave, Context.MODE_PRIVATE)

        //Guardar los valores en la variable
        guardar.setOnClickListener {
            val editor = sp.edit() //Modo edicion de las preferencias
            //guardar los datos usando el formato 'key: value'
            editor.putString(rut.text.toString()+"name", nombre.text.toString())
            editor.putString(rut.text.toString()+"mail", email.text.toString())
            editor.apply()
            Toast.makeText(this, "Asignando Valores", Toast.LENGTH_LONG).show()
            //Dejar en blanco los campos
            rut.setText("")
            nombre.setText("")
            email.setText("")
        }

        //Creacion de listener
        buscar.setOnClickListener {
            //Recuperar el mail usando la key nombre
            val correo = sp.getString(rut.text.toString()+"mail", "")

            if (correo!!.isNotEmpty()){
                email.setText(correo) //Print correo en el campo del editor
            }
        }
    }

    /* La funcion debe crearse fuera del 'oncreate' */
    fun Alerta(mensaje: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Preferencias")
        builder.setMessage(mensaje)
        builder.create().show()
    }
}