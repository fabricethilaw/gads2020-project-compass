package com.thilawfabrice.compass.framework

import com.thilawfabrice.compass.data.RequestsForFavTips
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork

class TipCreator : RequestsForFavTips{
    override fun setTipAsFavorite(tip: TipForRemoteWork) {
        TODO("Not yet implemented")
    }

    override fun getFavoriteTips(callback: (List<TipForRemoteWork>) -> Unit) {
        TODO("Not yet implemented")
    }
}