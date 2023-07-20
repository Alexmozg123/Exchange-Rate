package com.example.exchangerate.ui.detailscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.exchangerate.R
import com.example.exchangerate.ui.appComponent
import javax.inject.Inject

class DetailFragment : Fragment() {

    private lateinit var tvCurrencyForOneRub: TextView
    private lateinit var tvExchangeRateAgainstTheRuble: TextView
    private lateinit var etConvertValue: EditText

    private val quotation: DetailFragmentArgs by navArgs()
    @Inject lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().appComponent.inject(this)
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        tvCurrencyForOneRub = view.findViewById(R.id.tvCurrencyForOneRub)
        tvExchangeRateAgainstTheRuble = view.findViewById(R.id.tvExchangeRateAgainstTheRuble)
        etConvertValue = view.findViewById(R.id.etConvertValue)
        etConvertValue.addTextChangedListener(createTextChangedListener())

        setViewInfo()

        return view
    }

    private fun setViewInfo() {
        tvCurrencyForOneRub.text = "1 ${quotation.quatation.nameCurrency} = ${quotation.quatation.rate} ${R.string.BASE_CURRENCY}"
        etConvertValue.hint = quotation.quatation.nameCurrency
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.result.observe(viewLifecycleOwner) {
            tvExchangeRateAgainstTheRuble.text = it
        }
    }

    private fun createTextChangedListener() = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
            viewModel.updateExchangeRateAgainstTheRuble(quotation.quatation.rate, s.toString())

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
    }
}