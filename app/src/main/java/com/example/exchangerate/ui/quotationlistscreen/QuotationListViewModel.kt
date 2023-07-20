package com.example.exchangerate.ui.quotationlistscreen

import androidx.lifecycle.*
import com.example.exchangerate.domain.QuotationInteractor
import com.example.exchangerate.domain.model.Quotation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotationListViewModel @Inject constructor(
    private val quotationInteractor: QuotationInteractor,
) : ViewModel() {

    private val rates = mutableListOf<Quotation>()

    private val _result = MutableLiveData<List<Quotation>>()
    val result : LiveData<List<Quotation>>
        get() = _result

    fun updateQuotationList() {
        viewModelScope.launch(Dispatchers.IO) {
            quotationInteractor.getQuotationList().also {
                rates.clear()
                rates.addAll(it)
            }
            _result.postValue(rates)
        }
    }

    fun onTextChanged(text: String) {
        val filterList: MutableList<Quotation> = ArrayList()
        for (item in rates) {
            if (item.nameCurrency.contains(text)) {
                filterList.add(item)
            }
        }
        _result.postValue(filterList)
    }

    @Suppress("UNCHECKED_CAST")
    class QuotationListVMFactory @Inject constructor(
        private val quotationInteractor: QuotationInteractor,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            QuotationListViewModel(quotationInteractor) as T
    }
}