package com.example.exchangerate.ui.quotationlistscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.R
import com.example.exchangerate.domain.model.Quotation
import com.example.exchangerate.ui.appComponent
import javax.inject.Inject

class QuotationListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var svEdit: SearchView

    private val adapter = QuotationAdapter { quotation -> navigateToDetailFragment(quotation) }
    @Inject lateinit var viewModel: QuotationListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().appComponent.inject(this)
        val view = inflater.inflate(R.layout.fragment_quotation_list, container, false)
        recyclerView = view.findViewById(R.id.rvRates)
        svEdit = view.findViewById(R.id.svEdit)
        svEdit.clearFocus()
        svEdit.setOnQueryTextListener(createSearchListener())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.adapter = adapter
        viewModel.result.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.updateQuotationList()
    }

    private fun navigateToDetailFragment(quotation: Quotation) {
        val action = QuotationListFragmentDirections
            .actionQuotationListFragmentToDetailFragment(quotation)
        requireView().findNavController().navigate(action)
    }

    private fun createSearchListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean = false

        override fun onQueryTextChange(newText: String): Boolean {
            viewModel.onTextChanged(newText)
            return true
        }
    }
}