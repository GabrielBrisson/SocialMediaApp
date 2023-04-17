package com.curral.social_media.data.di

import android.content.Context
import com.curral.social_media.data.api.repository.UserRepositoryImpl
import com.curral.social_media.data.api.service.SocialMediaService
import com.curral.social_media.data.local.repository.SharedPrefImpl
import com.curral.social_media.domain.repository.SharedPref
import com.curral.social_media.domain.repository.UserRepository
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    private val baseUrl = "http://apigateway.trabalhosocialmedia.live/api/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Provides
    @Singleton
    fun providesApi(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesSocialMediaService(retrofit: Retrofit): SocialMediaService =
        retrofit.create(SocialMediaService::class.java)

    @Provides
    @Singleton
    fun providesUserRepository(socialMediaService: SocialMediaService): UserRepository =
        UserRepositoryImpl(socialMediaService)

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPref =
        SharedPrefImpl(context)
}
