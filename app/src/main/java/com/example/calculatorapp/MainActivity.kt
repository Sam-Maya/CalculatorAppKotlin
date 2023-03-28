package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var display : TextView? = null
    private var lastNum = false
    private var lastDecimal = false
    private var hasOperator = false
    private var isNeg = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

    }


    fun onDigit(view: View){
        display?.append((view as Button).text)
        lastNum = true
    }

    fun onClr(view : View){
        display?.text = null
        lastNum = false
        lastDecimal = false
        hasOperator = false
        isNeg = false
    }

    fun onDecimal(view : View){
        if (!lastDecimal) {
            display?.append((view as Button).text)
            lastDecimal = true
            lastNum = false
        }
    }

    fun onOperator(view : View){
        if (!lastNum && !lastDecimal && (view as Button).text == "-" && !isNeg){
            isNeg = true
            display?.append((view as Button).text)
        } else if( !hasOperator && lastNum ) {
            display?.let {
                if (it.length() > 0) {
                    display?.append((view as Button).text)
                    lastDecimal = false
                    lastNum = false
                    hasOperator = true
                    isNeg = false
                }
            }
        }
    }
}