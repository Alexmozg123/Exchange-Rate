package com.example.exchangerate.ui.detailscreen

import androidx.lifecycle.*
import com.example.exchangerate.domain.QuotationInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val quotationInteractor: QuotationInteractor,
) : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result : LiveData<String>
        get() = _result

    fun updateExchangeRateAgainstTheRuble(rate: String, convertValue: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (convertValue.isEmpty()) {
                _result.postValue("Edit value")
            } else _result.postValue(quotationInteractor.countConvert(rate, convertValue))
        }
    }

    @Suppress("UNCHECKED_CAST")
    class DetailVMFactory @Inject constructor(
        private val quotationInteractor: QuotationInteractor,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            DetailViewModel(quotationInteractor) as T
    }
}