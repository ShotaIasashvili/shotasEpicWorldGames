package com.games.epicworld.presentation.commonui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.games.epicworld.R
import com.games.epicworld.presentation.theme.EpicWorldTheme


@Composable
fun GetGamesError(buttonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.home_screen_error_message),
            textAlign = TextAlign.Center,
            color = EpicWorldTheme.colors.background,
            style = EpicWorldTheme.typography.body1
        )
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = buttonClick
        ) {
            Text(
                text = stringResource(id = R.string.home_screen_retry),
                style = EpicWorldTheme.typography.button
            )
        }
    }
}

@Composable
fun NoResultsView() {
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.game_search_no_matches),
        style = EpicWorldTheme.typography.body1,
        color = EpicWorldTheme.colors.primary
    )
}


@Preview(showBackground = true)
@Composable
fun ErrorItemPreview() {
    GetGamesError {
        //do nothing
    }
}