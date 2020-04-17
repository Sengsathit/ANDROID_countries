package kodizfun.countries.layer_data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kodizfun.countries.di.AppScope
import kodizfun.countries.layer_data.datasource.remote.network.CountriesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @AppScope
    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(COUNTRIES_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @AppScope
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @AppScope
    @Provides
    fun provideHttpLoginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @AppScope
    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @AppScope
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @AppScope
    @Provides
    fun provideFlagsService(retrofit: Retrofit): CountriesApi =
        retrofit.create(CountriesApi::class.java)

    companion object {
        const val COUNTRIES_BASE_URL = "https://raw.githubusercontent.com/"
    }
}