package com.example.tictactoe.data.di

import android.content.Context
import com.example.tictactoe.data.datasources.DataStoreSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context):DataStoreSource{
        return DataStoreSource(context)
    }
}