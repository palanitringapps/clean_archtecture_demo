package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.model.WorkOrder
import com.example.myapplication.databinding.FragmentWorkOrderListBinding
import com.example.myapplication.ui.adapter.WorkOrderListAdapter
import com.example.myapplication.ui.viewmodel.WorkOrderViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 * This fragment shows list of work order for current logged user
 */
@AndroidEntryPoint
class WorkOrderListFragment : Fragment(), WorkOrderListAdapter.NavigateToDetails {

    private var _binding: FragmentWorkOrderListBinding? = null

    private val binding get() = _binding!!
    private val viewModel: WorkOrderViewModel by activityViewModels()

    private lateinit var workOrderListAdapter: WorkOrderListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWorkOrderListBinding.inflate(inflater, container, false)

        workOrderListAdapter = WorkOrderListAdapter(listener = this)
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = workOrderListAdapter
        }
        initObserver()
        return binding.root
    }

    private fun initObserver() {
        viewModel.response.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            workOrderListAdapter.setList(it)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMenuOptions()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchResponseData() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getWorkOrders()
    }

    /**
     * callback method which is used to initiate work order details screen navigation
     */

    override fun navigateToDetails(workOrder: WorkOrder) {
        viewModel.workOrderDetails = workOrder
        findNavController().navigate(R.id.action_work_order_list_fragment_to_details)
    }

    /** Removed the deprecated code
     *  create and select added menu host to display the menus
     */
    private fun initMenuOptions() {

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_done, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_done -> {
                        fetchResponseData()
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}