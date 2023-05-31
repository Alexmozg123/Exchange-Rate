package com.example.exchangerate.domain

class ExtensionsWorker {
    fun deleteRateExtension(list: List<Pair<String, String>>) : List<Pair<String, String>> {
        val resultList = ArrayList<Pair<String, String>>()
        for (item in list) {
            val subStr = item.first.subSequence(0, 3)
            resultList.add(Pair(subStr.toString(), item.second))
        }
        return resultList.toList()
    }
}
