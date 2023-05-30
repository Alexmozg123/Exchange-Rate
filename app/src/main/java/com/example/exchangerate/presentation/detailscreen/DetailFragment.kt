package com.example.exchangerate.presentation.detailscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.exchangerate.R

class DetailFragment : Fragment() {

    private lateinit var tvCurrencyForOneRub: TextView
    private lateinit var tvExchangeRateAgainstTheRuble: TextView
    private lateinit var etConvertValue: EditText

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        tvCurrencyForOneRub = view.findViewById(R.id.tvCurrencyForOneRub)
        tvExchangeRateAgainstTheRuble = view.findViewById(R.id.tvExchangeRateAgainstTheRuble)
        etConvertValue = view.findViewById(R.id.etConvertValue)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.result.observe(viewLifecycleOwner) {
            tvExchangeRateAgainstTheRuble.text = it
        }
        listenEditTextChanges()
    }

    private fun listenEditTextChanges() {
        val currency = args.currency
        etConvertValue.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val convertValue = if (s.isNullOrEmpty()) { "1" } else s.toString()
                viewModel.updateExchangeRateAgainstTheRuble(currency, convertValue)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })
    }

}