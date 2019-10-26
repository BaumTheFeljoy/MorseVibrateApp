package felix.practice.morsevibrate

import android.app.Application
import felix.practice.morsevibrate.di.ComponentProvider
import felix.practice.morsevibrate.di.DaggerApplicationComponent
import kotlinx.serialization.UnstableDefault
import timber.log.Timber

@UnstableDefault
class MorseApplication : Application(), ComponentProvider {

    override val component by lazy {
        DaggerApplicationComponent.factory().create(applicationContext, this)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}