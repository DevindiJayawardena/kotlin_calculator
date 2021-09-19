package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //this is onClick event
    fun onDigit(view: View){
        //Toast.makeText(this, "Button works!", Toast.LENGTH_SHORT).show()
        //var tvInput = findViewById<TextView>(R.id.tvInput)
        tvInput.append((view as Button).text)
        lastNumeric = true

        //if(tvInput.text.contains("1"))
            //tvInput.text = "Ha haa"
    }

    fun onClear(view: View) {
        tvInput.text = ""
        lastNumeric = false //here we set these both to 'false' bcz when it clears we want the initial state of our app
        lastDot = false
    }

    fun onDecimalPoint(view: View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View){
        if(lastNumeric) {
            var tvValue = tvInput.text.toString()
            var prefix = ""

            try{
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1) //-215 -> '-' is starts with index 0, '2' starts with index 1
                }

                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    //99-15 So, splitValue[0] = 99, splitValue[1] = 15

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }

                else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    //99-15 So, splitValue[0] = 99, splitValue[1] = 15

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }

                else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    //99-15 So, splitValue[0] = 99, splitValue[1] = 15

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

                else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]
                    //99-15 So, splitValue[0] = 99, splitValue[1] = 15

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
            }catch(e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    //this function is private because it's going to be required in here in our main activity.
    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0,result.length - 2) //getting rid of the last 2 characters (last two of 99.0 is '.0')
        }
        return value
    }


    fun onOperator(view: View){
        if (lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }


    private fun isOperatorAdded(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}

