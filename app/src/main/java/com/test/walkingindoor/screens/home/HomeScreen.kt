package com.test.walkingindoor.screens.home

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.test.walkingindoor.utils.spacing
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
                modifier=Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(spacing.medium)
            ) {
                BottomSheetDragHandle()

                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.small)
                ) {
                    CardItem(modifier = Modifier.weight(0.5f),
                        name = "Music ",
                        type = "Spotify",
                        id = R.drawable.baseline_music_note_24
                    ) {}
                    CardItem(modifier = Modifier.weight(0.5f),
                        name = "Mode ",
                        type = "Indoor",
                        id = R.drawable.baseline_home_24,
                        onClick = {})
                }

                AnimatedVisibility(visible = visible) {
                    LazyVerticalGrid(
                        horizontalArrangement =Arrangement.spacedBy(spacing.small),
                        verticalArrangement = Arrangement.spacedBy(spacing.medium),
                        columns = GridCells.Fixed(2)
                    )
                    {

                        item {
                            CardItem(
                                name = "Goals",
                                type = if (selectedGoal.isNotEmpty()){selectedGoal[0]} else{"Select Goals"},
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
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.medium),
                ) {
                    ButtonWithColor(
                        modifier = Modifier
                            .weight(0.5f),
                        color = Color.Green,
                        text = "SCHEDULE"
                    ) {

                    }
                    ButtonWithColor(
                        modifier = Modifier
                            .weight(0.5f),
                        color = Color.Blue,
                        text = "START"
                    ) {
                        homeViewModel.onUIEvent(StepCounterUIEvent.StartButtonClicked)
                        navController.navigate(route = AppScreens.StepsCounterScreen.name)
                    }
                }

            }
        },
        sheetBackgroundColor = Color.Gray,
        backgroundColor = Color.DarkGray,
        sheetPeekHeight = 200.dp,
        scaffoldState = scaffoldState

    ) {

        visible = !sheetState.isCollapsed
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(spacing.medium),
        ) {

            MainCircularSlider() {
                navController.navigate(route = AppScreens.StepsCounterScreen.name)
            }
            DetailsCard(modifier = Modifier)
        }

    }
}

@Composable
fun MainCircularSlider(modifier: Modifier = Modifier, onClick: () -> Unit) {
    val isDuration = remember {
        mutableStateOf(true)
    }
    Surface(
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
    ) {
        Column( modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularSlider(
                modifier = Modifier.size(180.dp),
                scaleRange = 60f,
                isDuration = isDuration.value,
                onChange = {
                    Log.d("progress", it.toString())
                           },
                onChangeType = {isDuration.value=!isDuration.value}
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.medium)
            ) {
                ProgressBarItem(
                    isDuration = isDuration.value,
                    modifier = Modifier.weight(0.3f),
                    value = 57,
                    name = "Recommended"
                )
                ProgressBarItem(
                    isDuration = isDuration.value,
                    modifier = Modifier.weight(0.3f),
                    value = 10,
                    name = "Goal"
                )
                ProgressBarItem(
                    isDuration = isDuration.value,
                    modifier = Modifier.weight(0.3f),
                    value = 30,
                    name = "Achieved"
                )
            }
        }
    }
}


@Composable
fun DetailsCard(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Row(
            modifier=Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(spacing.medium)
        ) {
            DetailsItem(
                type = "Distance",
                value = "5 Km",
                id = R.drawable.total_distance
            ) {

            }
            DetailsItem(
                type = "Calories",
                value = "400 kal",
                id = R.drawable.calories
            ) {

            }
            DetailsItem(
                type = "Heart Rate",
                value = "78",
                id = R.drawable.heartrate
            ) {

            }
            DetailsItem(
                type = "Blood Pressure",
                value = "120/80 hhmg",
                id = R.drawable.pulse_rate
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
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id),
            contentDescription = null
        )
        Text( text = type, fontSize = 12.sp)
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}


@Composable
fun ProgressBarItem(modifier: Modifier, value: Int, name: String, isDuration: Boolean) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$value" + if (isDuration) " min" else " Km",
            fontSize = 12.sp,
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
            text = name,
            fontSize = 12.sp,
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

