package com.example.exchangerate.data.api

import com.example.exchangerate.R
import com.example.exchangerate.data.repository.model.QuotationBin
import retrofit2.http.GET

interface ApiService {
    @GET("?get=rates" +
            "&pairs=${R.string.PAIR_CURRENCY}" +
            "&key=${R.string.API_KEY}")
    suspend fun getQuotationList() : QuotationBin
}