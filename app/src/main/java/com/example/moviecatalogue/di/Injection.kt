package com.example.moviecatalogue.di

import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.network.ApiClient

object Injection {
    fun provideRepository(): MovieRepository {
        val remoteRepository = RemoteDataSource.getInstance(ApiClient.getClient())

        return MovieRepository.getInstance(remoteRepository)
    }
}