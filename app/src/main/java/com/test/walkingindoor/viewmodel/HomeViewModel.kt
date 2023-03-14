package com.test.walkingindoor.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.test.walkingindoor.sensor.MeasurableSensor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val stepsSensor: MeasurableSensor
):ViewModel() {
    private val _selectedGoal =MutableStateFlow(emptyList<String>())
    val selectedGoal:StateFlow<List<String>> = _selectedGoal

    private val _selectedWalkTypes =  MutableStateFlow("")
    val selectedWalkTypes:  StateFlow<String> = _selectedWalkTypes


    fun onGoalSelected(goal: List<String>) {
        _selectedGoal.value = goal
    }

    fun onWalkTypesSelected(walkTypes:String) {
        _selectedWalkTypes.value = walkTypes
    }

    var isDark by mutableStateOf(false)
    var steps by mutableStateOf(0f)

    init {

        stepsSensor.startListening()
        stepsSensor.setOnSensorValuesChangedListener { step->
           steps= step[0]
            Log.d("steps", "$step: ")
        }
    }


}


