package com.example.exchangerate.data.repository

import com.example.exchangerate.data.api.RetrofitInstance
import com.example.exchangerate.domain.Repository

class RepositoryImpl : Repository {
    override suspend fun getQuotationListWithAGivenCurrency(): Map<String, String> =
        RetrofitInstance.api.getQuotationListWithAGivenCurrency().data

    override suspend fun convertTheCurrency(convertValue: Float): String =
        RetrofitInstance.api.convertTheCurrency(convertValue).data[""].toString()
}