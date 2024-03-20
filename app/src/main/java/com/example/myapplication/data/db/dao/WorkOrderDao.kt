package com.example.myapplication.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.model.WorkOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkOrderDao {
    @Query("Select * from WorkOrder")
    fun getAllWorkOrders(): Flow<List<WorkOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkOrders(workList: List<WorkOrder>)
}