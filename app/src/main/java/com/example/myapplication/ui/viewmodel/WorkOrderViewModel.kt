package com.example.myapplication.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import com.example.myapplication.data.model.WorkOrder
import com.example.myapplication.data.repo.WorkOrderRepository
import com.example.myapplication.utils.isNetworkConnected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkOrderViewModel @Inject constructor
    (
    private val repository: WorkOrderRepository,
    private val application: Application
) : AndroidViewModel(application) {
    private val _response: MutableLiveData<List<WorkOrder>> = MutableLiveData()
    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val response: LiveData<List<WorkOrder>> = _response
    val errorMessage: LiveData<String> = _errorMessage
    var workOrderDetails: WorkOrder? = null

    init {
        /**
         * coroutine flow which is used to collect the changes from work order table content
         * this value updates the livedata object
         */
        viewModelScope.launch {
            repository.getWorkOrders().collect {
                _response.value = it
            }
        }
    }

    /**
     * this method is used to fetch the latest work order response from remote service
     */
    fun getWorkOrders() = viewModelScope.launch {
        if (isNetworkConnected(application)) {
            repository.getWorkOrdersFromRemote()
        } else {
            _errorMessage.value = application.getString(R.string.network_error)
            Log.i("WorkOrderViewModel", application.getString(R.string.network_error))
        }
    }
}

