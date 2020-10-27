package com.thilawfabrice.compass.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork
import com.thilawfabrice.compass.singleArgViewModelFactory
import com.thilawfabrice.compass.usecases.ActionsOnTips

class HomeViewModel(private val actions: ActionsOnTips,) : ViewModel() {

    companion object {
        /**
         * Factory for creating [ProductViewModel]
         * Pass repository to [ProductViewModel]
         */
        val FACTORY = singleArgViewModelFactory(::HomeViewModel)
    }

    fun getFeaturedTip(callback: (List<TipForRemoteWork>) -> Unit) {
        actions.getFeaturedTips(callback)
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


}