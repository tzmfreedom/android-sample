package com.freedom_man.standard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData()
}
