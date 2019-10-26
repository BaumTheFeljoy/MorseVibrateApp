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
        viewModelScope.launch {
            morseCodes.postValue("")
        }
    }
}