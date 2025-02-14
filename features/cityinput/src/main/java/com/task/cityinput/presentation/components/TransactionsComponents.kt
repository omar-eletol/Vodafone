package com.task.cityinput.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.task.core.R


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
internal fun TransactionsItem( onItemClicked: (() -> Unit?)?) {
    Card(
        modifier = Modifier.padding(vertical = 10.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, colorResource(R.color.border_gray)),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        onClick = { onItemClicked?.invoke() }
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GlideImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(width = 78.dp, height = 78.dp),
                model =
                "https://tb-static.uber.com/prod/image-proc/processed_images/8fe82646a8a3f13b36e996a83752c618/3ac2b39ad528f8c8c5dc77c59abb683d.jpeg",
                contentDescription = "Challenge Image",
                contentScale = ContentScale.Crop,


                )
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text =  "",
                    style = TextStyle(
                        fontWeight = FontWeight.W600,
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                )
                RowWithImage(
                    title = " Rewards",
                    titleSize = 14.sp,
                    titleColor = R.color.dark_blue,
                    image = R.drawable.ic_gift_challenges_history,
                    imageSize = 18.dp,
                    titleFontWeight = FontWeight.W500
                )
//                RowVouchersAndPoints(item = item)
            }
        }
    }

}


@Composable
fun RowWithImage(
    title: String,
    titleSize: TextUnit,
    titleColor: Int,
    titleFontWeight: FontWeight,
    image: Int,
    imageSize: Dp
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painterResource(image),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(imageSize)
        )
        Text(
            text = title,
            modifier = Modifier.padding(start = 5.dp),
            style = TextStyle(
                color = colorResource(titleColor),
                fontSize = titleSize,
                fontWeight = titleFontWeight
            )
        )
    }
}

//@Composable
//fun RowVouchersAndPoints(
//    item: HistoryUi
//) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        RowWithImage(
//            title = item.points + " Points",
//            titleSize = 12.sp,
//            titleColor = R.color.text_gray,
//            image = R.drawable.ic_points_challenges_history,
//            imageSize = 13.dp,
//            titleFontWeight = FontWeight.W500
//        )
//        Surface(modifier = Modifier.width(10.dp)) {}
//        RowWithImage(
//            title = item.vouchers + " Vouchers",
//            titleSize = 12.sp,
//            titleColor = R.color.text_gray,
//            image = R.drawable.ic_vouchers_challenges_history,
//            imageSize = 13.dp,
//            titleFontWeight = FontWeight.W500
//        )
//    }
//}


@Preview
@Composable
fun PreviewForTransactionsItem(
) {
//    val item = HistoryUi(
//        id = "1",
//        challengeName = "two gift",
//        rewardsCount = "2",
//        points = "200",
//        vouchers = "3"
//    )
//
//    Box(
//        modifier = Modifier
//            .background(Color.White)
//            .padding(20.dp)
//    ) {
//        TransactionsItem(item = item, onItemClicked = null)
//    }

}
