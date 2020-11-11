package com.thilawfabrice.compass.ui.displaytips

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import com.thilawfabrice.compass.R
import com.thilawfabrice.compass.domain.entities.TipForRemoteWork
import kotlinx.android.synthetic.main.activity_tip_details.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class TipDetailsActivity : AppCompatActivity(), View.OnClickListener {

    private var tip: TipForRemoteWork? = null
    private lateinit var fullscreenContentControls: LinearLayout
    private val hideHandler = Handler(Looper.getMainLooper())

    @SuppressLint("InlinedApi")
    private val hidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        mainContent.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    private val hideRunnable = Runnable { hide() }


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tip_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        val tipIntentExtra: String? = intent.getStringExtra(TIP_DETAILS_INTENT_EXTRA)
        tip = if (tipIntentExtra != null) {
            Gson().fromJson(tipIntentExtra, TipForRemoteWork::class.java)
        } else null

        tip?.let {
            with(it) {
                contentTV.text = content
                authorNameTV.text = author?.name
                val company =
                    "${author?.companyName?.run { if (this.isBlank().not()) "at $this" else "" }}"
                val role = "${author?.role} $company"

                authorRoleTV.text = role
                com.bumptech.glide.Glide.with(view.context)
                    .load(author?.picture)
                    .circleCrop()
                    .into(authorPictureImg)
            }
        }
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }


    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    companion object {

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300

        const val TIP_DETAILS_INTENT_EXTRA = "tip"
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.shareOnTwitter -> {
                // listenToBox.onShare(tip, SocialTarget.TWITTER)
            }
            R.id.shareOnLinkedIn -> {
                //  listenToBox.onShare(tip, SocialTarget.LINKEDIN)
            }
            R.id.shareOnFacebook -> {
                // listenToBox.onShare(tip, SocialTarget.FACEBOOK)
            }
            R.id.shareCopy -> {
            }
            R.id.goToWebsite -> {
            }
        }
    }
}