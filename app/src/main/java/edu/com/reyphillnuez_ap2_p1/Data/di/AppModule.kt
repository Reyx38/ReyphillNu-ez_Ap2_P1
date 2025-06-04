package edu.com.reyphillnuez_ap2_p1.Data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.com.reyphillnuez_ap2_p1.Data.Local.database.parcial1
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            parcial1::class.java,
            "parcial1.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun providesTareas(tareasdb: parcial1) = tareasdb.tareasDao()
}