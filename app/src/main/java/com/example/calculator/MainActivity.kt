package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    private lateinit var txt_calculation: TextView
    private lateinit var txt_result: TextView

    private lateinit var btn_backspace: ImageView

    private lateinit var card_clear: CardView
    private lateinit var percentage: CardView
    private lateinit var card_division: CardView
    private lateinit var card_btn_7: CardView
    private lateinit var card_btn_8: CardView
    private lateinit var card_btn_9: CardView
    private lateinit var card_multiply: CardView
    private lateinit var card_btn_4: CardView
    private lateinit var card_btn_5: CardView
    private lateinit var card_btn_6: CardView
    private lateinit var card_subtract: CardView
    private lateinit var card_btn_1: CardView
    private lateinit var card_btn_2: CardView
    private lateinit var card_btn_3: CardView
    private lateinit var card_add: CardView
    private lateinit var card_zero: CardView
    private lateinit var card_equal: CardView

    var firstNumber = ""
    var secondNumber = ""
    var currentNumber = ""
    var currentOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_calculation = findViewById(R.id.txt_calculation)
        txt_result = findViewById(R.id.txt_result)
        btn_backspace = findViewById(R.id.btn_backspace)
        card_clear = findViewById(R.id.card_clear)
        percentage = findViewById(R.id.percentage)
        card_division = findViewById(R.id.card_division)
        card_btn_7 = findViewById(R.id.card_btn_7)
        card_btn_8 = findViewById(R.id.card_btn_8)
        card_btn_9 = findViewById(R.id.card_btn_9)
        card_multiply = findViewById(R.id.card_multiply)
        card_btn_4 = findViewById(R.id.card_btn_4)
        card_btn_5 = findViewById(R.id.card_btn_5)
        card_btn_6 = findViewById(R.id.card_btn_6)
        card_subtract = findViewById(R.id.card_subtract)
        card_btn_1 = findViewById(R.id.card_btn_1)
        card_btn_2 = findViewById(R.id.card_btn_2)
        card_btn_3 = findViewById(R.id.card_btn_3)
        card_add = findViewById(R.id.card_add)
        card_zero = findViewById(R.id.card_zero)
        card_equal = findViewById(R.id.card_equal)

        initializeButtons()

        btn_backspace.setOnClickListener{
            if (currentNumber.isNotEmpty()){
                currentNumber = currentNumber.substring(0,currentNumber.length - 1)
                updateDisplay()
            }
        }
    }

    private fun initializeButtons() {
        var buttonValues = arrayOf(
            "7","8","9","/",
            "4","5","6","*",
            "1","2","3","-",
            "0","C","=","+",
            "%"
        )

        var buttonIds = arrayOf(
            R.id.card_btn_7, R.id.card_btn_8, R.id.card_btn_9, R.id.card_division,
            R.id.card_btn_4, R.id.card_btn_5, R.id.card_btn_6, R.id.card_multiply,
            R.id.card_btn_1, R.id.card_btn_2, R.id.card_btn_3, R.id.card_subtract,
            R.id.card_zero, R.id.card_clear, R.id.card_equal, R.id.card_add,
            R.id.percentage
        )

        buttonIds.forEachIndexed{index, buttonId ->
            findViewById<CardView>(buttonId).setOnClickListener{
                handleButtonClick(buttonValues[index])
            }
        }
    }

    private fun handleButtonClick(value: String){
        when(value){
            in "0".."9" -> appendDigit(value)

            "+" -> {
                appendOperator("+")
                currentOperator = "+"
            }
            "-" -> {
                appendOperator("-")
                currentOperator = "-"
            }
            "*" -> {
                appendOperator("*")
                currentOperator = "*"
            }
            "/" -> {
                appendOperator("/")
                currentOperator = "/"
            }
            "%" -> {
                appendOperator("%")
                currentOperator = "%"
            }
            "C" -> clearInput()
            "=" -> checkResult()
        }
    }

    private fun clearInput(){
        firstNumber = ""
        secondNumber = ""
        currentNumber = ""
        txt_calculation.setText("")
        txt_result.setText("")
        updateDisplay()
    }

    private fun checkResult(){
        if(firstNumber.isNotEmpty() && currentNumber.isNotEmpty()){
            secondNumber = currentNumber
            var result = performCalculation(currentOperator)
            txt_result.text = result.toString()
            firstNumber = ""
            secondNumber = ""
            currentNumber = ""
        }
    }

    private fun performCalculation(operator: String): Double{
        return when(operator){
            "+" -> firstNumber.toDouble() + secondNumber.toDouble()
            "-" -> firstNumber.toDouble() - secondNumber.toDouble()
            "/" -> firstNumber.toDouble() / secondNumber.toDouble()
            "*" -> firstNumber.toDouble() * secondNumber.toDouble()
            "%" -> firstNumber.toDouble() % secondNumber.toDouble()
            else -> {throw IllegalAccessException("Invalid Operator")}
        }
    }

    private fun percentageCalculation(operator: String){

    }

    private fun appendOperator(operator: String){
        if(currentNumber.isNotEmpty()){
            firstNumber = currentNumber
            currentNumber = ""
        }
        updateDisplay()
    }

    private fun appendDigit(digit:String){
        currentNumber += digit
        updateDisplay()
    }

    private fun updateDisplay(){
        txt_calculation.text = currentNumber
    }

}