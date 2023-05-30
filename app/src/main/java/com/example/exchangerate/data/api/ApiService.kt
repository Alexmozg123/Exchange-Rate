package com.example.exchangerate.data.api

import com.example.exchangerate.Const
import com.example.exchangerate.domain.Quotation
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?get=rates" +
            "&pairs=${Const.PAIR_CURRENCY}" +
            "&key=${Const.API_KEY}")
    suspend fun getQuotationListWithAGivenCurrency() : Quotation

    @GET("?get=rates" +
            "&key=${Const.API_KEY}")
    suspend fun getOneCurrency(
        @Query("pairs") currency: String
    ) : Quotation
}