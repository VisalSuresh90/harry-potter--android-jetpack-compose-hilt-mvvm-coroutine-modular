package com.visal.mystoreapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.visal.harrypotter.data.api.AllCharactersApiService
import com.visal.harrypotter.data.api.AllSpellsApiService
import com.visal.harrypotter.data.api.HogwartsStaffApiService
import com.visal.harrypotter.data.api.HogwartsStudentsApiService
import com.visal.harrypotter.data.api.HouseCharactersApiService
import com.visal.harrypotter.data.datasource.AllCharactersDataSource
import com.visal.harrypotter.data.datasource.AllCharactersSourceImpl
import com.visal.harrypotter.data.datasource.AllSpellsDataSource
import com.visal.harrypotter.data.datasource.AllSpellsDataSourceImpl
import com.visal.harrypotter.data.datasource.HogwartsStaffDataSource
import com.visal.harrypotter.data.datasource.HogwartsStaffDataSourceImpl
import com.visal.harrypotter.data.datasource.HogwartsStudentsDataSource
import com.visal.harrypotter.data.datasource.HogwartsStudentsDataSourceImpl
import com.visal.harrypotter.data.datasource.HouseCharactersDataSource
import com.visal.harrypotter.data.datasource.HouseCharactersSourceImpl
import com.visal.harrypotter.data.repository.AllCharactersRepository
import com.visal.harrypotter.data.repository.AllSpellsRepository
import com.visal.harrypotter.data.repository.HogwartsStaffRepository
import com.visal.harrypotter.data.repository.HogwartsStudentsRepository
import com.visal.harrypotter.data.repository.HouseCharactersRepository
import com.visal.harrypotter.ui.common.util.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    /**
     * Provides a Retrofit instance for making network requests.
     *
     * @return The configured Retrofit instance.
     */
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        // Create an HTTP logging interceptor for debugging purposes
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // Create an OkHttpClient with the HTTP logging interceptor and set read timeout
        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
            readTimeout(60, TimeUnit.SECONDS)
        }

        // Create a Moshi instance for JSON parsing
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        // Build and configure the Retrofit instance with base URL, client, and converter factory
        return Retrofit.Builder()
            .baseUrl(AppConstant.APP_BASE_URl)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


    //AllCharacters
    /**
     * Provides an instance of the AllCharactersApiService using the provided Retrofit instance.
     *
     * @param retrofit The Retrofit instance for creating the API service.
     * @return The configured AllCharactersApiService instance.
     */
    @Provides
    @Singleton
    fun providesAllCharactersApiService(retrofit: Retrofit): AllCharactersApiService {
        // Create and return an instance of AllCharactersApiService using Retrofit
        return retrofit.create(AllCharactersApiService::class.java)
    }


    /**
     * Provides an instance of the AllCharactersDataSource by utilizing the provided AllCharactersApiService.
     *
     * @param apiService The AllCharactersApiService used to interact with the API.
     * @return The configured AllCharactersDataSource instance.
     */
    @Provides
    @Singleton
    fun providesAllCharactersDataSource(apiService: AllCharactersApiService): AllCharactersDataSource {
        // Create and return an instance of AllCharactersDataSource using the provided API service
        return AllCharactersSourceImpl(apiService)
    }


    /**
     * Provides an instance of the AllCharactersRepository by utilizing the provided AllCharactersDataSource.
     *
     * @param dataSource The AllCharactersDataSource used for retrieving character data.
     * @return The configured AllCharactersRepository instance.
     */
    @Provides
    @Singleton
    fun providesAllCharactersRepository(dataSource: AllCharactersDataSource): AllCharactersRepository {
        // Create and return an instance of AllCharactersRepository using the provided data source
        return AllCharactersRepository(dataSource)
    }


    //Hogwarts Students
    /**
     * Provides an instance of the HogwartsStudentsApiService using the provided Retrofit instance.
     *
     * @param retrofit The Retrofit instance for creating the API service.
     * @return The configured HogwartsStudentsApiService instance.
     */
    @Provides
    @Singleton
    fun providesHogwartsStudentsApiService(retrofit: Retrofit): HogwartsStudentsApiService {
        // Create and return an instance of HogwartsStudentsApiService using Retrofit
        return retrofit.create(HogwartsStudentsApiService::class.java)
    }


    /**
     * Provides an instance of the HogwartsStudentsDataSource by utilizing the provided HogwartsStudentsApiService.
     *
     * @param apiService The HogwartsStudentsApiService used to interact with the API.
     * @return The configured HogwartsStudentsDataSource instance.
     */
    @Provides
    @Singleton
    fun providesHogwartsStudentsDataSource(apiService: HogwartsStudentsApiService): HogwartsStudentsDataSource {
        // Create and return an instance of HogwartsStudentsDataSource using the provided API service
        return HogwartsStudentsDataSourceImpl(apiService)
    }


    /**
     * Provides an instance of the HogwartsStudentsRepository by utilizing the provided HogwartsStudentsDataSource.
     *
     * @param dataSource The HogwartsStudentsDataSource used for retrieving Hogwarts student data.
     * @return The configured HogwartsStudentsRepository instance.
     */
    @Provides
    @Singleton
    fun providesHogwartsStudentsRepository(dataSource: HogwartsStudentsDataSource): HogwartsStudentsRepository {
        // Create and return an instance of HogwartsStudentsRepository using the provided data source
        return HogwartsStudentsRepository(dataSource)
    }

    //Hogwarts Staff
    /**
     * Provides an instance of the HogwartsStaffApiService using the provided Retrofit instance.
     *
     * @param retrofit The Retrofit instance for creating the API service.
     * @return The configured HogwartsStaffApiService instance.
     */
    @Provides
    @Singleton
    fun providesHogwartsStaffApiService(retrofit: Retrofit): HogwartsStaffApiService {
        // Create and return an instance of HogwartsStaffApiService using Retrofit
        return retrofit.create(HogwartsStaffApiService::class.java)
    }


    /**
     * Provides an instance of the HogwartsStaffDataSource by utilizing the provided HogwartsStaffApiService.
     *
     * @param apiService The HogwartsStaffApiService used to interact with the API.
     * @return The configured HogwartsStaffDataSource instance.
     */
    @Provides
    @Singleton
    fun providesHogwartsStaffDataSource(apiService: HogwartsStaffApiService): HogwartsStaffDataSource {
        // Create and return an instance of HogwartsStaffDataSource using the provided API service
        return HogwartsStaffDataSourceImpl(apiService)
    }


    /**
     * Provides an instance of the HogwartsStaffRepository by utilizing the provided HogwartsStaffDataSource.
     *
     * @param dataSource The HogwartsStaffDataSource used for retrieving Hogwarts staff data.
     * @return The configured HogwartsStaffRepository instance.
     */
    @Provides
    @Singleton
    fun providesHogwartsStaffRepository(dataSource: HogwartsStaffDataSource): HogwartsStaffRepository {
        // Create and return an instance of HogwartsStaffRepository using the provided data source
        return HogwartsStaffRepository(dataSource)
    }

    //AllSpells
    /**
     * Provides an instance of the AllSpellsApiService using the provided Retrofit instance.
     *
     * @param retrofit The Retrofit instance for creating the API service.
     * @return The configured AllSpellsApiService instance.
     */
    @Provides
    @Singleton
    fun providesAllSpellsApiService(retrofit: Retrofit): AllSpellsApiService {
        // Create and return an instance of AllSpellsApiService using Retrofit
        return retrofit.create(AllSpellsApiService::class.java)
    }


    /**
     * Provides an instance of the AllSpellsDataSource by utilizing the provided AllSpellsApiService.
     *
     * @param apiService The AllSpellsApiService used to interact with the API.
     * @return The configured AllSpellsDataSource instance.
     */
    @Provides
    @Singleton
    fun providesAllSpellsDataSource(apiService: AllSpellsApiService): AllSpellsDataSource {
        // Create and return an instance of AllSpellsDataSource using the provided API service
        return AllSpellsDataSourceImpl(apiService)
    }


    /**
     * Provides an instance of the AllSpellsRepository by utilizing the provided AllSpellsDataSource.
     *
     * @param allSpellsDataSource The AllSpellsDataSource used for retrieving spell data.
     * @return The configured AllSpellsRepository instance.
     */
    @Provides
    @Singleton
    fun providesAllSpellsRepository(allSpellsDataSource: AllSpellsDataSource): AllSpellsRepository {
        // Create and return an instance of AllSpellsRepository using the provided data source
        return AllSpellsRepository(allSpellsDataSource)
    }

    //All Hoouse Characters
    /**
     * Provides an instance of the AllCharactersApiService using the provided Retrofit instance.
     *
     * @param retrofit The Retrofit instance for creating the API service.
     * @return The configured HouseCharactersApiService instance.
     */
    @Provides
    @Singleton
    fun providesAllHouseCharactersApiService(retrofit: Retrofit): HouseCharactersApiService {
        // Create and return an instance of HouseCharactersApiService using Retrofit
        return retrofit.create(HouseCharactersApiService::class.java)
    }


    /**
     * Provides an instance of the HouseCharactersDataSource by utilizing the provided HouseCharactersApiService.
     *
     * @param apiService The HouseCharactersApiService used to interact with the API.
     * @return The configured HouseCharactersDataSource instance.
     */
    @Provides
    @Singleton
    fun providesHouseCharactersDataSource(apiService: HouseCharactersApiService): HouseCharactersDataSource {
        // Create and return an instance of HouseCharactersDataSource using the provided API service
        return HouseCharactersSourceImpl(apiService)
    }


    /**
     * Provides an instance of the HouseCharactersRepository by utilizing the provided HouseCharactersDataSource.
     *
     * @param dataSource The HouseCharactersDataSource used for retrieving character data.
     * @return The configured HouseCharactersRepository instance.
     */
    @Provides
    @Singleton
    fun providesHouseCharactersRepository(dataSource: HouseCharactersDataSource): HouseCharactersRepository {
        // Create and return an instance of HouseCharactersRepository using the provided data source
        return HouseCharactersRepository(dataSource)
    }


}