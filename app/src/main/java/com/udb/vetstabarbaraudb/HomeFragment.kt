package com.udb.vetstabarbaraudb

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


class HomeFragment : Fragment() {

    private lateinit var tableLayout: TableLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        tableLayout = view.findViewById(R.id.tableLayout)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.updateToolbarTitle("Inicio")

        val dividerColor = ContextCompat.getColor(requireContext(), R.color.dark_grey)
        // Ejemplo de datos de pacientes
        val patients = listOf(
            Patient("Manchas", "Veterinario 1", "10:00"),
            Patient("Pecorino", "Veterinario 2", "11:00"),
            Patient("Monkey D. Zeus", "Veterinario 3", "12:00"),
            Patient("Claro", "Veterinario 1", "10:00"),
            Patient("Chucho", "Veterinario 2", "11:00"),
            Patient("Michi", "Veterinario 3", "12:00"),
            Patient("Babotas", "Veterinario 1", "10:00"),
            Patient("Carro ultimo modelo", "Veterinario 2", "11:00"),
            Patient("Caray", "Veterinario 3", "12:00")
            // Agrega más pacientes según sea necesario
        )

        // Mostrar los últimos 6 o 5 pacientes, según la cantidad de pacientes
        val startIndex = patients.size - 6

        for (i in startIndex until patients.size) {
            val patient = patients[i]
            val row = TableRow(requireContext())
            val layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 16)
            row.layoutParams = layoutParams

            val nameColumn = createTextView(patient.name)
            val veterinarianColumn = createTextView(patient.veterinarian)
            val timeColumn = createTextView(patient.time)

            row.addView(nameColumn)
            row.addView(veterinarianColumn)
            row.addView(timeColumn)


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

        // Configuración de los botones
        view.findViewById<Button>(R.id.btnViewHistory).setOnClickListener {
            // Navegar al Fragmento RecordFragment
            val recordFragment = RecordFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, recordFragment)
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.btnRegisterPatient).setOnClickListener {
            // Lógica para registrar un paciente
        }

        view.findViewById<Button>(R.id.btnScheduleAppointment).setOnClickListener {
            // Lógica para programar una cita
        }
    }

    private fun createTextView(text: String): TextView {
        val textView = TextView(requireContext())
        textView.text = text
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        params.weight = 1f
        textView.layoutParams = params
        return textView
    }

    data class Patient(val name: String, val veterinarian: String, val time: String)
}