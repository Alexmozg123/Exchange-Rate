package com.example.exchangerate.domain

class Calculator {
    fun countConvert(rate: String, convertValue: String) : String{
        val multiply = rate.toFloat() * convertValue.toFloat()
        return multiply.toString()
    }
}
