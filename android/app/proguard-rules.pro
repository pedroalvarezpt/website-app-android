# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep WebView JavaScript interface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Keep AppBridge class (used by JavaScript)
-keep class pt.plantalivre.app.AppBridge { *; }

# Keep WebView classes
-keep class android.webkit.** { *; }
-keep class androidx.webkit.** { *; }

# Keep crash reporting
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Remove logging in release
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# Keep AndroidX
-keep class androidx.** { *; }
-dontwarn androidx.**

# Keep Material Design
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**
