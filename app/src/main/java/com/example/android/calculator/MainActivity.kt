package com.example.android.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView
    private lateinit var five: TextView
    private lateinit var six: TextView
    private lateinit var seven: TextView
    private lateinit var eight: TextView
    private lateinit var nine: TextView
    private lateinit var zero: TextView
    private lateinit var allClear: TextView
    private lateinit var divide: TextView
    private lateinit var multiply: TextView
    private lateinit var sub: TextView
    private lateinit var add: TextView
   private lateinit var equals: TextView
    private lateinit var decimal: TextView
    private lateinit var modulo: TextView
    private lateinit var sigChange: TextView
    private lateinit var backSpace: ImageView
    private lateinit var result: TextView
    private lateinit var expression: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        decimal = findViewById(R.id.decimal)
        modulo = findViewById(R.id.modulo)
        divide = findViewById(R.id.divide)
        multiply = findViewById(R.id.multi)
        add = findViewById(R.id.addition)
        sub = findViewById(R.id.sub)
        equals = findViewById(R.id.equals)
        sigChange = findViewById(R.id.signChange)
        allClear = findViewById(R.id.AC)
        backSpace = findViewById(R.id.backspace)
        result = findViewById(R.id.result)
        expression = findViewById(R.id.realTimeCal)

        zero.setOnClickListener {
            appendText("0" , true)
        }

        one.setOnClickListener {
            appendText("1", true)
        }
        two.setOnClickListener {
            appendText("2", true)
        }
        three.setOnClickListener {
            appendText("3", true)
        }
        four.setOnClickListener {
            appendText("4", true)
        }
        five.setOnClickListener {
            appendText("5", true)
        }
        six.setOnClickListener {
            appendText("6", true)
        }
        seven.setOnClickListener {
            appendText("7", true)
        }
        eight.setOnClickListener {
            appendText("8", true)
        }
        nine.setOnClickListener {
            appendText("9", true)
        }



        modulo.setOnClickListener {
            appendText("%", false)
        }
        divide.setOnClickListener {
            appendText("/", false)
        }
        multiply.setOnClickListener {
            appendText("*", false)
        }
        add.setOnClickListener {
            appendText("+", false)
        }
        sub.setOnClickListener {
            appendText("-", false)
        }
        modulo.setOnClickListener {
            appendText("%" , false)
        }


        decimal.setOnClickListener {



                if ( expression.text.isNotEmpty()) {
                    appendText(".", true)

                }


        }


        backSpace.setOnClickListener{
            result.text = ""
            result.hint = ""
            val value = expression.text
            if(value.isNotEmpty()){
                expression.text = value.substring(0,value.length-1)
            }
        }

        allClear.setOnClickListener {
            expression.text = ""
            result.text = ""
            result.hint = ""
        }

        sigChange.setOnClickListener {
              result.text = ""
            result.hint = ""

            if(expression.text.isNotEmpty() && expression.text[0] == '-'){
                expression.text = expression.text.substring(1)
                result.text = expression.text

            }else {
                expression.text = "-${expression.text}"
                result.text = expression.text
            }

        }

        equals.setOnClickListener {
              // exception handling --> 1/0 = error
            try {
                val  expr = ExpressionBuilder(expression.text.toString()).build()
                val answer = expr.evaluate()
                result.text = answer.toString()
            }catch (e:Exception){
                result.text = "ERROR"
            }
        }


    }

    private fun appendText(value: String, toBeCleared: Boolean) {

        if (result.text != "") {
            expression.text = ""
        }

        if (toBeCleared) {
            // value is a number
            result.text = ""
            expression.append(value)
        } else {
            // value is a operator
            expression.append(result.text)
            expression.append(value)
            result.text = ""

        }

        result.hint = expression.text

    }

}