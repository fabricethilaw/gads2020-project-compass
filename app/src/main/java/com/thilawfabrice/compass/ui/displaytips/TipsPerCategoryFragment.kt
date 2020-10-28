package com.thilawfabrice.compass.ui.displaytips

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.thilawfabrice.compass.R
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork
import com.thilawfabrice.compass.ui.TipDetailsActivity
import com.thilawfabrice.compass.ui.getVewModel
import com.thilawfabrice.compass.ui.runOnMainThread
import com.thilawfabrice.compass.ui.widgets.TipBlockLayout
import com.thilawfabrice.compass.ui.widgets.TipSelectionListener

class TipsPerCategoryFragment : Fragment() {


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

        with(getVewModel()) {
            getSelectedCategory().observe(viewLifecycleOwner) { selected ->

                getTipsOfCategory(selected) {
                    runOnMainThread { showTips(block, title = selected, data = it) }
                }
            }
        }

        return root
    }

    companion object {
        fun newInstance() = TipsPerCategoryFragment()

    }

    @UiThread
    private fun showTips(
        block: TipBlockLayout,
        title: String,
        data: List<TipForRemoteWork>
    ) {
        block.setTitle(title)
        block.setActionButtonText(getString(R.string.see_all_block_tips, data.size))
        block.setDataList(data)
        block.setItemSelectionCallback(object : TipSelectionListener {
            override fun onTipSelected(tip: TipForRemoteWork) {
                runOnMainThread { showTipDetails(tip) }
            }

        })
        block.postInvalidate()
    }

    private fun showTipDetails(tip: TipForRemoteWork) {
        val tipDetailsIntent = Intent(requireContext(), TipDetailsActivity::class.java)
        tipDetailsIntent.putExtra(TipDetailsActivity.TIP_DETAILS_INTENT_EXTRA, Gson().toJson(tip))

        startActivity(tipDetailsIntent)
    }

    private val tipDetailsBoxCallback = object : ListenToTipDetailsBox {
        override fun onAddToFavorites(tip: TipForRemoteWork) {

        }

        override fun onVisitWebSite(tip: TipForRemoteWork) {
        }

        override fun onShare(tip: TipForRemoteWork, socialTarget: SocialTarget) {
        }

    }

}