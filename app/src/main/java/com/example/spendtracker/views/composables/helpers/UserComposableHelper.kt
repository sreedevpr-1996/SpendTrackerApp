package com.example.spendtracker.views.composables.helpers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import com.example.spendtracker.R
import com.example.spendtracker.constants.SizeConstants
import com.example.spendtracker.database.tables.UserEntity

@Composable
fun WelcomeUser(
    userEntity: UserEntity?,
    arrowClicked: () -> Unit,
    fontSize: TextUnit,
    modifier: Modifier,
) {
    if (userEntity != null) {
        val firstName = userEntity.firstName
        InteractiveTextWithArrow(
            text = stringResource(R.string.continue_as, firstName),
            arrowClicked = { arrowClicked() }, fontSize, modifier
        )
        VerticalSpacer()
        CustomText(text = stringResource(R.string.or), fontSize = fontSize)
        VerticalSpacer()

    } else {
        CustomText(
            text = stringResource(R.string.no_logged_in_user),
            fontSize = SizeConstants.mediumFont
        )
    }
}

@Composable
fun CreateUser(arrowClicked: () -> Unit, fontSize: TextUnit, modifier: Modifier) {
    InteractiveTextWithArrow(
        text = stringResource(R.string.create_new_user),
        arrowClicked = { arrowClicked() }, fontSize, modifier
    )
}