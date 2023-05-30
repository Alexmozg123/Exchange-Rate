package com.example.exchangerate.presentation.detailscreen

import androidx.lifecycle.*
import com.example.exchangerate.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result : LiveData<String>
        get() = _result

    fun updateExchangeRateAgainstTheRuble(currency: String, value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val rate = repository.getOneCurrency(currency)
            _result.postValue(countConvert(rate, value))
        }
    }

    private fun countConvert(rate: String, value: String) : String {
        val result = rate.toFloat() * value.toFloat()
        return result.toString()
    }

    @Suppress("UNCHECKED_CAST")
    class DetailVMFactory(
        private val repository: Repository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            DetailViewModel(repository) as T
    }
}