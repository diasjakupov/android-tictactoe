package com.example.tictactoe.data.di

import android.util.Log
import com.example.tictactoe.data.network.GameApi
import com.example.tictactoe.data.network.UserApi
import com.example.tictactoe.data.utils.Constants
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.Buffer
import javax.inject.Singleton
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor


@Module
@InstallIn(SingletonComponent::class)
class WebModule {


    @Singleton
    @Provides
    fun provideOkHttpClient():OkHttpClient{
        val interceptor=Interceptor{
            val url = it.request()
                .url.newBuilder()
                .build()


            val request = it.request()
                .newBuilder()
                .url(url)
                .build()


            return@Interceptor it.proceed(request)
        }
        val loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideWebSocketFactory(): WebSocketFactory{
        return WebSocketFactory()
    }

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit):UserApi{
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGameApi(retrofit: Retrofit):GameApi{
        return retrofit.create(GameApi::class.java)
    }
}