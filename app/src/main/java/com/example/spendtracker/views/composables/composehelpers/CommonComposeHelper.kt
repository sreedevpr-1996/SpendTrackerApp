package com.example.spendtracker.views.composables.composehelpers

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.spendtracker.R
import com.example.spendtracker.constants.SizeConstants
import kotlinx.coroutines.flow.SharedFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun InteractiveTextWithArrow(
    text: String,
    arrowClicked: () -> Unit,
    fontSize: TextUnit,
    modifier: Modifier,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CustomText(text, fontSize = fontSize)
        Image(
            Icons.AutoMirrored.Filled.ArrowForward,
            stringResource(R.string.empty),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = modifier.clickable {
                arrowClicked()
            })

    }
}

@Composable
fun EditInfo(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    isNumeric: Boolean = false,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    clickAction: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    TextField(
        trailingIcon = trailingIcon,
        readOnly = readOnly,
        value = value,
        onValueChange = { onValueChange(it) },
        interactionSource = interactionSource,
        label = { CustomText(title, color = Color(0xFFBB86FC)) },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color(0xFFBB86FC),
            unfocusedIndicatorColor = Color(0xFF9A67EA),
            cursorColor = Color.White
        ),
        modifier = Modifier
            .padding(SizeConstants.dp25, SizeConstants.dp12)
            .fillMaxWidth(),
        singleLine = true,
        keyboardOptions = if (isNumeric) {
            KeyboardOptions(keyboardType = KeyboardType.Number)
        } else {
            KeyboardOptions.Default
        }
    )
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Release) {
                // ✅ handle click here
                clickAction()
            }
        }
    }


}

@Composable
fun CustomButton(text: String, submit: () -> Unit, isDisabled: Boolean) {

    OutlinedButton(
        onClick = { submit() }, enabled = !isDisabled, colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.White
        ), border = BorderStroke(SizeConstants.borderStroke, Color.White), modifier = Modifier
            .padding(SizeConstants.dp25, SizeConstants.dp08)
            .fillMaxWidth(), shape = RoundedCornerShape(SizeConstants.buttonBorderRadius)

    ) {
        CustomText(text, fontSize = SizeConstants.mediumFont)
    }
}

@Composable
fun SetSnackBar(
    snackBarMessageFlow: SharedFlow<String>,
    snackBarHostState: SnackbarHostState,
    snackBarShown: () -> Unit = {},
    snackBarStarted: () -> Unit = {},

    ) {
    LaunchedEffect(snackBarMessageFlow) {
        snackBarMessageFlow.collect { message ->
            snackBarStarted()
            val showSnackBar = snackBarHostState.showSnackbar(message)

            when (showSnackBar) {
                SnackbarResult.Dismissed -> snackBarShown()
                SnackbarResult.ActionPerformed -> snackBarShown()
            }
        }
    }
}

@Composable
fun CustomText(
    text: String,
    fontSize: TextUnit = TextUnit.Unspecified,
    color: Color = Color.White,
    fontWeight: FontWeight = FontWeight.Normal,
    font: Font = Font(R.font.aovel),
    letterSpacing: TextUnit = 1.5.sp,
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontFamily = FontFamily(font),
        fontWeight = fontWeight, letterSpacing = letterSpacing
    )

}

@Composable
fun VerticalSpacer(height: Dp = SizeConstants.defaultSpacerHeight) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun HorizontalSpacer(width: Dp = SizeConstants.defaultSpacerHeight) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun CustomDateTimeField(
    title: String,
    selectedDateTime: Long?,
    onDateTimeSelected: (Long) -> Unit,
) {
    val dateTimeFormat = remember {
        SimpleDateFormat(
            "dd/MM/yyyy HH:mm",
            Locale.getDefault()
        )
    }
    val calendar = Calendar.getInstance()
    val context = LocalContext.current
    val dateTimeText = selectedDateTime?.let { dateTimeFormat.format(it) } ?: ""
    val openPicker = {
        DatePickerDialog(
            context,
            { _, year, month, day ->

                TimePickerDialog(
                    context,
                    { _, hour, minute ->
                        calendar.set(year, month, day, hour, minute, 0)
                        val timestamp = calendar.timeInMillis
                        onDateTimeSelected(timestamp)
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
                ).show()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.maxDate = System.currentTimeMillis()
        }.show()
    }

    EditInfo(
        clickAction = { openPicker() },
        title = title,
        value = dateTimeText,
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = {
                openPicker()
            }) {
                Icon(
                    Icons.Filled.DateRange,
                    stringResource(R.string.empty)
                )
            }

        })

}
