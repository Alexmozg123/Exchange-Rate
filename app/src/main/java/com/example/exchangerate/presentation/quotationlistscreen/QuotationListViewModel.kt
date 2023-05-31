package com.example.exchangerate.presentation.quotationlistscreen

import androidx.lifecycle.*
import com.example.exchangerate.domain.ExtensionsWorker
import com.example.exchangerate.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotationListViewModel(
    private val repository: Repository,
    private val extensionsWorker: ExtensionsWorker,
) : ViewModel() {

    private val _result = MutableLiveData<List<Pair<String, String>>>()
    val result : LiveData<List<Pair<String, String>>>
        get() = _result

    fun updateQuotationList() {
        viewModelScope.launch(Dispatchers.IO) {
            val resultMap = repository.getQuotationListWithAGivenCurrency()
            val resultList: List<Pair<String, String>> = resultMap.toList()
            _result.postValue(extensionsWorker.deleteRateExtension(resultList))
        }
    }

    fun filterList(
        text: String,
        emptyListener: (errorStr: String) -> Unit,
        listListener: (listListener: List<Pair<String, String>>) -> Unit,
    ) {
        val filterList: MutableList<Pair<String, String>> = ArrayList()
        for (item in _result.value!!) {
            if (item.first.contains(text)) {
                filterList.add(item)
            }
        }

        if (filterList.isEmpty()) {
            emptyListener("No data found")
        } else listListener(filterList.toList())
    }

    @Suppress("UNCHECKED_CAST")
    class QuotationListVMFactory(
        private val repository: Repository,
        private val extensionsWorker: ExtensionsWorker,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            QuotationListViewModel(repository, extensionsWorker) as T
    }
}