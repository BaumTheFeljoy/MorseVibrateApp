package felix.practice.morsevibrate.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    val morseCodes: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        /**
         * Start a coroutine within the ViewModel's scope, so it gets cancelled automatically
         * when the ViewModel is no longer needed.
         */
        viewModelScope.launch {
            morseCodes.postValue("hey wenn das geht wär echt sweet")
        }
    }
}