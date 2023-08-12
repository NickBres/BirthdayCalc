package com.example.birthdaycalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataLay = findViewById<LinearLayout>(R.id.dataLayout)
        dataLay.visibility = View.GONE

        val button = findViewById<Button>(R.id.calcBtn)
        button.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dataPick = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val date = Calendar.getInstance()
                date.set(year, month, dayOfMonth)

                // Calculations
                val tillMin = calcMinutesTillDate(date)
                val tillHour = calcHoursTillDate(date)
                val tillDay = calcDaysTillDate(date)
                val tillMonth = calcMonthsTillDate(date)
                val tillYear = calcYearsTillDate(date)

                // Find the text views
                val dateText = findViewById<TextView>(R.id.date)
                val minTillDateText = findViewById<TextView>(R.id.minutesTillDate)
                val hourTillDateText = findViewById<TextView>(R.id.hoursTillDate)
                val dayTillDateText = findViewById<TextView>(R.id.daysTillDate)
                val monthTillDateText = findViewById<TextView>(R.id.monthTillDate)
                val yearTillDateText = findViewById<TextView>(R.id.yearTillDate)

                // Set the text views
                dateText.text = "$dayOfMonth/${month+1}/$year"
                minTillDateText.text = "$tillMin"
                hourTillDateText.text = "$tillHour"
                dayTillDateText.text = "$tillDay"
                monthTillDateText.text = "$tillMonth"
                yearTillDateText.text = "$tillYear"

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

            dataPick.datePicker.maxDate = Date().time // Set the max date to today so that the user can't select a date in the future
            dataPick.show()
            dataLay.visibility = View.VISIBLE
        }

    }

    fun calcMinutesTillDate(date : Calendar): Int {
        val now = Calendar.getInstance()
        val diff = now.timeInMillis - date.timeInMillis
        return (diff / 1000 / 60).toInt()
    }

    fun calcHoursTillDate(date : Calendar): Int {
        return calcMinutesTillDate(date) / 60
    }

    fun calcDaysTillDate(date : Calendar): Int {
        return calcHoursTillDate(date) / 24
    }

    fun calcMonthsTillDate(date : Calendar): Int {
        return calcDaysTillDate(date) / 30
    }

    fun calcYearsTillDate(date : Calendar): Int {
        return calcMonthsTillDate(date) / 12
    }
}

