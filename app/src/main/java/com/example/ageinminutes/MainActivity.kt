package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)


        DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
            // month starts from zero
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
            val minutes = (year - selectedYear)*364*24*60 +
                    (month - selectedMonth)*30*24*60 +
                    (day- selectedDayOfMonth)*24*60
            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
            tvSelectedDate.setText(selectedDate)
            selectedDateString.setText("Selected Date")
            if (selectedYear<year || (selectedYear==year && selectedMonth<month) ||
                (selectedYear==year && selectedMonth==month && selectedDayOfMonth<day)) {
                tvSelectedDateInMinutes.setText("$minutes")
                selectedDateInMinutesString.setText("Age in minutes")

            }
            else {
                tvSelectedDateInMinutes.setText("Selected birthday is wrong.")
                tvSelectedDateInMinutes.setTextSize(15F)
                selectedDateInMinutesString.setText("")

            }



            // Toast.makeText(this, "Minutes are $minutes", Toast.LENGTH_LONG).show()
        }, year, month, day ).show()
    }


}