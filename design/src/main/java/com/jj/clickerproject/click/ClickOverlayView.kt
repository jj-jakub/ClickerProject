package com.jj.clickerproject.click

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jj.clickerproject.design.gridMultiple

val overlayCornerShape = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomEnd = 8.dp,
    bottomStart = 8.dp,
)

@Composable
fun ClickOverlayView(
    isActive: Boolean,
    onStartClicking: () -> Unit,
    onStopClicking: () -> Unit,
) {
    Row(
        modifier = Modifier
            .clip(shape = overlayCornerShape),
    ) {
        if (!isActive) {
            Row(
                modifier = Modifier
                    .background(Color.Green)
                    .padding(
                        horizontal = gridMultiple(i = 2),
                        vertical = gridMultiple(i = 1),
                    )
                    .clickable {
                        onStartClicking()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(gridMultiple(i = 1)),
            ) {
                Text(
                    color = Color.White,
                    text = "Start",
                )
            }
        } else {
            Column(
                modifier = Modifier.width(IntrinsicSize.Max),
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .clickable {
                            onStopClicking()
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .padding(
                                horizontal = gridMultiple(i = 2),
                                vertical = gridMultiple(i = 1),
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(gridMultiple(i = 1)),
                    ) {
                        Text(
                            color = Color.White,
                            text = "Stop",
                        )
                    }
                }
            }
        }
    }
}

@Preview(device = "spec:width=1280dp,height=800dp,dpi=480")
@Composable
fun PreviewRemoteControlOverlayActiveView() {
    ClickOverlayView(
        isActive = true,
        onStartClicking = {},
        onStopClicking = {},
    )
}

@Preview(device = "spec:width=1280dp,height=800dp,dpi=480")
@Composable
fun PreviewRemoteControlOverlayInActiveView() {
    ClickOverlayView(
        isActive = false,
        onStartClicking = {},
        onStopClicking = {},
    )
}