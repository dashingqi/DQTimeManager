package com.dashingqi.dqtimemanager.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * 主页面-时间页面
 * @author zhangqi61
 * @since 2023/8/28
 */
@Composable
fun HomePageView() {

    val homeViewModel: HomeViewModel = viewModel()
    val timeState by homeViewModel.timeState.collectAsState()

    Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    OutlinedButton(
                        onClick = { },
                        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(text = "PM")
                    }

                    OutlinedButton(
                        onClick = { /*TODO*/ }, shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
                    ) {
                        Text(text = "AM")
                    }

                }
                Switch(checked = false, onCheckedChange = {})
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column {
                    Text(text = "2023/08/28(四)", modifier = Modifier.padding(bottom = 15.dp, start = 5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TimeCard(timeState.hour)
                        Text(text = ":")
                        TimeCard(timeState.minute)
                        Text(text = ":")
                        TimeCard(timeState.second)
                    }
                }
            }

        }
    }
}

@Composable
fun TimeCard(value: Int) {
    Box {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ), modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Text(text = "$value", modifier = Modifier.padding(vertical = 10.dp, horizontal = 12.dp))

        }
    }
}

@Preview
@Composable
fun HomePageViewPreview() {
    HomePageView()
}