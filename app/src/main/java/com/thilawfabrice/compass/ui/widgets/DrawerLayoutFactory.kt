package com.thilawfabrice.compass.ui.widgets

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
            tag = name
            // identifier = _identifier.toLong()
            badge = StringHolder("0")
        }


    fun updateBadges(slider: MaterialDrawerSliderView, tips: List<TipForRemoteWork>) {
        val map = getContentSizeForEachTopic(slider, tips).toMap()
        for (item in sliderItems) {
            if (item.name == null) return
            map[item.name!!.textRes.toLong()]
            slider.updateBadge(
                identifier = item.identifier,
                badge = StringHolder(map[item.name!!.textRes.toLong()].toString())
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