package com.thilawfabrice.compass.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork
import com.thilawfabrice.compass.usecases.ActionsOnTips

class TipsViewModel(private val actions: ActionsOnTips) : ViewModel() {

    private var tipsAreLoaded = MutableLiveData<Boolean>(false)
    fun tipsLoadingStatus(): LiveData<Boolean> = tipsAreLoaded
    private var lastSelectedCategory: String = "Recruiting"
    private val liveTipDataAvailable = MutableLiveData<List<TipForRemoteWork>>(listOf())

    /**
     *
     */
    fun getLastSelectedCategory() = lastSelectedCategory


    fun liveTipData(): LiveData<List<TipForRemoteWork>> = liveTipDataAvailable

    private val liveSelectedCategory = MutableLiveData<String>().run {
        postValue(lastSelectedCategory)
        this
    }

    /**
     *
     */
    fun loadTips(callback: (List<TipForRemoteWork>) -> Unit, onError: (String) -> Unit) {


        actions.getTips({ it ->
            val data = it.distinctBy { it.content }
            // Right when data are available, display tips for a default topic
            updateSelectedCategory(getLastSelectedCategory())
            callback(data)
            tipsAreLoaded.postValue(true)
            liveTipDataAvailable.postValue(it)
        }, onError)
    }

    /**
     *
     */
    fun updateSelectedCategory(category: String) {
        lastSelectedCategory = category
        liveSelectedCategory.postValue(category)
    }

    /**
     *
     */
    fun getSelectedCategory() = liveSelectedCategory as LiveData<String>

    /**
     *
     */
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
