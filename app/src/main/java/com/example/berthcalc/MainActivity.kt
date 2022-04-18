package com.example.berthcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

private val main = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var firstInput: EditText
    private lateinit var secondInput: EditText
    private lateinit var thirdInput: EditText

    private var valueOne: Double = 0.0
    private var valueTwo: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Input fields
        firstInput = findViewById(R.id.first_field)
        secondInput = findViewById(R.id.second_field)
        thirdInput = findViewById(R.id.third_field)

        firstInput.isFocusable = false
        secondInput.isFocusable = false
        thirdInput.isFocusable = false



        //buttons
        //numbers
        val buttonOne: Button = findViewById(R.id.one_button)
        val buttonTwo: Button = findViewById(R.id.two_button)
        val buttonThree: Button = findViewById(R.id.three_button)
        val buttonFour: Button = findViewById(R.id.four_button)
        val buttonFive: Button = findViewById(R.id.five_button)
        val buttonSix: Button = findViewById(R.id.six_button)
        val buttonSeven: Button = findViewById(R.id.seven_button)
        val buttonEight: Button = findViewById(R.id.eight_button)
        val buttonNine: Button = findViewById(R.id.nine_button)
        val buttonZero: Button = findViewById(R.id.zero_button)
        val buttonDot: Button = findViewById(R.id.dot_button)

        //operations
        val plus: Button = findViewById(R.id.plus_button)
        val minus: Button = findViewById(R.id.minus_button)
        val divide: Button = findViewById(R.id.divide_button)
        val multiply: Button = findViewById(R.id.multiply_button)
        val equal: Button = findViewById(R.id.equal_button)

        val numButtonArray: List<Button> = listOf(buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine, buttonZero, buttonDot)
        val operationButtonArray: List<Button> = listOf(plus, minus, divide, multiply)

        for (button in operationButtonArray) {
            button.setOnClickListener {

                if (thirdInput.text.isNotEmpty()){

                    firstInput.text = thirdInput.text
                    secondInput.text.clear()
                    thirdInput.text.clear()

                    secondInput.isFocusable = true
                    firstInput.isFocusable = false

                    val buttonOperation = it as Button
                    findViewById<TextView>(R.id.operation_label).text = buttonOperation.text

                }else {
                    val buttonOperation = it as Button
                    findViewById<TextView>(R.id.operation_label).text = buttonOperation.text
                    secondInput.isFocusable = true
                    firstInput.isFocusable = false
                }


            }
        }

        for (button in numButtonArray) {
            firstInput.isFocusable = true
            button.setOnClickListener {
                val buttonName =  it as Button
                if (thirdInput.text.isEmpty()) {
                    if (firstInput.isFocusable) {
                        firstInput.append(buttonName.text.toString())

                    }else if (secondInput.isFocusable) {
                        secondInput.append(buttonName.text.toString())

                    }
                }else {
                    firstInput.setText(buttonName.text)
                    secondInput.text.clear()
                    thirdInput.text.clear()

                    firstInput.isFocusable = true
                    secondInput.isFocusable = false
                    thirdInput.isFocusable = false

                }



//                Log.d(main, buttonName.text.toString().toDouble().toString())
            }
        }

        equal.setOnClickListener {

            if (firstInput.text.isNotEmpty() && secondInput.text.isNotEmpty()) {
                valueOne = firstInput.text.toString().toDouble()

                valueTwo = secondInput.text.toString().toDouble()


                val answer = when (this.operation_label.text) {
                    "x" -> valueOne * valueTwo
                    "/" -> valueOne / valueTwo
                    "+" -> valueOne + valueTwo
                    "-" -> valueOne - valueTwo
                    else -> 0.0
                }
                firstInput.isFocusable = false
                secondInput.isFocusable = false
                thirdInput.isFocusable = true

                thirdInput.setText(answer.toString())
            }
        }

        clear_button.setOnClickListener {
            firstInput.isFocusable = true
            secondInput.isFocusable = false
            thirdInput.isFocusable = false

            firstInput.text.clear()
            secondInput.text.clear()
            thirdInput.text.clear()

            valueOne = 0.0
            valueTwo = 0.0
        }

        neg_button.setOnClickListener {

            val textViewList: List<TextView> = listOf(firstInput, secondInput, thirdInput)

            for (active in textViewList) {
                if (active.text.isNotEmpty()) {

                    if (active.isFocusable){
                        var some = active.text.toString().toDouble() * -1
                        active.text = some.toString()
                    }
                }

            }

        }
    }
}