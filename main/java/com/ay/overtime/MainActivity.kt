package com.ay.overtime

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the UI elements
        val hourlyWageInput = findViewById<EditText>(R.id.hourlyWageInput)
        val hoursInput = findViewById<EditText>(R.id.hoursWorked)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val doubleTextView = findViewById<TextView>(R.id.doubleTextView)
        val TotalTextView = findViewById<TextView>(R.id.textTotal)
        val TotalTextViewAfterTax = findViewById<TextView>(R.id.textTotalAfterTax)


        // Set up the button click listener
        calculateButton.setOnClickListener {

            // Get the input from the user and converts to string
            val hourlyWageText = hourlyWageInput.text.toString()
            val hoursInputText = hoursInput.text.toString()

            // Validations and inputs

            if (hourlyWageText.isNotEmpty()) {
                //try block
                try {
                    val hourlyWage = hourlyWageText.toDouble()
                    // Calculates overtime pay
                    val overtimePay = hourlyWage * 1.5
                    val doubleTimePay = hourlyWage * 2
                    // Displays the result
                    resultTextView.text = "Overtime Pay is: $${String.format("%.2f", overtimePay)} hourly"
                    doubleTextView.text = "DoubleTime Pay is: $${String.format("%.2f", doubleTimePay)} hourly"
                } catch (e: NumberFormatException) {
                    resultTextView.text = "Invalid input. Please enter a number."
                }
            } else {
                resultTextView.text = "Please enter your hourly wage."
            }

            // Validations and output of overpay totals and taxable, based off amount worked
            if (hoursInputText.isNotEmpty()) {
                //try block
                try {
                    // new variables that are converted to doubles from string
                    val hourlyWage = hourlyWageText.toDouble()
                    val hoursWorked = hoursInputText.toDouble()

                    // Calculates the overtime pay in total and using averages for taxable amounts based on US average
                    val overtimePayTotal = hourlyWage * 1.5 * hoursWorked
                    val overtimePayTotalAfterTax = (hourlyWage * 1.5 * hoursWorked) - ((hourlyWage * 1.5 * hoursWorked) *0.15)
                    val doubleTimePayTotal = hourlyWage * 2 * hoursWorked
                    val doubleTimePayTotalAfterTax = (hourlyWage * 2 * hoursWorked)  - ((hourlyWage * 2 * hoursWorked) * 0.15 )
                    // Displays the result

                    TotalTextView.text = "Overtime Pay is: $${String.format("%.2f", overtimePayTotal)} Total\nAfter Tax: \$${
                        String.format("%.2f", overtimePayTotalAfterTax)}"
                    TotalTextViewAfterTax.text = "DoubleTime Pay is: $${String.format("%.2f", doubleTimePayTotal)} Total\nAfter Tax: $${String.format("%.2f", doubleTimePayTotalAfterTax)} "
                //catch block
                } catch (e: NumberFormatException) {
                    TotalTextView.text = "Invalid input. Please enter a number."
                    TotalTextViewAfterTax.text = " "
                }
            } else {
                TotalTextView.text = "Please enter the hours you have worked."
                TotalTextViewAfterTax.text = " "
            }
        }
    }
}