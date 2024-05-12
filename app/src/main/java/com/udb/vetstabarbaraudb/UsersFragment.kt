package com.udb.vetstabarbaraudb

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


class UsersFragment : Fragment() {
    private val users =
        arrayOf(
            arrayOf("Usuario1", "Especialidad1"),
            arrayOf("Usuario2", "Especialidad2"),
            arrayOf("Usuario3", "Especialidad3"),
            arrayOf("Usuario4", "Especialidad4"),
            arrayOf("Usuario5", "Especialidad5"),
            arrayOf("Usuario6", "Especialidad6"),
            arrayOf("Usuario7", "Especialidad7"),
            arrayOf("Usuario8", "Especialidad8")

        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.updateToolbarTitle("Usuarios")

        // Se obtiene el color de la línea desde los recursos
        val dividerColor = ContextCompat.getColor(requireContext(), R.color.dark_grey)
        // Buscar el TableLayout en el diseño del fragmento
        val tableLayout = view.findViewById<TableLayout>(R.id.tableLayout)

        // Iterar sobre el array de usuarios y agregar cada usuario a la tabla
        for (user in users) {
            val row = TableRow(requireContext())
            val layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            row.layoutParams = layoutParams

            // Agregar datos de usuario a la fila
            for (userData in user) {
                val textView = TextView(requireContext())
                textView.text = userData
                textView.setPadding(10, 10, 10, 10)
                row.addView(textView)
            }
            // Agregar el botón de "Editar" a la fila
            val editButton = Button(requireContext())
            editButton.text = "Editar"
            editButton.setBackgroundResource(R.drawable.rounded_button_background)
            editButton.setTextColor(Color.WHITE)
            editButton.setOnClickListener {
                // lógica para editar el usuario
                showEditAlertDialog()
            }
            row.addView(editButton)


            // Agregar la fila a la tabla
            tableLayout.addView(row)

            val divider = View(requireContext())
            val dividerLayoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                1 // Altura de la línea en píxeles
            )
            dividerLayoutParams.setMargins(0, 15, 0, 15) // Márgenes para la línea (opcional)
            divider.layoutParams = dividerLayoutParams
            divider.setBackgroundColor(dividerColor)
            tableLayout.addView(divider)
        }
    }
    private fun showEditAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.apply {
            setTitle("Editar Usuario")
            setMessage("falta implementar la lógica para editar el usuario.")
            setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}