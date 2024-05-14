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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class RecordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.updateToolbarTitle("Historial")

        // Configuración del botón para ver citas de hoy
        view.findViewById<Button>(R.id.btnTodayAppointments).setOnClickListener {
            // Acción para ver citas de hoy
        }

        // Configuración del botón para ver historial completo
        view.findViewById<Button>(R.id.btnFullHistory).setOnClickListener {
            // Acción para ver historial completo
        }

        // Mostrar datos en la primera tabla (citas de hoy)
        val tableLayout1 = view.findViewById<TableLayout>(R.id.tableLayout1)
        showAppointments(tableLayout1, getDummyAppointments().filter { isSameDay(it.time, Date()) })

        // Mostrar datos en la segunda tabla (citas anteriores)
        val tableLayout2 = view.findViewById<TableLayout>(R.id.tableLayout2)
        showAppointments(tableLayout2, getDummyAppointments().filter { it.time.before(Date()) && !isSameDay(it.time, Date()) })
    }

    private fun showAppointments(tableLayout: TableLayout, appointments: List<Appointment>) {

        val dividerColor = ContextCompat.getColor(requireContext(), R.color.dark_grey)


        // Ordenar citas por hora, de la más reciente a la más antigua
        val sortedAppointments = appointments.sortedByDescending { it.time }

        // Mostrar solo las últimas 5 citas
        val appointmentsToShow = sortedAppointments.take(5)

        // Mostrar cada cita en una fila de la tabla
        appointmentsToShow.forEach { appointment ->
            val row = TableRow(requireContext())

            // Agregar datos de la cita a la fila
            val patientTextView = TextView(requireContext()).apply {
                text = appointment.patient
                setPadding(24, 8, 64, 8)
            }
            row.addView(patientTextView)

            val veterinarianTextView = TextView(requireContext()).apply {
                text = appointment.veterinarian
                setPadding(24, 8, 64, 8)
            }
            row.addView(veterinarianTextView)

            val timeTextView = TextView(requireContext()).apply {
                val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                text = dateFormat.format(appointment.time)
                setPadding(16, 8, 16, 8)
            }
            row.addView(timeTextView)

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


    private fun isSameDay(date1: Date, date2: Date): Boolean {
        val cal1 = Calendar.getInstance().apply { time = date1 }
        val cal2 = Calendar.getInstance().apply { time = date2 }
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)
    }

    private fun getDummyAppointments(): List<Appointment> {
        val currentDate = Date()

        // Fecha y hora específica (año, mes, día, hora, minuto)
        val specificDate = Date(2023 - 1900, 5 - 1, 15, 10, 0) // 15 de mayo de 2024, 10:00 AM

        return listOf(
            Appointment("Paciente 1", "Veterinario 1", currentDate),
            Appointment("Paciente 2", "Veterinario 2", currentDate),
            Appointment("Paciente 3", "Veterinario 3", specificDate),
            Appointment("Paciente 4", "Veterinario 4", specificDate),
            Appointment("Paciente 5", "Veterinario 5", specificDate)
        )
    }

}
data class Appointment(val patient: String, val veterinarian: String, val time: Date)