package com.test.walkingindoor.screens.home

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.test.walkingindoor.R
import com.test.walkingindoor.component.*
import com.test.walkingindoor.navigation.AppScreens
import com.test.walkingindoor.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {


    val stepsCount = homeViewModel.steps
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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    ButtonWithColor(
                        modifier = Modifier.weight(0.5f).padding(4.dp),
                        color = Color.Green,
                        text = "SCHEDULE"
                    ) {

                    }
                    ButtonWithColor(
                        modifier = Modifier.weight(0.5f).padding(4.dp),
                        color = Color.Blue,
                        text = "START"
                    ) {

                    }
                }

            }
        },
        sheetBackgroundColor = Color.Gray,
        backgroundColor =  Color.DarkGray ,
        sheetPeekHeight = 200.dp,
        scaffoldState = scaffoldState

    ) {

        visible = !sheetState.isCollapsed
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Text(modifier = Modifier.size(100.dp).align(Alignment.CenterHorizontally), text ="count is : $stepsCount")
            MainCircularSlider(){
                 navController.navigate(route = AppScreens.StepsCounterScreen.name)
            }
            DetailsCard(modifier = Modifier)
        }

    }
}

@Composable
fun MainCircularSlider(modifier: Modifier = Modifier, onClick: () -> Unit) {

    Surface(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 9.dp, bottom = 0.dp)
            .width(380.dp)
            .height(300.dp)
            .clickable {
                onClick()
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProgressBarItem(modifier = Modifier.weight(0.3f), value = 57, name = "Recommended")
                ProgressBarItem(modifier = Modifier.weight(0.3f), value = 10, name = "Goal")
                ProgressBarItem(modifier = Modifier.weight(0.3f), value = 30, name = "Achieved")
            }
        }
    }
}


@Composable
fun DetailsCard(modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.LightGray,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DetailsItem(
                type = "Distance",
                value = "5 Km",
                id = R.drawable.baseline_heart_broken_24
            ) {

            }
            DetailsItem(
                type = "Calories",
                value = "400 kal",
                id = R.drawable.baseline_heart_broken_24
            ) {

            }
            DetailsItem(
                type = "Heart Rate",
                value = "78",
                id = R.drawable.baseline_heart_broken_24
            ) {

            }
            DetailsItem(
                type = "Blood Pressure",
                value = "120/80 hhmg",
                id = R.drawable.baseline_heart_broken_24
            ) {

            }
        }
    }
}

@Composable
fun DetailsItem(
    modifier: Modifier = Modifier,
    type: String,
    value: String,
    @DrawableRes id: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .height(100.dp)
            .width(80.dp)
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = painterResource(id), contentDescription = null)
        Text(text = type, fontSize = 10.sp)
        Text(text = value, fontSize = 10.sp)
    }
}


@Composable
fun ProgressBarItem(modifier: Modifier, value: Int, name: String) {

    Column(
        modifier = modifier
            .height(80.dp)
            .width(120.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = "$value min",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        CustomProgressBar(
            Modifier
                .clip(shape = RoundedCornerShape(6.dp))
                .height(4.dp),
            100.dp,
            Color.Gray,
            Brush.horizontalGradient(listOf(Color(0xffFD7D20), Color(0xffFBE41A))),
            value
        )
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CustomProgressBar(
    modifier: Modifier,
    width: Dp,
    backgroundColor: Color,
    foregroundColor: Brush,
    percent: Int
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .width(width)
    ) {
        Box(
            modifier = modifier
                .background(foregroundColor)
                .width(width * percent / 100)
        )
    }
}

