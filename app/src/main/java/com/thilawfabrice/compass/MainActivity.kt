package com.thilawfabrice.compass

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import com.mikepenz.materialdrawer.model.interfaces.Nameable
import com.thilawfabrice.compass.ui.displaytips.TipsPerCategoryFragment
import com.thilawfabrice.compass.ui.widgets.DrawerLayoutFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // todo set @font/source_sans_pro" font family
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val drawerLayoutFactory by lazy { DrawerLayoutFactory() }
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        // Handle Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, TipsPerCategoryFragment.newInstance()).commit()

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer_layout,
            toolbar, com.mikepenz.materialdrawer.R.string.material_drawer_open,
            com.mikepenz.materialdrawer.R.string.material_drawer_close
        )

        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        drawerLayoutFactory.buildMenuItems(slider)

        // specify a click listener
        slider.onDrawerItemClickListener = { v, drawerItem, position ->
            // do something with the clicked item :D
            runOnUiThread {
                val selectedItem = (drawerItem as Nameable).name!!
                    .getText(this)
                // Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show()
                selectedItem?.let { (application as Compass).tipViewModel.updateSelectedCategory(it) }
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
}