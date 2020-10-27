package com.thilawfabrice.compass.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thilawfabrice.compass.R
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork

/**
 * TODO: document your custom view class.
 */
class TipBlockLayout : FrameLayout {

    private var _title: String? = null
    private var _tips: MutableList<TipForRemoteWork> = mutableListOf()
    private val mAdapter by lazy { TipListAdapter() }

    private lateinit var viewContainer: View

    private val titleTextView by lazy { viewContainer.findViewById<TextView>(R.id.blockTitle) }
    private val seeMoreBtn by lazy { viewContainer.findViewById<TextView>(R.id.seeMoreButton) }
    private val listView by lazy { viewContainer.findViewById<RecyclerView>(R.id.tipsListview) }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context, attrs, defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        viewContainer = inflate(context, R.layout.tip_block_layout, this)
        initListView()
    }

    private fun initListView() {
        listView.setHasFixedSize(true)
        listView.adapter = mAdapter
    }

    /**
     * Sets the title of this block
     */
    fun setTitle(title: String) {
        _title = title
        titleTextView.text = _title
        titleTextView.postInvalidate()
    }

    fun setActionButtonText(text: String) {
        seeMoreBtn.text = text
    }

    /**
     * Adds a click events listener  to the "See more" button of the block
     */
    fun setOnActionButtonListener(onClickListener: OnClickListener) {
        seeMoreBtn.setOnClickListener(onClickListener)
    }

    /**
     * Provides list content to be displayed
     */
    fun setDataList(data: List<TipForRemoteWork>) {
        _tips.clear()
        _tips.addAll(data)
        mAdapter.update(_tips)
    }

    /*
     * Sets item selection callback
     */
    fun setItemSelectionCallback(callback: TipSelectionListener) {
        mAdapter.setItemClickListener(callback)
    }




}