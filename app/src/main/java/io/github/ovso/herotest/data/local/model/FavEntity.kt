package io.github.ovso.herotest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavEntity(
    @PrimaryKey val id: Int = 0
)
