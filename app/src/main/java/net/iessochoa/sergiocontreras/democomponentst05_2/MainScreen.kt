package net.iessochoa.sergiocontreras.democomponentst05_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import net.iessochoa.sergiocontreras.democomponentst05_2.data.getGameList
import net.iessochoa.sergiocontreras.democomponentst05_2.model.Game
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.GameCard
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.components.CustomDropdownMenu
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.components.RatingBar
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.theme.DemoComponentsT052Theme
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.theme.Typography

@Composable
fun MainScreen() {

    val gameList = getGameList() // Cargamos la lista de juegos
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var currentGameScore by remember { mutableIntStateOf(0) }
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
                currentGameScore = currentGameScore,
                onGameSelected = {
                    gameSelected = it
                },
                onScoreChanged = { currentGameScore = it },
                modifier = Modifier
                    .padding(innerPadding)
            )
            Spacer(modifier = Modifier.weight(1f)) //El trucazo de la separacion
            SummaryPanel()
        }
    }

}

@Composable
fun ReviewForm(
    games: List<Game>,
    gameSelected: Game,
    modifier: Modifier = Modifier,
    onGameSelected: (Game) -> Unit = {},
    onScoreChanged: (Int) -> Unit = {},
    currentGameScore: Int,
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre componentes
    ) {

        val optionsList = games.map { it.title }

        Spacer(modifier = Modifier.height(16.dp))

        // Placeholder 1: DropDownMenu (Componente custom nuestro reutilizado)
        CustomDropdownMenu(
            options = optionsList,
            selected = gameSelected.title,
            onValueChanged = { title ->
                onGameSelected(games.find { it.title == title }!!)
            },
            label = "Games"
        )

        //Esto aqui mal metido a fuego para que lo movamos donde toca
        GameCard(game = gameSelected)

        // Placeholder 2: RatingBar (Componente custom nuestro reutilizado)
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Difficulty: ")
            RatingBar(
                currentRating = currentGameScore,
                onRatingChanged = onScoreChanged
            )
        }


        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Score: ")
            Spacer(modifier= Modifier.weight(1f))
            Slider(
                value = 3f,
                onValueChange = {},
                steps = 5,
                valueRange = 0f..5f
            )
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
