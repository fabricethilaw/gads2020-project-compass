package com.thilawfabrice.compass.ui.widgets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thilawfabrice.compass.R
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork

/**
 * Adapter for managing the display of tips cards in a listview
 */
class TipListAdapter :
    RecyclerView.Adapter<TipViewHolder>() {

    private var callback: TipSelectionListener? = null
    private val data = mutableListOf<TipForRemoteWork>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_tip, parent, false)
        return TipViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(data[position])

        // set click events callback
        callback?.let { holder.setItemClickListener(it) }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setItemClickListener(listener: TipSelectionListener) {
        callback = listener
    }

    /**
     * Clear the Recyclerview view items and replace them with new data
     */
    fun update(freshData: List<TipForRemoteWork>) {
        data.clear()
        data.addAll(freshData)
        notifyDataSetChanged()
    }
}

class TipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var contentTV: TextView = itemView.findViewById(R.id.content)
    private var authorNameTV: TextView = itemView.findViewById(R.id.tipAuthorName)
    private var authorRoleTV: TextView = itemView.findViewById(R.id.tipAuthorRole)
    private var authorPictureImg: ImageView = itemView.findViewById(R.id.tipAuthorPicture)
    private lateinit var item: TipForRemoteWork

    fun bind(tipForRemoteWork: TipForRemoteWork) {
        item = tipForRemoteWork
        with(tipForRemoteWork) {
            val contentSlice = if (content.length > 160) {
                content.subSequence(0, 160)
                    .toString() + itemView.context.getString(R.string.marquee)
            } else {
                content
            }

            contentTV.text = contentSlice
            authorNameTV.text = author?.name
            val company =
                "${author?.companyName?.run { if (this.isBlank().not()) "at $this" else "" }}"
            val role = "${author?.role} $company"

            authorRoleTV.text = role
            Glide.with(itemView.context)
                .load(author?.picture)
                .circleCrop()
                .placeholder(R.drawable.ic_picture_person)
                .into(authorPictureImg)
        }
    }

    fun setItemClickListener(callback: TipSelectionListener) {
        itemView.setOnClickListener { callback.onTipSelected(item) }
    }
}

interface TipSelectionListener {
    fun onTipSelected(tip: TipForRemoteWork)
}