package com.thilawfabrice.compass.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork
import com.thilawfabrice.compass.usecases.ActionsOnTips

class TipsViewModel(private val actions: ActionsOnTips) : ViewModel() {

    private var lastSelectedCategory: String = "Recruiting"

    fun getLastSelectedCategory() = lastSelectedCategory

    private val liveSelectedCategory = MutableLiveData<String>().run {
        postValue(lastSelectedCategory)
        this
    }

    fun loadTips(callback: (List<TipForRemoteWork>) -> Unit, onError: (String) -> Unit) {
        actions.getTips(callback, onError)
    }

    fun updateSelectedCategory(category: String) {
        lastSelectedCategory = category
        liveSelectedCategory.postValue(category)
    }

    fun getSelectedCategory() = liveSelectedCategory as LiveData<String>

    fun getTipsOfCategory(category: String, callback: (List<TipForRemoteWork>) -> Unit) {
        actions.getAllTipsForSpecificCategory(category, callback)
    }

    companion object {
        /**
         * Factory for creating [ProductViewModel]
         * Pass repository to [ProductViewModel]
         */
        val FACTORY = singleArgViewModelFactory(::TipsViewModel)
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


}

enum class DataState { LOADING, READY }