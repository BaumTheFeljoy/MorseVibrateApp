package felix.practice.morsevibrate.di

import kotlinx.serialization.UnstableDefault

/**
 * Provides the component.
 */
interface ComponentProvider {
    @UnstableDefault
    val component: ApplicationComponent
}