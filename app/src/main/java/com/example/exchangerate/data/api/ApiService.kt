package com.example.exchangerate.data.api

import com.example.exchangerate.Const
import com.example.exchangerate.domain.model.Quotation
import retrofit2.http.GET

interface ApiService {
    @GET("?get=rates" +
            "&pairs=${Const.PAIR_CURRENCY}" +
            "&key=${Const.API_KEY}")
    suspend fun getQuotationListWithAGivenCurrency() : Quotation
}