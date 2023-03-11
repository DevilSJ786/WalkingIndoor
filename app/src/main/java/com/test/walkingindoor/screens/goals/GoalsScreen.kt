package com.test.walkingindoor.screens.goals

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.test.walkingindoor.screens.home.HomeViewModel
import com.test.walkingindoor.utils.ListItem

@Composable
fun GoalsScreen(navController: NavController, homeViewModel: HomeViewModel) {
    val goals = listOf(
        "Walking",
        "Stairs,Walk",
        "Treadmill",
        "Dog Walk",
        "Power Walk",
        "Running",
        "Trekking"
    )
    var items by remember {
        mutableStateOf(goals.map {
            ListItem(title = it, isSelected = false)
        })
    }
    homeViewModel.onGoalSelected(items.filter { it.isSelected }.map { it.title })
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable { navController.popBackStack() },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
                Text(
                    text = "Goals",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Select the Goals",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.body1
                )
            }
            items(items.size) { i ->
                Surface(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 9.dp, bottom = 0.dp)
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable {
                            items = items.mapIndexed { j, item ->
                                if (i == j) {
                                    item.copy(isSelected = !item.isSelected)
                                } else item
                            }
                        },
                    color = if (!items[i].isSelected) {
                        Color(0xFFE9D7F7)
                    } else {
                        Color(0xFF7415BD)
                    },
                    shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(0.5f),
                            text = items[i].title,
                            style = MaterialTheme.typography.subtitle1,
                            fontSize = 25.sp
                        )
                        if (items[i].isSelected) {
                            Icon(
                                modifier = Modifier.weight(0.5f),
                                imageVector = Icons.Default.AdsClick,
                                contentDescription = null
                            )
                        }

                    }
                }
            }
        }
    }
}