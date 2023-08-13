package com.sofit.israr.composeproject.di

import android.content.Context
import com.sofit.israr.composeproject.R
import com.sofit.israr.composeproject.api.InterfaceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GlobalModule {

    @Provides
    @Singleton
    fun provideBaseUrl(@ApplicationContext context : Context):String{
        return context.getString(R.string.api_base_url)
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl : String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): InterfaceApi = retrofit.create(InterfaceApi::class.java)
}