package com.deromang.test.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deromang.test.model.Favorite

@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun getFavoriteById(id: String): Favorite?

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<Favorite>
}
