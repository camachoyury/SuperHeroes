package com.camachoyury.superheroes.presentation.di

import android.content.Context
import com.camachoyury.superheroes.BuildConfig
import com.camachoyury.superheroes.HeroesApp
import com.camachoyury.superheroes.data.remote.RequestInterceptor
import com.camachoyury.superheroes.data.repository.HeroesRepository
import com.camachoyury.superheroes.data.repository.HeroesRepositoryImpl
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Yury Camacho on 14/02/2018.
 */
@Module
class AppModule(val app: HeroesApp) {

    @Provides
    @Singleton
    fun providesAppContext(): Context = app

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl("http://gateway.marvel.com/v1/public/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor, requestInterceptor: RequestInterceptor): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)

                .addInterceptor(loggingInterceptor)
                .addInterceptor(requestInterceptor)
                .build()

        return httpClient
    }


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {

        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            // development build
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            // production build
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
        }

        return interceptor
    }


    @Provides
    @Singleton
    fun provideInterceptor(): RequestInterceptor {

        return RequestInterceptor()
    }


    @Provides
    @Singleton
    fun provideHeroRepository(retrofit: Retrofit):HeroesRepository{

        return HeroesRepositoryImpl<Any?>(retrofit)

    }

}