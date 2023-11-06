package co.jonathanbernal.authjsb

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SelfApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}