package com.example.exchangerate.domain

import com.example.exchangerate.domain.model.Quotation

interface Repository {
    suspend fun getQuotationList() : List<Quotation>
}