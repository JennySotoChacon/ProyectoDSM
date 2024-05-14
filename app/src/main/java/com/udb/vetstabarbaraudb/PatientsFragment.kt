package com.udb.vetstabarbaraudb

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat


class PatientsFragment : Fragment() {
    private val patients =
        arrayOf(
            arrayOf("Paciente1", "Detalles..."),
            arrayOf("Paciente2", "Detalles..."),
            arrayOf("Paciente3", "Detalles..."),
            arrayOf("Paciente4", "Detalles..."),
            arrayOf("Paciente5", "Detalles..."),
            arrayOf("Paciente6", "Detalles..."),
            arrayOf("Paciente7", "Detalles..."),
            arrayOf("Paciente8", "Detalles...")

        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.updateToolbarTitle("Pacientes")
        // Se obtiene el color de la línea desde los recursos
        val dividerColor = ContextCompat.getColor(requireContext(), R.color.dark_grey)
        // Buscar el TableLayout en el diseño del fragmento
        val tableLayout = view.findViewById<TableLayout>(R.id.tableLayout)

        // Iterar sobre el array de usuarios y agregar cada usuario a la tabla
        for (patient in patients) {
            val row = TableRow(requireContext())
            val layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            row.layoutParams = layoutParams

            // Agregar datos de usuario a la fila
            for (patientData in patient) {
                val textView = TextView(requireContext())
                textView.text = patientData
                textView.setPadding(10, 10, 10, 10)
                row.addView(textView)
            }
            // Agregar el botón de "Editar" a la fila
            val editButton = Button(requireContext())
            editButton.text = "Información"
            editButton.setBackgroundResource(R.drawable.rounded_button_background)
            editButton.setTextColor(Color.WHITE)
            val buttonHeight = resources.getDimensionPixelSize(R.dimen.button_height_fixed)
            editButton.height = buttonHeight
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
            setMessage("falta implementar la lógica para ver la información.")
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