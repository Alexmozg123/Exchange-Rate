package com.example.exchangerate.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quotation(
    val nameCurrency: String,
    val rate: String
) : Parcelable