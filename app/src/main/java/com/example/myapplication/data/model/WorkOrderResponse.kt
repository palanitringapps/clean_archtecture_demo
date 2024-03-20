package com.example.myapplication.data.model

import com.example.myapplication.data.model.WorkOrder
import com.google.gson.annotations.SerializedName

/**
 * Created by Jungho Lee on 2/21/24.
 * UpKeep
 * jungho@onupkeep.com
 */
data class WorkOrderResponse(@SerializedName("work_orders") val workOrders: List<WorkOrder>)