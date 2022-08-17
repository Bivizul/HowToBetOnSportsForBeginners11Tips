@file:Suppress("DEPRECATION")

package com.bivizul.howtobetonsportsforbeginners11tips.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import com.bivizul.howtobetonsportsforbeginners11tips.R
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.DEF
import com.bivizul.howtobetonsportsforbeginners11tips.data.Constant.KEY
import im.delight.android.webview.AdvancedWebView

class PredMainActivity : ComponentActivity(), AdvancedWebView.Listener {

    lateinit var predMain: AdvancedWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pred_main)

        val url = intent.getStringExtra(KEY) ?: DEF

        predMain = findViewById<View>(R.id.predMain) as AdvancedWebView

        predMain.webViewClient = WebViewClient()
        predMain.webChromeClient = MyChromeClient()

        predMain.setListener(this, this)
        predMain.setMixedContentAllowed(false)

        setSettings()

        if (savedInstanceState == null) {
            predMain.post {
                kotlin.run { predMain.loadUrl(url) }
            }
        }

        predMain.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.action == MotionEvent.ACTION_UP &&
                predMain.canGoBack()
            ) {
                predMain.goBack()
                return@OnKeyListener true
            }
            false
        })

    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        predMain.onResume()

    }

    @SuppressLint("NewApi")
    override fun onPause() {
        predMain.onPause()

        super.onPause()
    }

    override fun onDestroy() {
        predMain.onDestroy()
        super.onDestroy()
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        predMain.onActivityResult(requestCode, resultCode, intent)
    }

    override fun onBackPressed() {
        if (!predMain.onBackPressed()) {
            return
        }
        finishAndRemoveTask()
        System.exit(0)
    }


    override fun onPageStarted(url: String?, favicon: Bitmap?) {}

    override fun onPageFinished(url: String?) {}

    override fun onPageError(errorCode: Int, description: String?, failingUrl: String?) {}

    override fun onDownloadRequested(
        url: String?,
        suggestedFilename: String?,
        mimeType: String?,
        contentLength: Long,
        contentDisposition: String?,
        userAgent: String?,
    ) {
    }

    override fun onExternalPageRequest(url: String?) {}

    @SuppressLint("SetJavaScriptEnabled")
    private fun setSettings() {
        val webSettings = predMain.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.allowFileAccess = true
        webSettings.domStorageEnabled = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.useWideViewPort = true
        webSettings.setSupportZoom(true)
//        webSettings.setAppCacheEnabled(true)
        webSettings.cacheMode
        webSettings.userAgentString = webSettings.userAgentString.replace("; wv", "")
    }

    var filePathCallback: ValueCallback<Array<Uri>>? = null
    private val REQUEST_CODE = 100

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        predMain.saveState(outState)
    }


    inner class MyChromeClient : WebChromeClient() {

        override fun onShowFileChooser(
            view: WebView,
            filePath: ValueCallback<Array<Uri>>,
            fileChooserParams: FileChooserParams,
        ): Boolean {
            filePathCallback = filePath
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
            startActivityForResult(intent, REQUEST_CODE)
            return true
        }


        private var mCustomView: View? = null
        private var mCustomViewCallback: CustomViewCallback? = null
        private var mOriginalOrientation = 0
        private var mOriginalSystemUiVisibility = 0

        override fun getDefaultVideoPoster(): Bitmap? {
            return if (mCustomView == null) {
                null
            } else BitmapFactory.decodeResource(
                this@PredMainActivity.applicationContext.resources,
                2130837573
            )
        }

        override fun onHideCustomView() {
            (this@PredMainActivity.window.decorView as FrameLayout).removeView(mCustomView)
            mCustomView = null
            this@PredMainActivity.window.decorView.systemUiVisibility = mOriginalSystemUiVisibility
            this@PredMainActivity.requestedOrientation = mOriginalOrientation
            mCustomViewCallback!!.onCustomViewHidden()
            mCustomViewCallback = null
        }

        override fun onShowCustomView(
            paramView: View?,
            paramCustomViewCallback: CustomViewCallback?,
        ) {
            if (mCustomView != null) {
                onHideCustomView()
                return
            }
            mCustomView = paramView
            mOriginalSystemUiVisibility = this@PredMainActivity.window.decorView.systemUiVisibility
            mOriginalOrientation = this@PredMainActivity.requestedOrientation
            mCustomViewCallback = paramCustomViewCallback
            (this@PredMainActivity.window.decorView as FrameLayout).addView(
                mCustomView,
                FrameLayout.LayoutParams(-1, -1)
            )
            this@PredMainActivity.window.decorView.systemUiVisibility =
                3846 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}