package com.thilawfabrice.compass

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.thilawfabrice.compass.data.Repository
import com.thilawfabrice.compass.framework.TipCreator
import com.thilawfabrice.compass.framework.TipsStore
import com.thilawfabrice.compass.usecases.ActionsOnTips
import com.thilawfabrice.compass.viewmodel.TipsViewModel


class Compass : Application() {
    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        tipViewModel.loadTips(
            {
                // Right when data are available, display tips for a default topic
                tipViewModel.updateSelectedCategory(tipViewModel.getLastSelectedCategory())
            },
            {
                //  Toast.makeText(this, "Error when loading $it", Toast.LENGTH_LONG).show()
            }
        )
    }

    private val vmFactory: ViewModelProvider.NewInstanceFactory by lazy {
        TipsViewModel.FACTORY(
            ActionsOnTips(Repository(TipsStore(db), TipCreator()))
        )
    }

    val tipViewModel by lazy { vmFactory.create(TipsViewModel::class.java) }

    companion object {
        const val FIREBASE_TIPS_URL = "tips-for-remote_work"
    }
}