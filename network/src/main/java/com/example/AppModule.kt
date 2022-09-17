package com.example

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.network.CROP_BASE
import com.example.network.ProductInterface
import com.example.network.ProductRepoImpl
import com.example.network.ProductRepository
import com.example.network.room.CashDao
import com.example.network.room.CashDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory): Retrofit{
        return  Retrofit.Builder()
            .baseUrl(CROP_BASE)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
    @Provides
    @Singleton
    fun provideInterface(retrofit: Retrofit): ProductInterface{
        return retrofit.create(ProductInterface::class.java)
    }


    @Provides
    @Singleton
    fun provideRepository(productInterface: ProductInterface,cashDao: CashDao): ProductRepository{
        return  ProductRepoImpl(productInterface,cashDao)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context : Context): CashDatabase {
        return Room.databaseBuilder(context, CashDatabase::class.java,"cashData").build()
    }

    @Singleton
    @Provides
    fun provideDao(cashDatabase: CashDatabase)= cashDatabase.cashDao


}