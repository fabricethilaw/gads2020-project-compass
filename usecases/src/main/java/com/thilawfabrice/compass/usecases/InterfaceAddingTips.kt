package com.thilawfabrice.compass.usecases

import com.thilawfabrice.compass.domain.entities.TipForRemoteWork

interface InterfaceAddingTips {
    fun saveNewTip(content: String, authorName: String, authorPicture: String)
    fun getFavoriteTips(callback: (List<TipForRemoteWork>) -> Unit)
}