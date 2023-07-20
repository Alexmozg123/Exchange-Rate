package com.example.exchangerate.domain

import com.example.exchangerate.domain.model.Quotation
import javax.inject.Inject

class QuotationInteractor @Inject constructor(
    private val repository: Repository
) {

    suspend fun getQuotationList() : List<Quotation> = repository.getQuotationList()

    fun countConvert(rate: String, convertValue: String) : String{
        val multiply = rate.toFloat() * convertValue.toFloat()
        return multiply.toString()
    }
}
