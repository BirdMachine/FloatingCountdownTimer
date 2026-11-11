package xyz.tberghuis.floatingtimer.composables

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Rectangle
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import xyz.tberghuis.floatingtimer.R
import xyz.tberghuis.floatingtimer.viewmodels.TimerShapeChoiceVm

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ColumnScope.TimerShapeChoice(vm: TimerShapeChoiceVm) {
  Row(
    modifier = Modifier
      .align(Alignment.CenterHorizontally)
      .padding(10.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(stringResource(R.string.shape_label))
    RadioButton(
      selected = vm.timerShape == "circle",
      onClick = { vm.timerShape = "circle" },
      modifier = Modifier.testTag("CircleRadio"),
    )
    Icon(
      Icons.Outlined.Circle,
      contentDescription = stringResource(R.string.circle),
      modifier = Modifier.size(40.dp)
    )
    RadioButton(
      // Keep the legacy label shape compatible with existing saved timers.
      selected = vm.timerShape == "rectangle" || vm.timerShape == "label",
      onClick = { vm.timerShape = "rectangle" },
      modifier = Modifier
        .semantics { testTagsAsResourceId = true }
        .testTag("RectangleRadio"),
    )
    Icon(
      Icons.Outlined.Rectangle,
      contentDescription = stringResource(R.string.rectangle),
      modifier = Modifier.size(40.dp)
    )
  }
  OutlinedTextField(
    value = vm.label,
    onValueChange = { vm.label = it },
    modifier = Modifier
      .align(Alignment.CenterHorizontally)
      .padding(horizontal = 10.dp, vertical = 5.dp)
      .widthIn(max = 300.dp)
      .semantics { testTagsAsResourceId = true }
      .testTag("Label"),
    label = { Text(stringResource(R.string.optional_timer_label)) },
    singleLine = true,
  )
}
