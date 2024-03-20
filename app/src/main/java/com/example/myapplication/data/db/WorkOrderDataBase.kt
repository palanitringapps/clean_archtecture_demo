package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.db.dao.WorkOrderDao
import com.example.myapplication.data.model.WorkOrder

@Database(
    entities = [WorkOrder::class],
    version = 1, exportSchema = false
)
@TypeConverters
abstract class WorkOrderDataBase : RoomDatabase() {
    abstract fun getWorkOrderDao(): WorkOrderDao

    companion object {
        @Volatile
        private var instance: WorkOrderDataBase? = null

        fun getInstance(context: Context): WorkOrderDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WorkOrderDataBase {

            return Room.databaseBuilder(context, WorkOrderDataBase::class.java, "news-db")
                .addCallback(object : Callback() {
                }).build()
        }
    }
}