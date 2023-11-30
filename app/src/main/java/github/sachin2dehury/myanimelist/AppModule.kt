package github.sachin2dehury.myanimelist

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.sachin2dehury.myanimelist.data.AnimeService
import github.sachin2dehury.myanimelist.data.AnimeService.Companion.BASE_URL
import github.sachin2dehury.myanimelist.data.repository.PaginatedRepository
import github.sachin2dehury.myanimelist.data.repository.PaginatedRepositoryImpl
import github.sachin2dehury.myanimelist.domain.usecase.PaginatedUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkHttp() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun providesMoshi() = Moshi.Builder().build()

    @Provides
    @Singleton
    fun providesMoshiConverterFactory(moshi: Moshi) = MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Provides
    @Singleton
    fun providesAnimeService(retrofit: Retrofit) = retrofit.create(AnimeService::class.java)

    @Provides
    @Singleton
    fun providesPaginatedRepository(service: AnimeService): PaginatedRepository =
        PaginatedRepositoryImpl(service)

    @Provides
    @Singleton
    fun providesPaginatedUseCase(repository: PaginatedRepository, moshi: Moshi) = PaginatedUseCase(repository, moshi)
}
