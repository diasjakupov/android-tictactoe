package com.example.tictactoe.data.di

import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WebModule {

    @Singleton
    @Provides
    fun provideWebSocketFactory(): WebSocketFactory{
        return WebSocketFactory()
    }
}