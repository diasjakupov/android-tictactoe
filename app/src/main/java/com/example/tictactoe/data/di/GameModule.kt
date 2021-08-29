package com.example.tictactoe.data.di

import com.example.tictactoe.data.repository.game.GameRepository
import com.example.tictactoe.data.repository.game.GameRepositoryImpl
import com.neovisionaries.ws.client.WebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class GameModule {

    @Singleton
    @Provides
    fun provideGameRepository(factory: WebSocketFactory): GameRepository{
        return GameRepositoryImpl(factory)
    }
}