package com.example.exchangerate.presentation.detailscreen

import androidx.lifecycle.*
import com.example.exchangerate.domain.Calculator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val calculator: Calculator,
) : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result : LiveData<String>
        get() = _result

    fun updateExchangeRateAgainstTheRuble(rate: String, convertValue: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (convertValue.isEmpty()) {
                _result.postValue("Edit value")
            } else _result.postValue(calculator.countConvert(rate, convertValue))
        }
    }

    @Suppress("UNCHECKED_CAST")
    class DetailVMFactory(
        private val calculator: Calculator,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            DetailViewModel(calculator) as T
    }
}