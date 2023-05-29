package com.example.exchangerate.presentation.quotationlistscreen

import androidx.lifecycle.*
import com.example.exchangerate.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotationListViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _result = MutableLiveData<List<Pair<String, String>>>()
    val result : LiveData<List<Pair<String, String>>>
        get() = _result

    fun updateQuotationList() {
        viewModelScope.launch(Dispatchers.IO) {
            val resultMap = repository.getQuotationListWithAGivenCurrency()
            val entries: List<Pair<String, String>> = resultMap.toList()
            _result.postValue(entries)
        }
    }

//    private fun parseMapToObjectList(map: Map<String, String>) : List<`Pair<String, String>`> {
//        lateinit var list: MutableList<`Pair<String, String>`>
//        for ((currency, value) in map) {
//            list.add(`Pair<String, String>`(currency, value))
//        }
//        return list
//    }

//    fun filterList(text: String, toastListener: (errorStr: String) -> Unit) {
//        val filterList: List<Pair<String, String>> = ArrayList()
//        for (OneRatePair in 1.._result.value!!.size) {
//            if () {
//                filterList.listIterator()
//            }
//        }
//
//        if (filterList.isEmpty()) {
//            toastListener("No data found")
//        } else
//    }

    @Suppress("UNCHECKED_CAST")
    class QuotationListVMFactory(
        private val repository: Repository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            QuotationListViewModel(repository) as T
    }
}