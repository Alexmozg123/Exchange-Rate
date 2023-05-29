package com.example.exchangerate.domain

data class Quotation(
    val status: Int,
    val message: String,
    val data: Map<String, String>,
)