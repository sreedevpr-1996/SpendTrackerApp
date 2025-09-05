package com.example.spendtracker

import android.content.Context
import androidx.annotation.Px
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spendtracker.database.STDatabase
import com.example.spendtracker.database.dao.UserDao
import com.example.spendtracker.repositories.STUserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object STAppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): STDatabase =
        Room.databaseBuilder(context, STDatabase::class.java, "STDatabase").build()

    @Provides
    fun provideUserDao(db: STDatabase) = db.userDao()

    @Provides
    @Singleton
    fun provideUserRepo(userDao: UserDao): STUserRepo = STUserRepo(userDao)
}