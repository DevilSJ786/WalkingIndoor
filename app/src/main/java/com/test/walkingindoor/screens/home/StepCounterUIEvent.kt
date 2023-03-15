package com.test.walkingindoor.screens.home

sealed class StepCounterUIEvent {
    object StartButtonClicked : StepCounterUIEvent()
    object StopButtonClicked : StepCounterUIEvent()
}