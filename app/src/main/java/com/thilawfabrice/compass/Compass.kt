package com.thilawfabrice.compass

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.thilawfabrice.compass.data.Repository
import com.thilawfabrice.compass.framework.DummyTips
import com.thilawfabrice.compass.framework.TipCreator
import com.thilawfabrice.compass.usecases.ActionsOnTips
import com.thilawfabrice.compass.viewmodel.TipsViewModel

class Compass : Application() {

    private val vmFactory: ViewModelProvider.NewInstanceFactory by lazy {
        TipsViewModel.FACTORY(
            ActionsOnTips(Repository(DummyTips(), TipCreator()))
        )
    }

    val tipViewModel by lazy { vmFactory.create(TipsViewModel::class.java) }
}