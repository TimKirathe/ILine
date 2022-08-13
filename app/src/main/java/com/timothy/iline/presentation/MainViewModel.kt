package com.timothy.iline.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timothy.iline.data.api.RetrofitInstance
import com.timothy.iline.domain.modal.User
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _reg_stat = MutableLiveData<RegStatus>(RegStatus(name="", number=""))
    val reg_status: LiveData<RegStatus> = _reg_stat


    fun onNameChanged(name:String){
        _reg_stat.value = reg_status.value?.copy(
            name = name
        )
    }

    //reg_status = name = josef, number 8789

    fun onNumberChanged(number:String){
        _reg_stat.value = reg_status.value?.copy(
            number = number
        )
    }

    fun onSubmit(user: User){
            viewModelScope.launch {
                RetrofitInstance.api.saveUser(user)

            }
    }

}

data class RegStatus(
    val name:String,
    val number:String,
)