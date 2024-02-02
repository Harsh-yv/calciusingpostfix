package com.example.empty

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            var tvOne= findViewById<Button>(R.id.btn_one)
            var tvTwo= findViewById<Button>(R.id.btn_two)
            var tvThree= findViewById<Button>(R.id.btn_three)
            var tvFour= findViewById<Button>(R.id.btn_four)
            var tvFive= findViewById<Button>(R.id.btn_five)
            var tvSix= findViewById<Button>(R.id.btn_six)
            var tvSeven= findViewById<Button>(R.id.btn_seven)
            var tvEight= findViewById<Button>(R.id.btn_eight)
            var tvNine= findViewById<Button>(R.id.btn_nine)
            var tvZero= findViewById<Button>(R.id.btn_zero)
            var tvPlus= findViewById<Button>(R.id.btn_plus)
            var tvMinus= findViewById<Button>(R.id.btn_sub)
            var tvMul= findViewById<Button>(R.id.btn_mul)
            var tvDivide= findViewById<Button>(R.id.btn_slash)
            var tvPercent= findViewById<Button>(R.id.btn_percent)
            var tvlb= findViewById<Button>(R.id.btn_lb)
            var tvrb= findViewById<Button>(R.id.btn_rb)
            var tvAllclear= findViewById<Button>(R.id.btn_ac)
            var tvEquals= findViewById<Button>(R.id.btn_equal)
            var tvvalue= findViewById<TextView>(R.id.calcs)
            var tvResult= findViewById<TextView>(R.id.result)
            var tvBack =findViewById<Button>(R.id.btn_back)
            var value= StringBuilder("")
            /*Number Buttons*/

            tvOne.setOnClickListener {
                value.append("1")
                tvvalue.text=value
            }

            tvTwo.setOnClickListener {
                value.append("2")
                tvvalue.text=value
            }

            tvThree.setOnClickListener {
                value.append("3")
                tvvalue.text=value
            }
            tvFour.setOnClickListener {
                value.append("4")
                tvvalue.text=value
            }

            tvFive.setOnClickListener {
                value.append("5")
                tvvalue.text=value
            }

            tvSix.setOnClickListener {
                value.append("6")
                tvvalue.text=value
            }

            tvSeven.setOnClickListener {
                value.append("7")
                tvvalue.text=value
            }

            tvEight.setOnClickListener {
                value.append("8")
                tvvalue.text=value
            }

            tvNine.setOnClickListener {
                value.append("9")
                tvvalue.text=value
            }

            tvZero.setOnClickListener {
                value.append("0")
                tvvalue.text=value
            }


            /*Operators*/

            tvPlus.setOnClickListener {
                value.append("+")
                tvvalue.text=value
            }

            tvMinus.setOnClickListener {
                value.append("-")
                tvvalue.text=value
            }

            tvMul.setOnClickListener {
                value.append("*")
                tvvalue.text=value
            }

            tvDivide.setOnClickListener {
                value.append("/")
                tvvalue.text=value
            }

            tvPercent.setOnClickListener {
                value.append("%")
                tvvalue.text=value
            }
            tvBack.setOnClickListener{
                value.deleteCharAt(value.length-1)
                tvvalue.text=value
            }

            tvrb.setOnClickListener {
                value.append(")")
                tvvalue.text=value
            }
            tvlb.setOnClickListener {
                value.append("(")
                tvvalue.text=value
            }

            tvAllclear.setOnClickListener {
                tvvalue.text = ""
                tvResult.text = ""
                value.clear()
            }
            tvEquals.setOnClickListener{
                var input1 = tvvalue.text.toString()
                var a="ha"
                var opr= mapOf(Pair("*",2), Pair("%",4), Pair("/",2), Pair("+",1), Pair("-",1))
                var stack="("
                val postfix= mutableListOf<String>()
                input1+=')'
                val input= mutableListOf<String>()
                while(input1!="")
                {
                    var symbol=input1[0]
                    var ra=""
                    if(symbol.isDigit())
                    {
                        var a=input1
                        var b=0
                        while ( a[b].isDigit())
                        {
                            ra+=a[b]
                            print(symbol+"ha")
                            b+=1
                        }
                        println()
                        input1=input1.drop(b)
                    }
                    else
                    {
                        ra+=symbol
                        input1=input1.drop(1)
                    }

                    println(ra)
                    input+=ra
                }
                for (i in input)
                {
                    print(i+" ")
                }
                a+="wa"
                while (input.isNotEmpty()){
                    var symbol=input[0]
                    print("symbol=$symbol ,")
                    // add (
                    if(symbol=="(" )
                    {
                        stack+=symbol
                    }
                    if (symbol.toIntOrNull()!=null)
                    {
                        postfix+=symbol
                    }
                    if (symbol in opr.keys)
                    {
                        var last =stack.last().toString()
                        while (last in opr.keys)
                        {
                            if(opr[last]!!>opr[symbol]!!){
                                postfix+=last
                                stack=stack.dropLast(1)
                            }
                            else
                            {
                                break
                            }
                            last=stack.last().toString()
                            println("stuck")
                        }
                        stack+=symbol
                    }
                    if (symbol==")"){
                        var last =stack.last().toString()
                        while(last!="(")
                        {
                            postfix+=last
                            stack=stack.dropLast(1)
                            last=stack.last().toString()
                        }
                        stack=stack.dropLast(1)
                    }
                    input.removeFirst()
                    println("stack=$stack , postfix=$postfix")
                }
                println(stack)
                println(postfix)
                postfix+=")"
                var stack2= mutableListOf<Int>()
                var value=0
                var start =postfix[0]
                while(start!=")")
                {
                    if (start.toIntOrNull()!=null)
                    {
                        stack2 +=start.toInt()
                    }
                    else{
                        val prevind=stack2.indexOf(stack2.last())-1
                        when(start){
                            "+"->stack2[prevind]+=stack2[prevind+1]
                            "-"->stack2[prevind]-=stack2[prevind+1]
                            "*"->stack2[prevind]*=stack2[prevind+1]
                            "/"->stack2[prevind]/=stack2[prevind+1]
                            "%"->stack2[prevind]%=stack2[prevind+1]
                        }
                        stack2.removeLast()
                    }
                    postfix.removeFirst()
                    start=postfix[0]
                }
                tvResult.text="="+stack2[0].toString()
            }
        }

}