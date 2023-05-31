package com.example.exchangerate.presentation.quotationlistscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.R

class QuotationListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var svEdit: SearchView
    private lateinit var adapter: QuotationAdapter

    private val viewModel: QuotationListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quotation_list, container, false)
        recyclerView = view.findViewById(R.id.rvRates)
        svEdit = view.findViewById(R.id.svEdit)
        svEdit.clearFocus()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewModel()
        viewModel.updateQuotationList()
    }

    override fun onResume() {
        super.onResume()
        searching(svEdit)
    }

    private fun observeViewModel() {
        viewModel.result.observe(viewLifecycleOwner) {
            setAdapter(it)
        }
    }

    private fun setAdapter(rates: List<Pair<String, String>>) {
        adapter = QuotationAdapter(rates) { currency, value ->
            navigateToDetailFragment(currency, value)
        }
        adapter.submitList(rates)
        recyclerView.adapter = adapter
    }

    private fun navigateToDetailFragment(currency: String, value: String) {
        val rate = arrayOf(currency, value)
        val action = QuotationListFragmentDirections
            .actionQuotationListFragmentToDetailFragment(rate)
        requireView().findNavController().navigate(action)
    }

    private fun searching(search: SearchView) {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterList(newText,
                    { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() },
                    { adapter.submitList(it) })
                return true
            }
        })
    }
}