package io.github.ovso.herotest.data.local.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDao {
  @Insert
  fun insert(repo: FavEntity)

  @Delete
  fun delete(repo: FavEntity): Int

  @Query("DELETE FROM favorites")
  fun removeAll()

  @Query("SELECT * FROM favorites")
  fun favorites(): LiveData<List<FavEntity>>

  @Query("SELECT * FROM favorites WHERE id LIKE :id")
  fun getEntity(id: Int): LiveData<FavEntity?>
}
