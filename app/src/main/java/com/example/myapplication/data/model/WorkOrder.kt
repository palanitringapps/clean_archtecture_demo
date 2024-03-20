package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Jungho Lee on 2/6/24.
 * UpKeep
 * jungho@onupkeep.com
 */
@Entity(tableName = "WorkOrder")
data class WorkOrder(
    @PrimaryKey
    val id: String, val title: String, val description: String
)
