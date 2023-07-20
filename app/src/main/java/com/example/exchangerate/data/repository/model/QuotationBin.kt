package com.example.exchangerate.data.repository.model

data class QuotationBin(
    val status: Int,
    val message: String,
    val data: Map<String, String>
)