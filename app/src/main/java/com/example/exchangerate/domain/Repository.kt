package com.example.exchangerate.domain

interface Repository {
    suspend fun getQuotationListWithAGivenCurrency() : Map<String, String>
}