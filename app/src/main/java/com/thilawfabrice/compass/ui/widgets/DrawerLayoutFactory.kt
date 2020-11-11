package com.thilawfabrice.compass.ui.widgets

import android.util.Log
import androidx.lifecycle.LiveData
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.util.updateBadge
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork

class DrawerLayoutFactory(private val sliderItemIdentifiers: List<Int>) {

    private lateinit var sliderItems: List<PrimaryDrawerItem>

    /**
     *  Create a list of menu items to be added to the drawer
     */
    fun buildMenuItems(slider: MaterialDrawerSliderView): MaterialDrawerSliderView {
        val items = mutableListOf<PrimaryDrawerItem>().apply {
            for (nameRes in sliderItemIdentifiers) {
                add(buildSingleItem(nameRes, _identifier = sliderItemIdentifiers.indexOf(nameRes)))
            }
        }
        sliderItems = items
        // get the reference to the slider and add the items
        slider.itemAdapter.add(items)
        slider.selectedItemPosition = 1
        return slider
    }

    private fun buildSingleItem(name: Int, _identifier: Int) = PrimaryDrawerItem()
        .apply {
            nameRes = name
            identifier = _identifier.toLong()
            badge = StringHolder("0")
        }

    fun trackTopicContentSize(
        slider: MaterialDrawerSliderView,
        itemCountForEachTopic: LiveData<Map<String, Int>>
    ) {
        itemCountForEachTopic.observeForever { map ->
            for (topic in map.keys) {
                val sliderItem: PrimaryDrawerItem? = resolveSliderItemId(slider, topic)
                sliderItem?.let {

                    map[topic]?.let { count ->
                        slider.updateBadge(it.identifier, badge = StringHolder(count))
                    }
                }
            }
        }
    }


    private fun resolveSliderItemId(
        slider: MaterialDrawerSliderView,
        topic: String
    ): PrimaryDrawerItem? {

        return sliderItems.firstOrNull {
            Log.d("RESOLVING ITEMS", "Id reslved : $topic")
            it.name.toString() == topic
        }
    }

    fun updateBadges(slider: MaterialDrawerSliderView, tips: List<TipForRemoteWork>) {
        val map = getContentSizeForEachTopic(slider, tips)
        Log.d("TIP COUNT", "TIP COUNT : \n$map")
        for (item in sliderItems) {
            slider.updateBadge(
                identifier = item.identifier,
                badge = StringHolder(map[item.identifier].toString())
            )
        }
    }

    private fun getContentSizeForEachTopic(
        slider: MaterialDrawerSliderView,
        tips: List<TipForRemoteWork>
    ): MutableMap<Long, String> {
        val map = mutableMapOf<Long, String>()
        for (id in sliderItemIdentifiers) {
            val filter: String = slider.context.getString(id)
            val tipCountForTopic = tips.count { it.topics.toSet().contains(filter) }
            map[id.toLong()] = tipCountForTopic.toString()
        }
        return map
    }


}