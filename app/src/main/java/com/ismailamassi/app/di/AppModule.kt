package com.ismailamassi.app.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ismailamassi.app.App
import com.ismailamassi.data.db.AppDatabase
import com.ismailamassi.data.repository.ConfigRepositoryImpl
import com.ismailamassi.data.shared.SharedVariables
import com.ismailamassi.domain.Constants
import com.ismailamassi.domain.repository.ConfigRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): App {
        return app as App
    }

    @Singleton
    @Provides
    fun provideShared(@ApplicationContext app: Context) = SharedVariables(app)

    @Provides
    fun provideConfigRepositoryImpl(
        @ApplicationContext context: Context
    ): ConfigRepository = ConfigRepositoryImpl(context)


    @Provides
    @Singleton
    fun provideDatabase(context: Application): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE
        okHttpClient.newBuilder()
            .addInterceptor(logging)
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL.plus("api/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient.newBuilder().addInterceptor(logging).build())
    }
}
