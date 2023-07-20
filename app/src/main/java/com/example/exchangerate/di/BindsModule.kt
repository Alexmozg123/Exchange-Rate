package com.example.exchangerate.di

import com.example.exchangerate.data.repository.RepositoryImpl
import com.example.exchangerate.domain.Repository
import dagger.Binds
import dagger.Module

@Module
interface BindsModule {

    @Suppress("FunctionName")
    @Binds
    fun bindsRepositoryImpl_to_Repository(
        repositoryImpl: RepositoryImpl
    ): Repository
}