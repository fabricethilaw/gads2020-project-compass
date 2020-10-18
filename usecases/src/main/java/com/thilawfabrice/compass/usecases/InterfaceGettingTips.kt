package com.thilawfabrice.compass.usecases

import com.thilawfabrice.compass.domain.entities.TipForRemoteWork

interface InterfaceGettingTips {

    fun getFeaturedTips(callback: (List<TipForRemoteWork>) -> Unit)

    fun getSomeTipsForSpecificCategory(count: Int, callback: (List<TipForRemoteWork>) -> Unit)

    fun getAllTipsForSpecificCategory(category: String, callback: (List<TipForRemoteWork>) -> Unit)

}