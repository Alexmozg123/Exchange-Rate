package com.example.exchangerate.domain

import java.util.Currency

interface Repository {
    suspend fun getQuotationListWithAGivenCurrency() : Map<String, String>
    suspend fun getOneCurrency(currency: String) : String
}