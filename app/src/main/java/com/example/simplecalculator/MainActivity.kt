package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.udojava.evalex.Expression

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val textButtonsIds = listOf(
        R.id.b00,
        R.id.b0,
        R.id.b1,
        R.id.b2,
        R.id.b3,
        R.id.b4,
        R.id.b5,
        R.id.b6,
        R.id.b7,
        R.id.b8,
        R.id.b9,
        R.id.comma,
        R.id.plus,
        R.id.minus,
        R.id.multiply,
        R.id.divide,
        R.id.right_brace,
        R.id.left_brace
    )
    private lateinit var expressionText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("KEK", "amount = ${textButtonsIds.size}")
        textButtonsIds.forEach { id ->
            findViewById<View>(id).setOnClickListener(this)
        }
        expressionText = findViewById(R.id.expression)
        findViewById<View>(R.id.equals).setOnClickListener {
            expressionText.text = calculateExpression(expressionText.text.toString())
        }
    }

    private fun calculateExpression(text: String): String {
        return formatToText(
            Expression(formatToExpression(text))
                .eval()
                .toPlainString()
        )
    }

    private fun formatToText(expression: String): String {
        return expression
            .replace('/', '÷')
            .replace('*', '×')
            .replace('.', ',')
    }

    private fun formatToExpression(text: String): String {
        return text
            .replace('÷', '/')
            .replace('×', '*')
            .replace(',', '.')
    }

    override fun onClick(v: View) {
        if (textButtonsIds.contains(v.id)) {
            expressionText.text = "${expressionText.text}${(v as TextView).text}"
        }
    }
}