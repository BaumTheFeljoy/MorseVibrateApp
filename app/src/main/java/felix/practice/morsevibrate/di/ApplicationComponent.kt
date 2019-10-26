package felix.practice.morsevibrate.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import kotlinx.serialization.UnstableDefault
import javax.inject.Singleton

@Component(
    modules = []
)
@Singleton @UnstableDefault
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
            @BindsInstance application: Application
        ): ApplicationComponent
    }

    val knownViewModels: KnownViewModels
}