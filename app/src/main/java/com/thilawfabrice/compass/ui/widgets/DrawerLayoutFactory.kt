package com.thilawfabrice.compass.ui.widgets

import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.widget.MaterialDrawerSliderView
import com.thilawfabrice.compass.R

class DrawerLayoutFactory {


    /**
     *  Create a list of menu items to be added to the drawer
     */
    fun buildMenuItems(slider: MaterialDrawerSliderView): MaterialDrawerSliderView {
        val items = mutableListOf<PrimaryDrawerItem>().apply {
            for (nameRes in itemNames) {
                add(buildSingleItem(nameRes, _identifier = itemNames.indexOf(nameRes)))
            }
        }
        // get the reference to the slider and add the items
        slider.itemAdapter.add(items)
        return slider
    }

    private fun buildSingleItem(name: Int, _identifier: Int) = PrimaryDrawerItem()
        .apply {
            nameRes = name
            identifier = _identifier.toLong()
        }


    private val itemNames = listOf(
        R.string.menu_featured,
        R.string.menu_recruiting,
        R.string.menu_management,
        R.string.menu_communication,
        R.string.menu_way_of_work,
        R.string.menu_space,
        R.string.menu_meetings,
        R.string.menu_connection,
        R.string.menu_social,
        R.string.menu_boundaries,
        R.string.menu_health,
        R.string.menu_accountability
    )


}