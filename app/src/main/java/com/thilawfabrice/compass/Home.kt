package com.thilawfabrice.compass

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.mikepenz.materialdrawer.model.interfaces.Nameable
import com.thilawfabrice.compass.ui.displaytips.TipsPerCategoryFragment
import com.thilawfabrice.compass.ui.widgets.DrawerLayoutFactory
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main screen
 */
class Home : AppCompatActivity() {


    private val drawerLayoutFactory by lazy { DrawerLayoutFactory(sliderItemIdentifiers) }
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private val viewModel by lazy { (application as Compass).tipViewModel }
    private var selectedDrawerItem: String? = "Recruiting"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, TipsPerCategoryFragment.newInstance()).commit()

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer_layout,
            toolbar, com.mikepenz.materialdrawer.R.string.material_drawer_open,
            com.mikepenz.materialdrawer.R.string.material_drawer_close,
        )

        addOpenCloseSlideStateListener()

        drawer_layout.addDrawerListener(actionBarDrawerToggle)

        drawerLayoutFactory.buildMenuItems(slider)

        viewModel.liveTipData().observeForever {
            drawerLayoutFactory.updateBadges(slider, it)
        }
        // specify a click listener
        addClickListenerForSlider()
    }

    private fun setActionBar(toolbar: Toolbar) {
        // Handle Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun addOpenCloseSlideStateListener() {
        drawer_layout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // no need to take action here
            }

            override fun onDrawerOpened(drawerView: View) {
                // no need to take action here
            }

            override fun onDrawerClosed(drawerView: View) {
                viewModel.getLastSelectedCategory().let { viewModel.updateSelectedCategory(it) }
            }

            override fun onDrawerStateChanged(newState: Int) {
                // no need to take action here
            }
        })
    }

    private fun addClickListenerForSlider() {
        slider.onDrawerItemClickListener = { v, drawerItem, position ->
            // do something with the clicked item :D
            runOnUiThread {
                selectedDrawerItem = (drawerItem as Nameable).name!!
                    .getText(this)
                selectedDrawerItem?.let { viewModel.updateSelectedCategory(it) }
            }
            false
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onResume() {
        super.onResume()
        actionBarDrawerToggle.syncState()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return false
    }


    override fun onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (drawer_layout?.isDrawerOpen(slider) == true) {
            drawer_layout?.closeDrawer(slider)
        } else {
            super.onBackPressed()
        }
    }

    private val sliderItemIdentifiers = listOf(
        R.string.menu_zoom,
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