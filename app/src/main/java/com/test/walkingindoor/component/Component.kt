package com.test.walkingindoor.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdsClick
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.walkingindoor.widgets.RoundIconButton

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    name: String,
    type: String,
    @DrawableRes id: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .width(170.dp)
            .padding(6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {

        Row(
            modifier = modifier.padding(4.dp), verticalAlignment = Alignment.Top
        ) {
            Column(modifier.padding(5.dp), verticalArrangement = Arrangement.Top) {
                Icon(
                    painter = painterResource(id), contentDescription = null
                )
            }

            Column(
                modifier = Modifier.padding(6.dp),
                verticalArrangement = Arrangement.Top,
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 6.dp),
                    text = name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = type,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

@Composable
fun ButtonWithColor(
    modifier: Modifier, color: Color, text: String, onClick: () -> Unit
) {

    Button(modifier = modifier,
        onClick = {
            onClick()
        }, colors = ButtonDefaults.buttonColors(backgroundColor = color)
    )

    {
        Text(text = text, color = Color.White)
    }
}

@Composable
fun DistanceCardItem(
    modifier: Modifier = Modifier,
    name: String,
    @DrawableRes id: Int,
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .width(170.dp)
            .padding(6.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.padding(6.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id), contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(6.dp),
                    text = name,
                    style = MaterialTheme.typography.h6
                )
            }
            Row(
                modifier = Modifier.padding(3.dp), horizontalArrangement = Arrangement.Center
            ) {
                RoundIconButton(imageVector = Icons.Default.Remove, onClick = {

                })
                Text(
                    text = "99.00",
                    modifier = Modifier
                        .padding(horizontal = 9.dp)
                        .align(Alignment.CenterVertically)
                )
                RoundIconButton(imageVector = Icons.Default.Add, onClick = {

                })
            }
        }
    }
}

@Composable
fun BottomSheetDragHandle(
    modifier: Modifier = Modifier,
    height: Dp = 24.dp,
    barWidth: Dp = 32.dp,
    barHeight: Dp = 4.dp,
    color: Color = MaterialTheme.colors.onBackground.copy(alpha = 0.1f),
) {
    Spacer(modifier = modifier
        .drawBehind {
            val barWidthPx = barWidth.toPx()
            val barHeightPx = barHeight.toPx()
            val x = size.width / 2 - barWidthPx / 2
            val y = size.height / 2 - barHeightPx / 2
            drawRoundRect(
                color = color,
                topLeft = Offset(x, y),
                size = Size(barWidthPx, barHeightPx),
                cornerRadius = CornerRadius(barHeightPx / 2)
            )
        }
        .fillMaxWidth()
        .height(height))
}


@Composable
fun WalkingTypeItem(
    modifier: Modifier = Modifier,
    type: String = "Running",
    onClick: () -> Unit = {},
) {
    val correctSelectableChipColorsState = remember() {
        mutableStateOf(false)
    }
    Surface(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, top = 9.dp, bottom = 0.dp)
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                onClick()
                correctSelectableChipColorsState.value = !correctSelectableChipColorsState.value
            },
        color = if (!correctSelectableChipColorsState.value) {
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
                text = type,
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

