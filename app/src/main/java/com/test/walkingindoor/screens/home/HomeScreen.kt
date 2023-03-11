package com.test.walkingindoor.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.test.walkingindoor.R
import com.test.walkingindoor.component.BottomSheetDragHandle
import com.test.walkingindoor.component.ButtonWithColor
import com.test.walkingindoor.component.CardItem
import com.test.walkingindoor.component.DistanceCardItem
import com.test.walkingindoor.navigation.AppScreens

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {
    val selectedGoal by homeViewModel.selectedGoal.collectAsState(emptyList())
    val selectedWalkTypes by homeViewModel.selectedWalkTypes.collectAsState("")


    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        sheetContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomSheetDragHandle()

                Row(
                    Modifier
                        .padding(7.dp)
                        .weight(0.5f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CardItem(
                        name = "Music ",
                        type = "Spotify",
                        id = R.drawable.baseline_music_note_24
                    ) {}
                    DistanceCardItem(
                        name = "Distance",
                        id = R.drawable.baseline_horizontal_distribute_24
                    )
                }

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(128.dp),
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        top = 16.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    ),
                    content = {
                        item {
                            CardItem(
                                name = "Mode ",
                                type = "Indoor",
                                id = R.drawable.baseline_home_24,
                                onClick = {})
                        }
                        item {
                            CardItem(
                                name = "Goals",
                                type = selectedGoal.let { list->
                                    list.ifEmpty { "Select Goals" }
                                }.toString(),
                                id = R.drawable.round_filter_vintage_24,
                                onClick = { navController.navigate(route = AppScreens.GoalsScreen.name) })
                        }
                        item {
                            CardItem(
                                name = "Types",
                                type = selectedWalkTypes.let {
                                    it.ifBlank { "Select Types" }
                                },
                                id = R.drawable.baseline_merge_type_24,
                                onClick = { navController.navigate(route = AppScreens.WalkingtypesScreen.name) })
                        }

                    }
                )

                Row(
                    Modifier
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ButtonWithColor(
                        modifier = Modifier.weight(0.5f),
                        color = Color.Green,
                        text = "SCHEDULE"
                    ) {

                    }
                    ButtonWithColor(
                        modifier = Modifier.weight(0.5f),
                        color = Color.Blue,
                        text = "START"
                    ) {

                    }
                }

            }
        },
        sheetBackgroundColor = Color.Gray,
        backgroundColor = Color.Blue,
        sheetPeekHeight = 200.dp,
        scaffoldState = scaffoldState

    ) {
        Log.d("TAG", " MyContent:$selectedGoal")
        Text(modifier = Modifier.padding(it), text = selectedGoal.toString())
    }
}