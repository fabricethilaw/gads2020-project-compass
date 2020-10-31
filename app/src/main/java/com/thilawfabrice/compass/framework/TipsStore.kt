package com.thilawfabrice.compass.framework

import com.google.firebase.firestore.FirebaseFirestore
import com.thilawfabrice.compass.Compass
import com.thilawfabrice.compass.data.PersistenceSource
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork

/**
 * Provides Firestore database as a concrete implementation of persistence data source
 */
class TipsStore(private val db: FirebaseFirestore) : PersistenceSource {
    private val tips = mutableListOf<TipForRemoteWork>()
    override fun loadTips(
        callback: (tips: List<TipForRemoteWork>) -> Unit,
        errorHandler: (String) -> Unit
    ) {
        db.collection(Compass.FIREBASE_TIPS_URL)
            .get()
            .addOnSuccessListener { result ->
                result.map { document ->
                    document.toObject(TipForRemoteWork::class.java)
                }.let {
                    tips.addAll(it)
                    callback(tips)
                }
            }

            .addOnFailureListener { exception ->
                exception.message?.let { errorHandler.invoke(it) }
                //  Log.d(TAG, "Error getting documents: ", exception)
            }
    }

    override fun getFeaturedTips(callback: (List<TipForRemoteWork>) -> Unit) {
        val category = "Featured"
        tips.filter { it.topics.contains(category) }.run {
            callback(this)
        }
    }

    override fun getSomeTipsForSpecificCategory(
        count: Int,
        callback: (List<TipForRemoteWork>) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getAllTipsForSpecificCategory(
        category: String,
        callback: (List<TipForRemoteWork>) -> Unit
    ) {
        val r = tips.filter { it.topics.contains(category) }
        callback(r)
    }

    override fun saveNewTip(content: String, authorName: String, authorPicture: String) {
        TODO("Not yet implemented")
    }


}