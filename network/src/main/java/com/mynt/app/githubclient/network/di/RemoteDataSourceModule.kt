package com.mynt.app.githubclient.network.di

import com.mynt.app.githubclient.network.data.repo.RepoRemoteDataSource
import com.mynt.app.githubclient.network.data.repo.RepoRemoteDataSourceImpl
import com.mynt.app.githubclient.network.data.user.UserRemoteDataSource
import com.mynt.app.githubclient.network.data.user.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindUserRemoteDataSource(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindRepoRemoteDataSource(
        repoRemoteDataSourceImpl: RepoRemoteDataSourceImpl
    ): RepoRemoteDataSource

}
