package com.example.progresswebview

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * @author xiayiye5
 * 2020年6月5日14:47:18
 */
class KotlinWebViewActivity : AppCompatActivity() {
    private var pbAd: ProgressBar? = null
    private var wvInternetPage: WebView? = null
    private var tvWebTitle: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN or
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED
        )
        setContentView(R.layout.activity_webview_browser)
        var loadUrl = intent.getStringExtra("load_url")
        loadUrl = "https://blog.csdn.net/xiayiye5/category_7513606.html"
        var title = intent.getStringExtra("title")
        title = "下一页5|git的使用"
        wvInternetPage = findViewById(R.id.pb_webView)
        tvWebTitle = findViewById(R.id.tv_web_title)
        pbAd = findViewById(R.id.pb_ad)
        WebViewOption(loadUrl, title)
    }

    private fun WebViewOption(loadUrl: String, title: String) {
        tvWebTitle!!.text = title
        val settings = wvInternetPage!!.settings
        settings.javaScriptEnabled = true
        //允许打开js新窗口
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.domStorageEnabled = true
        wvInternetPage!!.webViewClient = MyWebViewClient()
        wvInternetPage!!.webChromeClient = MyChromeWebViewClient()
        wvInternetPage!!.loadUrl(loadUrl)
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }

        override fun onPageFinished(view: WebView, url: String) {
            tvWebTitle!!.text = view.title
            super.onPageFinished(view, url)
        }
    }

    private inner class MyChromeWebViewClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            if (newProgress == 100) {
                pbAd!!.visibility = View.INVISIBLE
            } else {
                if (pbAd!!.visibility == View.GONE) {
                    pbAd!!.visibility = View.VISIBLE
                }
                pbAd!!.progress = newProgress
            }
            super.onProgressChanged(view, newProgress)
        }
    }

    override fun onBackPressed() {
        if (wvInternetPage!!.canGoBack()) {
            wvInternetPage!!.goBack()
            return
        }
        super.onBackPressed()
    }
}