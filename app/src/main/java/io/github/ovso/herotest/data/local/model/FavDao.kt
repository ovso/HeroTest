package io.github.ovso.herotest.data.local.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.ovso.herotest.data.local.model.FavEntity

@Dao
interface FavDao {
  @Insert
  fun insert(repo: FavEntity)

  @Delete
  fun delete(repo: FavEntity): Int

  @Query("delete from favorites")
  fun removeAll()

  @Query("SELECT * FROM favorites")
  fun bookmarks(): LiveData<List<FavEntity>>

}
