package com.example.myapplication.data.remote

import com.example.myapplication.data.model.WorkOrderResponse
import com.example.myapplication.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET(Constants.WORK_ORDER_URL)
    suspend fun getWorkOrderList(
    ): Response<WorkOrderResponse>
}