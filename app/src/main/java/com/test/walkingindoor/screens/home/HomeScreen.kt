package com.test.walkingindoor.screens.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.test.walkingindoor.R
import com.test.walkingindoor.component.*
import com.test.walkingindoor.navigation.AppScreens
import com.test.walkingindoor.viewmodel.HomeViewModel

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

    var visible by remember {
        mutableStateOf(false)
    }
    BottomSheetScaffold(
        sheetContent = {
            Column(
                Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomSheetDragHandle()
                Divider(
                    modifier = Modifier
                        .height(3.dp)
                        .fillMaxWidth()
                )
                Row(
                    Modifier
                        .padding(7.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
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

                AnimatedVisibility(visible = visible) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(128.dp),
                        contentPadding = PaddingValues(
                            start = 12.dp,
                            top = 0.dp,
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
                                    type = selectedGoal.let { list ->
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
                }


                Row(
                    Modifier
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
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
        backgroundColor = MaterialTheme.colors.background,
        sheetPeekHeight = 200.dp,
        scaffoldState = scaffoldState

    ) {

            visible = !sheetState.isCollapsed

        MainCircularSlider()
    }
}

@Composable
fun MainCircularSlider(modifier: Modifier = Modifier) {


    Surface(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 9.dp, bottom = 0.dp)
            .width(380.dp)
            .height(200.dp)
            .clickable {

            },
        color = Color(0xFF7415BD),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularSlider(
                modifier = Modifier.size(160.dp),
                scaleRange = 60f
            ) {
                Log.d("progress", it.toString())
            }
        }
    }

}