package com.example.test1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test1.DataClasses.Model
import com.example.test1.logic.ResponseFilm

class ViewModelUpdate():ViewModel() {
   var liveData= MutableLiveData<ArrayList<Model>>()
   var liveData1= MutableLiveData<ArrayList<Model>>()
    private  var x=20
    private val responseFilm= ResponseFilm()
    init {
        liveData.value =responseFilm.response(0)
    }
    fun save(){

        liveData1.value =responseFilm.response(x)
    }
    fun plus(){
        x+=20
    }

}