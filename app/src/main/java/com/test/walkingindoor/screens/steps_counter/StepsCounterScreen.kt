package com.test.walkingindoor.screens.steps_counter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.RunCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.test.walkingindoor.component.ButtonWithColor
import com.test.walkingindoor.utils.spacing
import com.test.walkingindoor.viewmodel.HomeViewModel


@Composable
fun StepsCounterScreen(navController: NavController,homeViewModel:HomeViewModel) {
    val state=homeViewModel.uiState.value
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            Row(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.medium)
            ) {
                Icon(
                    modifier = Modifier
                        .clickable { navController.popBackStack() },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
                Text(
                    text = "Steps Counter",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    ) {
        StepsItem(modifier = Modifier.padding(it),state)
    }

}


@Composable
fun StepsItem(modifier: Modifier = Modifier,state: StepCounterUIState) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(spacing.small)
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.CenterVertically),
                imageVector = Icons.Default.RunCircle,
                contentDescription = null
            )
            Text(
                text = "Continue running?",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )
        }
        Text(
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
            text = "Session Details",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6
        )

        LazyVerticalGrid(
            horizontalArrangement =Arrangement.spacedBy(spacing.small),
            verticalArrangement = Arrangement.spacedBy(spacing.medium),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
               horizontal = 16.dp
            )
        ) {
            item {
                SessionCard(type = "Distance(Kms)", count = "${state.distance} Km") {
                }
            }
            item {
                SessionCard(type = "Calories burned", count = "500 Cal") {
                }
            }
            item {
                SessionCard(type = "Avg.Pace(min/Km)", count = "30 Min") {
                }
            }
            item {
                SessionCard(type = "Steps", count = "${state.steps}") {
                }
            }
            item {
                SessionCard(type = "Avg.Speed(Km/Hr)", count = "5 Km/Hr") {
                }
            }
            item {
                SessionCard(type = "Avg.Heart.Rt(BPM)", count = "85") {
                }
            }
            item {
                SessionCard(type = "Weight Loosed", count = "0.25 kg") {
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(spacing.medium)
        ) {
            ButtonWithColor(
                modifier = Modifier
                    .weight(0.5f), color = Color.Red, text = "END"
            ) {

            }
            ButtonWithColor(
                modifier = Modifier
                    .weight(0.5f), color = Color.Blue, text = "RESUME"
            ) {

            }
        }
    }
}

@Composable
@Preview
fun SessionCard(
    modifier: Modifier = Modifier,
    type: String = "Distance",
    count: String = "500Km",
    onClick: () -> Unit={}
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.LightGray,
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(spacing.small),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = type,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = count,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}