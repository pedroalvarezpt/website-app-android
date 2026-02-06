package pt.plantalivre.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Splash Activity
 * 
 * Handles:
 * - App launch with splash screen
 * - Deep links (plantalivre.pt URLs)
 * - Quick transition to MainActivity
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Get deep link URL if present
        val deepLinkUrl = intent?.data?.toString()
        
        // Start MainActivity
        val mainIntent = Intent(this, MainActivity::class.java).apply {
            // Pass deep link URL to MainActivity
            if (deepLinkUrl != null) {
                putExtra(MainActivity.EXTRA_DEEP_LINK_URL, deepLinkUrl)
            }
            
            // Clear back stack
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        startActivity(mainIntent)
        finish()
    }
}
