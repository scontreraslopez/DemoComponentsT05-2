package net.iessochoa.sergiocontreras.democomponentst05_2.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.iessochoa.sergiocontreras.democomponentst05_2.model.Game
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.GameCard

@Composable
fun ReviewForm(
    games: List<Game>,
    gameSelected: Game,
    currentGameScore: Int,
    currentGameDifficulty: Int,
    onGameSelected: (Game) -> Unit,
    onRatingChanged: (Float) -> Unit,
    onDifficultyChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Difficulty: ")
            RatingBar(
                currentRating = currentGameDifficulty,
                onRatingChanged = onDifficultyChanged
            )
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Score: ")
            Spacer(modifier = Modifier.weight(1f))
            Slider(
                value = currentGameScore.toFloat(),
                onValueChange = onRatingChanged,
                steps = 5,
                valueRange = 0f..5f
            )
        }
    }
}
