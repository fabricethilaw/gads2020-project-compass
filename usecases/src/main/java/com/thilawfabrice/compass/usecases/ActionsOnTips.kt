package com.thilawfabrice.compass.usecases

import com.thilawfabrice.compass.data.Repository
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork

class ActionsOnTips(private val repository: Repository) : InterfaceAddingTips,
    InterfaceGettingTips {

    override fun getFeaturedTips(callback: (List<TipForRemoteWork>) -> Unit) {
        repository.getFeaturedTips(callback)
    }

    override fun getSomeTipsForSpecificCategory(
        count: Int,
        callback: (List<TipForRemoteWork>) -> Unit
    ) {
        repository.getSomeTipsForSpecificCategory(count, callback)
    }

    override fun getAllTipsForSpecificCategory(
        category: String,
        callback: (List<TipForRemoteWork>) -> Unit
    ) {
        repository.getAllTipsForSpecificCategory(category, callback)
    }


    override fun saveNewTip(content: String, authorName: String, authorPicture: String) {
        repository.saveNewTip(
            content = content,
            authorName = authorName,
            authorPicture = authorPicture
        )
    }


    override fun getFavoriteTips(callback: (List<TipForRemoteWork>) -> Unit) {
        repository.getFavoriteTips(callback)
    }

}