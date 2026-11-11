package xyz.tberghuis.floatingtimer.composables

import androidx.compose.material3.Text
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TimerText(
  text: String,
  fontSize: TextUnit,
  isBackgroundTransparent: Boolean,
  overflow: TextOverflow = TextOverflow.Clip
) {
  CompositionLocalProvider(
    LocalDensity provides Density(LocalDensity.current.density, 1f)
  ) {
    if (isBackgroundTransparent) {
      OutlinedTextWithShadow(text, fontSize, overflow)
    } else {
      Text(
        text,
        fontSize = fontSize,
        fontFamily = FontFamily.Default,
        softWrap = false,
        maxLines = 1,
        overflow = overflow,
        style = LocalTextStyle.current.copy(
          color = Color.Black,
          fontFeatureSettings = "tnum"
        ),
      )
    }
  }
}

@Composable
fun OutlinedTextWithShadow(
  text: String,
  fontSize: TextUnit,
  overflow: TextOverflow = TextOverflow.Clip,
) {
  val textStyleFill = LocalTextStyle.current.copy(
    fontSize = fontSize,
    color = Color.White,
    fontFeatureSettings = "tnum",
    drawStyle = Fill
  )
  val textStyleOutline = textStyleFill.copy(
    color = Color.Black,
    drawStyle = Stroke(
      miter = 10f,
      width = 5f,
      join = StrokeJoin.Round
    )
  )
  Box {
    Text(
      text = text,
      fontFamily = FontFamily.Default,
      softWrap = false,
      maxLines = 1,
      overflow = overflow,
      style = textStyleOutline,
    )
    Text(
      text = text,
      fontFamily = FontFamily.Default,
      softWrap = false,
      maxLines = 1,
      overflow = overflow,
      style = textStyleFill,
    )
  }
}