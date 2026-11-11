package xyz.tberghuis.floatingtimer.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

/** A caption outside the circle keeps the timer and progress arc the same size. */
@Composable
fun LabeledTimer(
  label: String?,
  fontSize: TextUnit,
  isBackgroundTransparent: Boolean,
  content: @Composable () -> Unit
) {
  val caption = label?.trim()?.takeIf { it.isNotEmpty() }
  if (caption == null) {
    content()
    return
  }
  val maxWidth = minOf(180.dp, (LocalConfiguration.current.screenWidthDp * .9f).dp)
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    content()
    Box(
      modifier = Modifier
        .padding(top = 2.dp)
        .widthIn(max = maxWidth)
        .then(
          if (isBackgroundTransparent) Modifier
          else Modifier.background(Color.White, RoundedCornerShape(6.dp))
        )
        .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
      TimerText(
        caption,
        fontSize * .6f,
        isBackgroundTransparent,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}
