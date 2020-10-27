package com.thilawfabrice.compass.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thilawfabrice.compass.R
import com.thilawfabrice.compass.data.Repository
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork
import com.thilawfabrice.compass.framework.DummyTips
import com.thilawfabrice.compass.framework.TipCreator
import com.thilawfabrice.compass.ui.home.HomeViewModel.Companion.FACTORY
import com.thilawfabrice.compass.ui.runOnMainThread
import com.thilawfabrice.compass.ui.widgets.TipBlockLayout
import com.thilawfabrice.compass.ui.widgets.TipSelectionListener
import com.thilawfabrice.compass.usecases.ActionsOnTips

class HomeFragment : Fragment() {

    private val vmFactory: ViewModelProvider.NewInstanceFactory by lazy {
        FACTORY(
            ActionsOnTips(Repository(DummyTips(), TipCreator()))
        )
    }

    private val homeViewModel by lazy { vmFactory.create(HomeViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val block: TipBlockLayout = root.findViewById(R.id.block)
        block.setOnActionButtonListener {
            runOnMainThread {
                Toast.makeText(requireContext(), "SEE MORE", Toast.LENGTH_SHORT).show()
            }
        }

        homeViewModel.getFeaturedTip {

            runOnMainThread {
                showFeatured(block, it)
            }
        }
        return root
    }

    @UiThread
    private fun showFeatured(
        block: TipBlockLayout,
        it: List<TipForRemoteWork>
    ) {
        block.setTitle("Featured")
        block.setActionButtonText(getString(R.string.see_all_block_tips,it.size))
        block.setDataList(it)
        block.setItemSelectionCallback(object : TipSelectionListener {
            override fun onTipSelected(tip: TipForRemoteWork) {

            }
        })
        block.postInvalidate()
    }
}