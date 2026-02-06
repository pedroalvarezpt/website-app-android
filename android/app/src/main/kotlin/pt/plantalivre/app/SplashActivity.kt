package pt.plantalivre.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

/**
 * Simple Splash Activity
 * Shows logo for 500ms then navigates to MainActivity
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // No setContentView - uses theme background
        
        // Navigate to main after short delay
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToMain()
        }, 500) // 500ms delay
    }
    
    private fun navigateToMain() {
        val deepLinkUrl = intent?.data?.toString()
        
        val mainIntent = Intent(this, MainActivity::class.java).apply {
            if (deepLinkUrl != null) {
                putExtra(MainActivity.EXTRA_DEEP_LINK_URL, deepLinkUrl)
            }
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        startActivity(mainIntent)
        finish()
    }
}
