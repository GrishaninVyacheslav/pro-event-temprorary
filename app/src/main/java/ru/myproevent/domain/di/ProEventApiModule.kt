package ru.myproevent.domain.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.myproevent.domain.model.IProEventDataSource
import javax.inject.Named
import javax.inject.Singleton

@Module
class ProEventApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "http://178.249.69.107:8762/"

    @Provides
    fun provideTickerDataApi(@Named("baseUrl") baseUrl: String, gson: Gson): IProEventDataSource {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IProEventDataSource::class.java)
    }


    @Singleton
    @Provides
    fun provideGson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        //       .excludeFieldsWithoutExposeAnnotation()
        .create()
}

