package com.example.foodies.di

import com.example.domain.repository.CatalogDBRepository
import com.example.foodies.data.repository.ProductRepositoryImpl
import com.example.domain.repository.ProductRepository
import com.example.domain.repository.UserDBRepository
import com.example.foodies.data.repository.CatalogDBRepositoryImpl
import com.example.foodies.data.repository.UserDBRepositoryImpl
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
    fun bindUserDBRepository(impl: UserDBRepositoryImpl): UserDBRepository

    @Binds
    @Singleton
    fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    @Singleton
    fun bindCatalogDBRepository(impl: CatalogDBRepositoryImpl): CatalogDBRepository
}