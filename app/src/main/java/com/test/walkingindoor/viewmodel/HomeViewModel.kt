package com.test.walkingindoor.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.test.walkingindoor.screens.home.StepCounterUIEvent
import com.test.walkingindoor.screens.steps_counter.StepCounterUIState
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


    private val _uiState = mutableStateOf(StepCounterUIState())
    val uiState: State<StepCounterUIState> = _uiState

    fun onUIEvent(uiEvent: StepCounterUIEvent) {
        when (uiEvent) {
            is StepCounterUIEvent.StartButtonClicked -> startSteps()
            is StepCounterUIEvent.StopButtonClicked -> stopSteps()
            else -> {}
        }
    }


    fun startSteps(){
        stepsSensor.startListening()
        stepsSensor.setOnSensorValuesChangedListener { step->
            _uiState.value=_uiState.value.copy(steps = step[0].toInt(), distance =(step[0].toInt()/1408) )
        }
    }

    fun stopSteps(){
        stepsSensor.stopListening()
    }


}


