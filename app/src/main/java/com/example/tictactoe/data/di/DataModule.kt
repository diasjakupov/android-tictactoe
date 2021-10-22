package com.example.tictactoe.data.di

import android.content.Context
import com.example.tictactoe.data.datasources.DataStoreSource
import com.example.tictactoe.data.datasources.RemoteDataSource
import com.example.tictactoe.data.datasources.RemoteDataSourceImpl
import com.example.tictactoe.data.network.GameApi
import com.example.tictactoe.data.network.UserApi
import com.example.tictactoe.data.repository.game.GameRepository
import com.example.tictactoe.data.repository.game.GameRepositoryImpl
import com.example.tictactoe.data.repository.user.UserRepository
import com.example.tictactoe.data.repository.user.UserRepositoryImpl
import com.neovisionaries.ws.client.WebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context):DataStoreSource{
        return DataStoreSource(context)
    }


    @Singleton
    @Provides
    fun provideRemoteDataSource(userApi: UserApi, gameApi:GameApi):RemoteDataSource{
        return RemoteDataSourceImpl(userApi, gameApi)
    }

    @Singleton
    @Provides
    fun provideUserRepository(dataStoreSource: DataStoreSource, remoteDataSource: RemoteDataSource):UserRepository{
        return UserRepositoryImpl(dataStoreSource, remoteDataSource)
    }


    @Singleton
    @Provides
    fun provideGameRepository(factory: WebSocketFactory, remoteDataSource: RemoteDataSource): GameRepository {
        return GameRepositoryImpl(factory, remoteDataSource)
    }


}