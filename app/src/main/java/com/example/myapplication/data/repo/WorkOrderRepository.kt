package com.example.myapplication.data.repo

import com.example.myapplication.data.db.dao.WorkOrderDao
import com.example.myapplication.data.model.WorkOrder
import com.example.myapplication.data.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ActivityRetainedScoped
class WorkOrderRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: WorkOrderDao
) {
    /**
     * Repository class function to fetch the data from remote
     */
    suspend fun getWorkOrdersFromRemote() {
        val responseData = remoteDataSource.getWorkOrders()
        responseData.body()?.let {
            localDataSource.insertWorkOrders(it.workOrders)
        }
    }

    /**
     * Repository class function to fetch the data from local
     */
    fun getWorkOrders(): Flow<List<WorkOrder>> {
        return localDataSource.getAllWorkOrders()
    }
}
