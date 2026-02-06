# Firebase Cloud Messaging (FCM) Setup

## 📱 Implementar Push Notifications no Android

### Pré-requisitos

1. **Firebase Project** - Criar em: https://console.firebase.google.com
2. **google-services.json** - Download após adicionar Android app
3. **Package name**: `pt.plantalivre.app`

### Configuração Gradle

```gradle
// build.gradle (project level)
buildscript {
    dependencies {
        classpath 'com.google.gms:google-services:4.4.0'
    }
}

// app/build.gradle
plugins {
    id 'com.google.gms.google-services'  // Descomentar
}

dependencies {
    // Descomentar:
    implementation platform('com.google.firebase:firebase-bom:32.7.0')
    implementation 'com.google.firebase:firebase-messaging-ktx'
    implementation 'com.google.firebase:firebase-analytics-ktx'
}
```

### PushNotificationService.kt

```kotlin
package pt.plantalivre.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        
        remoteMessage.notification?.let {
            showNotification(it.title, it.body)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sendTokenToBackend(token)
    }

    private fun showNotification(title: String?, message: String?) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = "plantalivre_notifications"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "PlantaLivre Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }

    private fun sendTokenToBackend(token: String) {
        // TODO: POST to https://plantalivre.pt/api/app/push/register
        android.util.Log.d("FCM", "Token: $token")
    }
}
```

### AndroidManifest.xml

```xml
<service
    android:name=".PushNotificationService"
    android:exported="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>

<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

### Request Permission (Android 13+)

```kotlin
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
    requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1001)
}
```

### Get Token

```kotlin
FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
    if (task.isSuccessful) {
        val token = task.result
        // Send to backend
    }
}
```

---

✅ **Checklist**
- [ ] Firebase project created
- [ ] google-services.json placed in app/
- [ ] Gradle dependencies uncommented
- [ ] PushNotificationService.kt created
- [ ] AndroidManifest updated
- [ ] Notification permission requested
- [ ] Token sent to backend
- [ ] Test notification received
