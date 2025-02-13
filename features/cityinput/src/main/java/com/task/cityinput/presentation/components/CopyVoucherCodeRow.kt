package com.task.cityinput.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.core.R
import com.task.core.mainComponents.dashedBorder

@Composable
fun CopyVoucherCodeRow(
    paddingTop: Dp
) {
    val clipboardManager = LocalClipboardManager.current
    Row(
        modifier = Modifier
            .padding(top = paddingTop)
            .background(Color(0xD0D0D80), shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .dashedBorder(
                width = 1.dp,
                color = colorResource(R.color.dark_blue),
                radius = 10.dp
            )
            .padding(start = 14.dp)
            .clickable {
                clipboardManager.setText(AnnotatedString("SC-457"))
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "SC-457",
            style = TextStyle(
                fontSize = 14.sp,
                color = colorResource(R.color.dark_blue),
                fontWeight = FontWeight.W600
            ),
        )
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                .background(colorResource(R.color.dark_blue))
                .padding(top = 15.dp, bottom = 15.dp, start = 25.dp, end = 25.dp)
        ) {
            Image(
                painterResource(R.drawable.ic_copy_text),
                contentDescription = "Copy",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(25.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewForCopyVoucherCodeRow(
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(20.dp)
    ) {
        CopyVoucherCodeRow(paddingTop = 5.dp)
    }

}