package com.thilawfabrice.compass.ui.displaytips

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.thilawfabrice.compass.R
import com.thilawfabrice.compass.R.drawable.box_cornered_background
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork

class TipDetailsBox(
    private val activity: Activity,
    private val tip: TipForRemoteWork,
    private val listenToBox: ListenToTipDetailsBox
) :
    Dialog(activity), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.box_tip_details)
        window?.setBackgroundDrawable(ContextCompat.getDrawable(context, box_cornered_background))
        val view = findViewById<FrameLayout>(R.id.root)
        val contentTV: TextView = view.findViewById(R.id.content)

        val authorNameTV: TextView = view.findViewById(R.id.tipAuthorName)
        val authorRoleTV: TextView = view.findViewById(R.id.tipAuthorRole)
        val authorPictureImg: ImageView = view.findViewById(R.id.tipAuthorPicture)

        val shareOnTwitterBtn: ImageView = view.findViewById(R.id.shareOnTwitter)
        val shareOnFacebookBtn: ImageView = view.findViewById(R.id.shareOnFacebook)
        val shareOnLinkedInBtn: ImageView = view.findViewById(R.id.shareOnLinkedIn)
        val shareCopyBtn: ImageView = view.findViewById(R.id.shareCopy)

        val visitAuthorWebPage: MaterialButton = view.findViewById(R.id.goToWebsite)


        shareOnTwitterBtn.setOnClickListener(this)
        shareOnLinkedInBtn.setOnClickListener(this)
        shareOnFacebookBtn.setOnClickListener(this)
        shareCopyBtn.setOnClickListener(this)
        visitAuthorWebPage.setOnClickListener(this)


        with(tip) {

            contentTV.text = content
            authorNameTV.text = author.name
            authorRoleTV.text = author.role
            Glide.with(view.context)
                .load(author.picture)
                .circleCrop()
                .into(authorPictureImg)


        }
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.shareOnTwitter -> {
                listenToBox.onShare(tip, SocialTarget.TWITTER)
            }
            R.id.shareOnLinkedIn -> {
                listenToBox.onShare(tip, SocialTarget.LINKEDIN)
            }
            R.id.shareOnFacebook -> {
                listenToBox.onShare(tip, SocialTarget.FACEBOOK)
            }
            R.id.shareCopy -> {
            }
            R.id.goToWebsite -> {
            }
        }
    }


}


interface ListenToTipDetailsBox {
    fun onAddToFavorites(tip: TipForRemoteWork)
    fun onVisitWebSite(tip: TipForRemoteWork)
    fun onShare(tip: TipForRemoteWork, socialTarget: SocialTarget)
}

enum class SocialTarget {
    TWITTER, FACEBOOK, LINKEDIN
}