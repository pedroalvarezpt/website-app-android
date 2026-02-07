package pt.plantalivre.app

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.webkit.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var loadingOverlay: FrameLayout
    private var isFirstLoad = true
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SITE_URL = "https://plantalivre.pt"
        private const val USER_AGENT = "PlantalivreApp/1.0"
        const val EXTRA_DEEP_LINK_URL = "deep_link_url"
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        swipeRefresh = findViewById(R.id.swipeRefresh)
        loadingOverlay = findViewById(R.id.loadingOverlay)

        setupWebView()
        setupSwipeRefresh()

        val deepLinkUrl = intent?.data?.toString()
        val initialUrl = if (deepLinkUrl != null && deepLinkUrl.contains("plantalivre.pt")) {
            deepLinkUrl
        } else {
            SITE_URL
        }
        
        webView.loadUrl(initialUrl)
    }

    private fun setupWebView() {
        webView.apply {
            setBackgroundColor(Color.parseColor("#4CAF50"))
            
            // Enable swipe refresh only when at top
            setOnScrollChangeListener { _, _, scrollY, _, _ ->
                swipeRefresh.isEnabled = scrollY == 0
            }
            
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                
                setSupportZoom(false)
                builtInZoomControls = false
                displayZoomControls = false
                
                userAgentString = USER_AGENT
                
                loadWithOverviewMode = true
                useWideViewPort = true
                cacheMode = WebSettings.LOAD_DEFAULT
                mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
                
                allowFileAccess = true
                allowContentAccess = true
            }

            addJavascriptInterface(AppBridge(this@MainActivity), "AndroidBridge")

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    if (!isFirstLoad) {
                        progressBar.visibility = View.VISIBLE
                    }
                }
                
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                    swipeRefresh.isRefreshing = false
                    
                    if (isFirstLoad) {
                        handler.postDelayed({
                            hideLoadingOverlay()
                        }, 800)
                        isFirstLoad = false
                    }
                    
                    injectAppTheme()
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    val url = request?.url?.toString() ?: return false
                    
                    if (url.contains("plantalivre.pt")) {
                        return false
                    }
                    
                    val blockedDomains = listOf<String>()
                    
                    if (blockedDomains.any { url.contains(it) }) {
                        return true
                    }
                    
                    try {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                        return true
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@MainActivity,
                            "Não foi possível abrir o link",
                            Toast.LENGTH_SHORT
                        ).show()
                        return true
                    }
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    if (newProgress < 100 && !isFirstLoad) {
                        progressBar.visibility = View.VISIBLE
                        progressBar.progress = newProgress
                    } else if (newProgress >= 100) {
                        progressBar.visibility = View.GONE
                    }
                }

                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    consoleMessage?.let {
                        android.util.Log.d(
                            "WebView",
                            "${it.message()} -- From line ${it.lineNumber()} of ${it.sourceId()}"
                        )
                    }
                    return super.onConsoleMessage(consoleMessage)
                }
            }
        }
    }
    
    private fun hideLoadingOverlay() {
        loadingOverlay.animate()
            .alpha(0f)
            .setDuration(500)
            .withEndAction {
                loadingOverlay.visibility = View.GONE
            }
            .start()
    }

    private fun setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            webView.reload()
        }
        swipeRefresh.setColorSchemeResources(
            R.color.plantalivre_primary,
            R.color.plantalivre_accent,
            R.color.plantalivre_light
        )
    }

    private fun injectAppTheme() {
        val appThemeJs = loadJavaScriptFromAssets("app-theme.js")
        
        if (appThemeJs.isNotEmpty()) {
            webView.evaluateJavascript(appThemeJs, null)
        } else {
            val fallbackJs = """
                (function() {
                    window.PLANTALIVRE_APP = true;
                    window.PLANTALIVRE_PLATFORM = 'android';
                    console.log('[PlantaLivre App] Android WebView initialized');
                })();
            """.trimIndent()
            
            webView.evaluateJavascript(fallbackJs, null)
        }
    }

    private fun loadJavaScriptFromAssets(fileName: String): String {
        return try {
            val inputStream = assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?
            
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
                stringBuilder.append('\n')
            }
            
            reader.close()
            stringBuilder.toString()
        } catch (e: Exception) {
            android.util.Log.e("MainActivity", "Error loading JS file: $fileName", e)
            ""
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
        webView.destroy()
    }
}
