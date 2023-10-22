package com.duongdk.hust.it4785.simplecaculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var solutionTextView: TextView
    private var operand1: Int = 0
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        solutionTextView = findViewById(R.id.solution_tv)

        // Gắn sự kiện cho các nút số
        val numberButtons = arrayOf(
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3,
            R.id.button_4, R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9
        )

        for (buttonId in numberButtons) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { onNumberButtonClick(button) }
        }

        // Gắn sự kiện cho các nút toán tử
        val operatorButtons = arrayOf(
            R.id.button_plus, R.id.button_minus, R.id.button_multiply, R.id.button_divide
        )

        for (buttonId in operatorButtons) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { onOperatorButtonClick(button) }
        }

        // Gắn sự kiện cho các nút chức năng
        val functionButtons = arrayOf(
            R.id.button_ce, R.id.button_c, R.id.button_bs, R.id.button_equals
        )

        for (buttonId in functionButtons) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { onFunctionButtonClick(button) }
        }
    }

    // Xử lý sự kiện khi nhấn nút số
    private fun onNumberButtonClick(button: View) {
        val number = (button as Button).text.toString()
        val currentText = solutionTextView.text.toString()

        if (currentText == "0") {
            solutionTextView.text = number
        } else {
            solutionTextView.text = currentText + number
        }
    }

    // Xử lý sự kiện khi nhấn nút toán tử
    private fun onOperatorButtonClick(button: View) {
        operand1 = solutionTextView.text.toString().toInt()
        operator = (button as Button).text.toString()
        solutionTextView.text = "0"
    }

    // Xử lý sự kiện khi nhấn nút chức năng
    private fun onFunctionButtonClick(button: View) {
        when (button.id) {
            R.id.button_ce -> {
                solutionTextView.text = "0"
            }
            R.id.button_c -> {
                solutionTextView.text = "0"
                operand1 = 0
                operator = null
            }
            R.id.button_bs -> {
                val currentText = solutionTextView.text.toString()
                if (currentText.length > 1) {
                    solutionTextView.text = currentText.dropLast(1)
                } else {
                    solutionTextView.text = "0"
                }
            }
            R.id.button_equals -> {
                if (operator != null) {
                    val operand2 = solutionTextView.text.toString().toInt()
                    val result = performOperation(operand1, operand2, operator!!)
                    solutionTextView.text = result.toString()
                    operand1 = result
                    operator = null
                }
            }
        }
    }
    private fun performOperation(operand1: Int, operand2: Int, operator: String): Int {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "x" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> operand1
        }
    }
}