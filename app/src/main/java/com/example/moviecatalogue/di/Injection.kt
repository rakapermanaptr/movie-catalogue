package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.room.MovieDatabase
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.network.ApiClient
import com.example.moviecatalogue.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteRepository = RemoteDataSource.getInstance(ApiClient.getClient())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteRepository, localDataSource, appExecutors)
    }
}