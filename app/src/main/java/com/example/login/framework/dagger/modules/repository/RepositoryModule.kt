package com.example.login.framework.dagger.modules.repository

import com.example.login.repository.MockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun providesMockRepository(
    ) = MockRepository()
}
