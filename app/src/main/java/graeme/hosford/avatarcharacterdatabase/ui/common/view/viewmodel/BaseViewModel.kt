package graeme.hosford.avatarcharacterdatabase.ui.common.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val errorMutable = MutableLiveData<Boolean>()
    val errorLiveData: LiveData<Boolean>
        get() = errorMutable
}