package com.task.cityinput.presentation.bottomSheets

import android.os.Bundle
import android.util.LayoutDirection
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.layoutDirection
import com.task.cityinput.presentation.components.PointsCard
import com.task.cityinput.presentation.components.VoucherCard
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class ChallengeDetailsBottomSheet : BottomSheetDialogFragment() {

    private val isArabicView get() = (Locale.getDefault().layoutDirection == LayoutDirection.RTL)
    private val lang get() = if (isArabicView) "ar" else "en"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Box(Modifier.safeDrawingPadding()) {
                    ChallengesDetailsScreen()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

@Composable
private fun ChallengesDetailsScreen(
) {
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(1.dp))
                .background(color = Color(0xFFB1B1B1))
                .size(width = 44.dp, height = 2.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {}
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "Rewards", style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        )
        VoucherCard(paddingTop = 15.dp)
        PointsCard(paddingTop = 15.dp)

    }
}


@Preview
@Composable
fun Preview(
) {
    ChallengesDetailsScreen()
}

