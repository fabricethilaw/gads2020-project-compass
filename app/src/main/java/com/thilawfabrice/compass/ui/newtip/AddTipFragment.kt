package com.thilawfabrice.compass.ui.newtip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thilawfabrice.compass.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddTipFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_tip, container, false)
    }

}