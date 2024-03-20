package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.databinding.FragmentWorkOrderDetailsLayoutBinding
import com.example.myapplication.ui.viewmodel.WorkOrderViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the third destination in the navigation.
 * This fragment shows work order details which selected from previous screen
 */

@AndroidEntryPoint
class WorkOrderDetailsFragment : Fragment() {
    private var _binding: FragmentWorkOrderDetailsLayoutBinding? = null

    private val binding get() = _binding!!
    private val viewModel: WorkOrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkOrderDetailsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvWorkOrderTitle.text = viewModel.workOrderDetails?.title
        binding.tvWorkOrderDesc.text = viewModel.workOrderDetails?.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}