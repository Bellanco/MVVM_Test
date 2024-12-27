package com.deromang.test.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deromang.test.data.db.FavoriteDAO
import com.deromang.test.model.Favorite

@Database(entities = [Favorite::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDAO
}
