package com.example.foodies.di

import com.example.foodies.data.repository.ProductRepositoryImpl
import com.example.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository
}