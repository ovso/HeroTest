package io.github.ovso.herotest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.ovso.herotest.data.local.model.FavDao
import io.github.ovso.herotest.data.local.model.FavEntity

@Database(entities = [FavEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favDao(): FavDao
}
