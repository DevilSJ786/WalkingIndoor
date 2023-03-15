package com.test.walkingindoor.di

import android.app.Application
import com.test.walkingindoor.sensor.MeasurableSensor
import com.test.walkingindoor.sensor.StepsSensor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {


    @Provides
    @Singleton
    fun provideStepsSensor(app: Application): MeasurableSensor {
        return StepsSensor(app)
    }
}