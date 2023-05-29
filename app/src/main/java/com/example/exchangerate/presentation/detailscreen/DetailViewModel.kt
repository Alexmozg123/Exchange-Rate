package com.example.exchangerate.presentation.detailscreen

import androidx.lifecycle.*
import com.example.exchangerate.domain.Repository

class DetailViewModel(
    private val repository: Repository,
) : ViewModel() {

//    private val _result = MutableLiveData<>()
//    val result : LiveData<>
//        get() = _result
//
//    fun updateQuotationList(convertValue: Float) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val hero = repository.convertTheCurrency(convertValue)
//            _result.postValue(hero)
//        }
//    }

    @Suppress("UNCHECKED_CAST")
    class DetailVMFactory(
        private val repository: Repository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            DetailViewModel(repository) as T
    }
}