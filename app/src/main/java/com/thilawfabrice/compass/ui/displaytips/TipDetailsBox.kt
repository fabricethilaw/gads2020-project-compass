package com.thilawfabrice.compass.ui.displaytips

import com.thilawfabrice.compass.domain.entities.TipForRemoteWork


interface ListenToTipDetailsBox {
    fun onAddToFavorites(tip: TipForRemoteWork)
    fun onVisitWebSite(tip: TipForRemoteWork)
    fun onShare(tip: TipForRemoteWork, socialTarget: SocialTarget)
}

enum class SocialTarget {
    TWITTER, FACEBOOK, LINKEDIN
}