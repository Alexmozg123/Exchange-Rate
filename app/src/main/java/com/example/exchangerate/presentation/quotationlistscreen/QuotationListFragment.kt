package com.example.exchangerate.presentation.quotationlistscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.R

class QuotationListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var svEdit: SearchView

    private val viewModel: QuotationListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quotation_list, container, false)
        recyclerView = view.findViewById(R.id.rvRates)
        svEdit = view.findViewById(R.id.svEdit)
        svEdit.clearFocus()
//        searching(svEdit)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewModel()
        viewModel.updateQuotationList()
    }

    private fun observeViewModel() {
        viewModel.result.observe(viewLifecycleOwner) {
            setAdapter(it)
        }
    }

    private fun setAdapter(rates: List<Pair<String, String>>) {
        val adapter = QuotationAdapter(rates) { currency ->
            navigateToDetailFragment(currency)
        }
        adapter.submitList(rates)
        recyclerView.adapter = adapter
    }

    private fun navigateToDetailFragment(currency: String) {
        val action = QuotationListFragmentDirections
            .actionQuotationListFragmentToDetailFragment(currency)
        requireView().findNavController().navigate(action)
    }

//    private fun searching(search: SearchView) {
//        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
////                viewModel.filterList(newText) {
////                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
////                }
//                return true
//            }
//        })
//    }
}