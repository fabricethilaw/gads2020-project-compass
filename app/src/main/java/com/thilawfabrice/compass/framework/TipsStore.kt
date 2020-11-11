package com.thilawfabrice.compass.framework

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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

        db.collection(Compass.FIREBASE_TIPS_URL).addSnapshotListener { snapshot, e ->
            if (e != null) {
                // Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                // Log.d(TAG, "Current data: ${snapshot.data}")
                dispatchData(result = snapshot, callback = callback)
            }
        }

        /* db.collection(Compass.FIREBASE_TIPS_URL)
             .get()
             .addOnSuccessListener { result ->
                 dispatchData(result, callback)
             }

             .addOnFailureListener { exception ->
                 exception.message?.let { errorHandler.invoke(it) }
                 //  Log.d(TAG, "Error getting documents: ", exception)
             }*/
    }

    private fun dispatchData(
        result: QuerySnapshot,
        callback: (tips: List<TipForRemoteWork>) -> Unit
    ) {
        result.map { document ->
            document.toObject(TipForRemoteWork::class.java)
        }.let {
            tips.clear()
            tips.addAll(it)
            callback(tips)
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