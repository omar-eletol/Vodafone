package com.task.cityinput.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.core.R

@Composable
fun VoucherRowChallengesDetails(
    title: Int,
    voucherState: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RowWithImage(
            stringResource(title),
            titleSize = 16.sp,
            titleColor = R.color.black,
            image = R.drawable.ic_voucher_challenges_details,
            imageSize = 18.dp,
            titleFontWeight = FontWeight.W500
        )
        Text(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(32.dp))
                .background(color = Color(0x33F1416C))
                .padding(vertical = 8.dp, horizontal = 13.dp),
            text = voucherState,
            style = TextStyle(
                fontSize = 12.sp,
                color = Color(0xFFF1416C),
                fontWeight = FontWeight.W500
            )
        )
    }
}

@Preview
@Composable
fun PreviewForVoucherRowChallengesDetails(
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(20.dp)
    ) {
        VoucherRowChallengesDetails(voucherState = "Expired", title = R.string.voucher)
    }
}
