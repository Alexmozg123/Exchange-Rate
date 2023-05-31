package com.example.exchangerate.domain.model

data class Quotation(
    val status: Int,
    val message: String,
    val data: Map<String, String>,
)