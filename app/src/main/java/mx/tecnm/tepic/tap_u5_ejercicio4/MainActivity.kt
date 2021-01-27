package mx.tecnm.tepic.tap_u5_ejercicio4

import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var vector = ArrayList<String>()
    var timer = object : CountDownTimer(20000, 500) {
        override fun onFinish() {
            start()
        }

        override fun onTick(millisUntilFinished: Long) {
            mostrarData()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer.start() //Hace que el timer se ejecute en segundo plano


        button.setOnClickListener {
            var cadena = "Nombre:  ${Nombre.text.toString()}\nEdad: ${Edad.text.toString()}" +
                    "\nTelefono:  ${Telefono.text.toString()}";

            vector.add(cadena)
            Toast.makeText(this, "Se capturo con exito", Toast.LENGTH_SHORT).show()
            Nombre.setText(""); Edad.setText(""); Telefono.setText("");
        }

        button2.setOnClickListener {
            var campoPosicion = EditText(this)

            campoPosicion.inputType = InputType.TYPE_CLASS_NUMBER

            AlertDialog.Builder(this).setTitle("Escriba posicion a eliminar").setView(campoPosicion)
                .setPositiveButton("Borrar") { d, i ->
                    eliminar(campoPosicion.text.toString().toInt())
                }
                .setNegativeButton("Cancelar") { d, i ->

                }
                .show()
        }


    }

    fun eliminar(posicion: Int) {
        try {
            vector.removeAt(posicion)
            Toast.makeText(this, "Se capturo con exito", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            AlertDialog.Builder(this).setTitle("Error").setMessage(e.message).show()
        }

    }

    fun mostrarData() {
        var cadena = "Mostrar: \n"

        if (vector.size == 0) {
            textView.setText("Mostrar: \nNo existen datos aun")
            return
        }
        var ultimoIndice = vector.size - 1;
        (0..ultimoIndice).forEach {
            cadena += "Posicion: ${it}\n${vector.get(it)}\n\n"
        }
        textView.setText(cadena)
    }
}