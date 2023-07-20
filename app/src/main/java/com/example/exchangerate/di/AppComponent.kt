package com.example.exchangerate.di

import com.example.exchangerate.ui.detailscreen.DetailFragment
import com.example.exchangerate.ui.quotationlistscreen.QuotationListFragment
import dagger.Component

@Component(modules = [NetworkModule::class, BindsModule::class])
interface AppComponent {
    fun inject(quotationListFragment: QuotationListFragment): QuotationListFragment
    fun inject(detailFragment: DetailFragment): DetailFragment
}