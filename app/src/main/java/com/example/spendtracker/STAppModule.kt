package com.example.spendtracker

import android.content.Context
import androidx.room.Room
import com.example.spendtracker.models.database.STDatabase
import com.example.spendtracker.models.database.dao.TransactionsDao
import com.example.spendtracker.models.database.dao.UserDao
import com.example.spendtracker.repositories.STTransactionRepo
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

    @Provides
    @Singleton
    fun provideTransactionRepo(transactionsDao: TransactionsDao): STTransactionRepo =
        STTransactionRepo(transactionsDao)

    @Provides
    fun provideTransactionsDao(db: STDatabase) = db.transactionsDao()


}