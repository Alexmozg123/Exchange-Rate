package com.example.exchangerate.data.repository

import com.example.exchangerate.data.api.ApiService
import com.example.exchangerate.domain.Repository
import com.example.exchangerate.domain.model.Quotation
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {
    override suspend fun getQuotationList(): List<Quotation> =
        apiService.getQuotationList().data
            .map { item -> Quotation(item.key.substring(0,2), item.value) }
}
