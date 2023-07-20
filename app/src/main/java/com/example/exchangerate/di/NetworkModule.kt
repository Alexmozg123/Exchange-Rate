package com.example.exchangerate.di

import com.example.exchangerate.data.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
class NetworkModule {

    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    fun provideApiService(
        moshi: Moshi
    ) : ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://currate.ru/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create()
    }
}
