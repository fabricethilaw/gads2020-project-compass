package com.thilawfabrice.compass.data

import com.thilawfabrice.compass.domain.entities.TipForRemoteWork


class Repository(
    private val tipsPersistenceSource: PersistenceSource,
    private val requestForFavTip: RequestsForFavTips
) {

    fun getFeaturedTips(callback: (List<TipForRemoteWork>) -> Unit) {
        tipsPersistenceSource.getFeaturedTips(callback)
    }

    fun getSomeTipsForSpecificCategory(count: Int, callback: (List<TipForRemoteWork>) -> Unit) {
        tipsPersistenceSource.getSomeTipsForSpecificCategory(count = count, callback = callback)
    }

    fun getAllTipsForSpecificCategory(
        category: String,
        callback: (List<TipForRemoteWork>) -> Unit
    ) {
        tipsPersistenceSource.getAllTipsForSpecificCategory(category, callback)
    }


    fun saveNewTip(content:String, authorName: String, authorPicture: String) {
        tipsPersistenceSource.saveNewTip(content = content, authorName = authorName, authorPicture = authorPicture)
    }

    fun saveTipToFavorite(tip: TipForRemoteWork) {
        requestForFavTip.setTipAsFavorite(tip)
    }

    fun getFavoriteTips(callback: (List<TipForRemoteWork>) -> Unit) {
        requestForFavTip.getFavoriteTips(callback)
    }

}

interface PersistenceSource {


    fun getFeaturedTips(callback: (List<TipForRemoteWork>) -> Unit): List<TipForRemoteWork>

    fun getSomeTipsForSpecificCategory(count: Int, callback: (List<TipForRemoteWork>) -> Unit)

    fun getAllTipsForSpecificCategory(
        category: String,
        callback: (List<TipForRemoteWork>) -> Unit
    ): List<TipForRemoteWork>

    // fun getATipContent()

    fun saveNewTip(content:String, authorName: String, authorPicture: String)


}

interface RequestsForFavTips {
    fun setTipAsFavorite(tip: TipForRemoteWork)
    fun getFavoriteTips(callback: (List<TipForRemoteWork>) -> Unit)
}