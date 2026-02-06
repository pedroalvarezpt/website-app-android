package pt.plantalivre.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

/**
 * Splash Activity with Android 12+ Splash Screen API
 * 
 * Features:
 * - Native splash screen with logo
 * - Smooth transition to MainActivity
 * - Deep link support
 * - Backward compatible to API 24
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Install splash screen (Android 12+ native, backported to older versions)
        val splashScreen = installSplashScreen()
        
        super.onCreate(savedInstanceState)
        
        // Keep splash visible while loading
        splashScreen.setKeepOnScreenCondition { false }
        
        // Navigate to MainActivity
        navigateToMain()
    }
    
    private fun navigateToMain() {
        // Get deep link URL if present
        val deepLinkUrl = intent?.data?.toString()
        
        // Start MainActivity
        val mainIntent = Intent(this, MainActivity::class.java).apply {
            // Pass deep link URL to MainActivity
            if (deepLinkUrl != null) {
                putExtra(MainActivity.EXTRA_DEEP_LINK_URL, deepLinkUrl)
            }
            
            // Preserve intent flags
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        startActivity(mainIntent)
        finish()
    }
}
