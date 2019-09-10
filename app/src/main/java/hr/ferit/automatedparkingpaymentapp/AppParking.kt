package hr.ferit.automatedparkingpaymentapp

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class AppParking : Application() {

    companion object {
        lateinit var instance: AppParking
            private set

        fun getAppContext(): Context = instance.applicationContext

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}