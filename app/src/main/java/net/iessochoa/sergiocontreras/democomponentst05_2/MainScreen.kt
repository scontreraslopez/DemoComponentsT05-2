package net.iessochoa.sergiocontreras.democomponentst05_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.democomponentst05_2.data.getGameList
import net.iessochoa.sergiocontreras.democomponentst05_2.model.Game
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.components.ReviewForm
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.theme.DemoComponentsT052Theme
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.theme.Typography

@Composable
fun MainScreen() {

    val gameList = getGameList() // Cargamos la lista de juegos
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var currentGameScore by remember { mutableFloatStateOf(0f) }
    var currentDifficulty by remember { mutableIntStateOf(0) }
    var gameSelected by remember { mutableStateOf(gameList[0])} //Game


    val fabOnClick: () -> Unit = {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "Me encanta el juego ${gameSelected.title}",
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Game review",
                    style = Typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
            }

        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = fabOnClick
            ) {
                Icon(Icons.Default.Favorite, contentDescription = "Add")
            }
        }
        //bottomBar = TODO(),
    ) { innerPadding ->
        Column {
            ReviewForm(
                games = gameList,
                gameSelected = gameSelected,
                currentGameScore = currentGameScore.toInt(),
                currentGameDifficulty = currentDifficulty,
                onGameSelected = {
                    gameSelected = it
                },
                onRatingChanged = { currentGameScore = it },
                onDifficultyChanged = { currentDifficulty = it },
                modifier = Modifier
                    .padding(innerPadding)
            )
            Spacer(modifier = Modifier.weight(1f)) //El trucazo de la separacion
            SummaryPanel()
        }
    }

}


@Composable
fun SummaryPanel(
    modifier: Modifier = Modifier,
    selectedGame: Game? = null,
    difficulty: Int = 0,
    score: Int = 0
) {
    val selectedGameTitle = selectedGame?.title ?: "No game selected"

    Column(
        modifier = modifier
    ) {
        Text("Review para el juego $selectedGameTitle")
        Text("Dificultad: $difficulty/5")
        Text("Puntuación: $score/5")
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    DemoComponentsT052Theme {
        MainScreen()
    }
}
