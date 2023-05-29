package com.example.exchangerate.data.api

import com.example.exchangerate.Const
import com.example.exchangerate.domain.Quotation
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("?get=rates" +
            "&pairs=${Const.PAIR_CURRENCY}" +
            "&key=${Const.API_KEY}")
    suspend fun getQuotationListWithAGivenCurrency() : Quotation

    @GET(".../{value}")
    suspend fun convertTheCurrency(
        @Path("value") convertValue: Float
    ) : Quotation
}