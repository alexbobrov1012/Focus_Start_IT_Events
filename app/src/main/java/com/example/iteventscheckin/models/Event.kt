package com.example.iteventscheckin.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "events_table")
class Event {

    @PrimaryKey
    var id: Int = 0

    var title: String? = null

    var description: String? = null

    var cardImage: String? = null

    var eventFormat: String? = null

    @Embedded
    var date: DateEvent? = null
}
