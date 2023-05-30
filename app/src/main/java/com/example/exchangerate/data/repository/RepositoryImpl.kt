package com.example.exchangerate.data.repository

import com.example.exchangerate.data.api.RetrofitInstance
import com.example.exchangerate.domain.Repository

class RepositoryImpl : Repository {
    override suspend fun getQuotationListWithAGivenCurrency(): Map<String, String> =
        RetrofitInstance.api.getQuotationListWithAGivenCurrency().data

    override suspend fun getOneCurrency(currency: String): String =
        RetrofitInstance.api.getOneCurrency(currency).data[currency].toString()
}