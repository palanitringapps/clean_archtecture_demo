package com.example.myapplication.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiServices: ApiServices) {
    suspend fun getWorkOrders() =
        apiServices.getWorkOrderList()
}