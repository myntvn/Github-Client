package com.mynt.app.githubclient.repository.di

import com.mynt.app.githubclient.repository.repo.RepoRepository
import com.mynt.app.githubclient.repository.repo.RepoRepositoryImpl
import com.mynt.app.githubclient.repository.user.UserRepository
import com.mynt.app.githubclient.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindRepoRepository(
        repoRepositoryImpl: RepoRepositoryImpl
    ): RepoRepository

}
