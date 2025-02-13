package com.task.cityinput.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.core.R

@Composable
fun VoucherCard(
    paddingTop: Dp,
) {
    Card(
        modifier = Modifier
            .padding(top = paddingTop)
            .clip(shape = RoundedCornerShape(12.dp))
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(
            1.dp, colorResource(R.color.border_gray)
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            VoucherRowChallengesDetails(voucherState = "Expired", title = R.string.voucher)
            CopyVoucherCodeRow(paddingTop = 15.dp)
            Row(modifier = Modifier.padding(top = 13.dp)) {
                RowWithImage(
                    title = stringResource(R.string.expire_at),
                    titleSize = 12.sp,
                    titleColor = R.color.text_gray,
                    image = R.drawable.ic_clock,
                    imageSize = 18.dp,
                    titleFontWeight = FontWeight.W400
                )
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = "6 Nov 2023",
                    style = TextStyle(
                        color = colorResource(id = R.color.text_gray),
                        fontWeight = FontWeight.W600
                    )
                )
            }

        }
    }
}





@Preview
@Composable
fun Preview(
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(20.dp)
    ) {
        VoucherCard(paddingTop = 10.dp)
    }

}
