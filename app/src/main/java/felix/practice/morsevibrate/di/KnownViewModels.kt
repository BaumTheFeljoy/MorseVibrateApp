package felix.practice.morsevibrate.di


import felix.practice.morsevibrate.ui.MainViewModel
import kotlinx.serialization.UnstableDefault
import javax.inject.Inject
import javax.inject.Provider

/**
 * Provides the ViewModels.
 */
@UnstableDefault
class KnownViewModels @Inject constructor(
    val mainViewModel: Provider<MainViewModel>
)