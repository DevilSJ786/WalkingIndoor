package com.test.walkingindoor.screens.walking_types

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.test.walkingindoor.component.WalkingTypeItem
import com.test.walkingindoor.screens.home.HomeViewModel

@Composable
fun WalkingtypesScreen(
    navController: NavController, homeViewModel: HomeViewModel
) {
    val itemList = listOf(
        "Walking",
        "Stairs,Walk",
        "Treadmill",
        "Dog Walk",
        "Power Walk",
        "Running",
        "Trekking"
    )

    SelectItem(
        navController = navController,
        list = itemList
    ) { homeViewModel.onWalkTypesSelected(it) }

}


@Composable
fun SelectItem(navController: NavController, list: List<String>, onClick: (String) -> Unit) {

    val itemSelection = remember {
        mutableStateOf(-1)
    }
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
                    text = "Walking Types",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {  Text(
                modifier = Modifier.padding(16.dp),
                text = "Select the Walking Type",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                style = MaterialTheme.typography.body1
            ) }
            items(count = list.size) { indexNumber ->
                Surface(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 9.dp, bottom = 0.dp)
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable {
                            onClick(list[indexNumber])
                            itemSelection.value =
                                if (itemSelection.value != indexNumber) indexNumber
                                else -1
                        },
                    color = if (itemSelection.value != indexNumber) {
                        Color(0xFFE9D7F7)
                    } else {
                        Color(0xFF7415BD)
                    },
                    shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .weight(0.5f),
                            text = list[indexNumber],
                            style = MaterialTheme.typography.subtitle1,
                            fontSize = 25.sp
                        )

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



