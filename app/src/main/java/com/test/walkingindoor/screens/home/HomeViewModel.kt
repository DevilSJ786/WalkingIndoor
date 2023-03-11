package com.test.walkingindoor.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.test.walkingindoor.utils.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor():ViewModel() {
    private val _selectedGoal =MutableStateFlow(emptyList<String>())
    val selectedGoal:StateFlow<List<String>> = _selectedGoal

    private val _selectedWalkTypes =  MutableStateFlow("")
    val selectedWalkTypes:  StateFlow<String> = _selectedWalkTypes

    fun onGoalSelected(goal: List<String>) {
        Log.d("TAG", "viewmodel: $goal")
        _selectedGoal.value = goal
    }

    fun onWalkTypesSelected(walkTypes:String) {
        _selectedWalkTypes.value = walkTypes
    }

}