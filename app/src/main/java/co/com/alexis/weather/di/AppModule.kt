package co.com.alexis.weather.di

import co.com.alexis.weather.data.remote.repository.WeatherRepositoryImpl
import co.com.alexis.weather.domain.repository.IWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerWeatherRepository(): IWeatherRepository = WeatherRepositoryImpl()

}