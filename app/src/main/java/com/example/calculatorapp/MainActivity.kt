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

    fun calculate(view : View){
        if(hasOperator && lastNum){
            val str : CharSequence = display?.text as CharSequence
            val operatorsList = arrayListOf('+', '-', '*', '/')
            val nums = arrayListOf<String>()
            var currentStr = ""
            var op : Char? = null
            var i = 0

            while(i < str.length){
                if (str[i] in operatorsList){
                    if (str[i] == '-' && currentStr == "") {
                        currentStr += str[i]
                        i++
                    }else {
                        nums.add(currentStr)
                        currentStr = ""
                        op = str[i]
                        i++
                    }
                }else {
                    currentStr += str[i]
                    i++
                }
            }
            nums.add(currentStr)

            var ans = nums[0].toDouble()
            when (op){
                '*' -> ans *= nums[1].toDouble()
                '/' -> if(nums[1].toDouble() != 0.0){
                    ans /= nums[1].toDouble()
                }
                '+' -> ans += nums[1].toDouble()
                '-' -> ans -= nums[1].toDouble()
            }
            if(ans % 1 == 0.0){
                display?.text = ans.toInt().toString()
            }else display?.text = ans.toString()

            hasOperator = false
            lastDecimal = true
            if(ans < 0){
                isNeg = true
            }
        }
    }
}