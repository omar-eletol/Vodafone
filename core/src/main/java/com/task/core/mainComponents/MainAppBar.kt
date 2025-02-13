package com.task.core.mainComponents

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.task.core.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(title: String, onBackClicked: () -> Unit) {
    TopAppBar({
        CenterAlignedTopAppBar(colors = topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.Black,
        ), title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        }, navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "back"
                )
            }
        })
    }

    )

}



