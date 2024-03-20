package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.WorkOrder
import com.example.myapplication.databinding.ListItemWorkOrderBinding

/**
 * Created by Jungho Lee on 2/6/24.
 * UpKeep
 * jungho@onupkeep.com
 */
class WorkOrderListAdapter(
    private val dataSet: MutableList<WorkOrder> = mutableListOf(),
    private val listener: NavigateToDetails
) :
    RecyclerView.Adapter<WorkOrderListAdapter.WorkOrderListItemViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int,
    ): WorkOrderListItemViewHolder {
        val binding = ListItemWorkOrderBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return WorkOrderListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: WorkOrderListItemViewHolder, position: Int) {
        holder.bindData(dataSet[position])
    }

    fun setList(list: List<WorkOrder>) {
        dataSet.clear()
        dataSet.addAll(list)
        notifyItemRangeChanged(0, list.size)
    }

    inner class WorkOrderListItemViewHolder(private val binding: ListItemWorkOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(workOrder: WorkOrder) {
            binding.tvTitle.text = workOrder.title
            binding.clRoot.setOnClickListener { listener.navigateToDetails(workOrder) }
        }
    }

    interface NavigateToDetails {
        fun navigateToDetails(workOrder: WorkOrder)
    }
}