package net.iessochoa.sergiocontreras.democomponentst05_2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.iessochoa.sergiocontreras.democomponentst05_2.ui.theme.DemoComponentsT052Theme

@Composable
fun MainScreen() {

    val gameList = getGameList() // Cargamos la lista de juegos

    Scaffold(modifier = Modifier.fillMaxSize()
        //topBar = TODO(),
        //snackbarHost = TODO(),
        //floatingActionButton = TODO(),
        //bottomBar = TODO(),
    ) { innerPadding ->
        ReviewForm(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }

}

@Composable
fun ReviewForm(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacio entre componentes
    ) {

        Text(
            text = "Game review",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Placeholder 1: DropDownMenu (Componente custom nuestro reutilizado)
        Text(
            text = "[ AQUÍ VA EL DROPDOWN (DynamicSelectTextField) ]",
            modifier = Modifier.fillMaxWidth()
        )

        // Placeholder 2: RatingBar (Componente custom nuestro reutilizado)
        Text(
            text = "[ AQUÍ VA EL RATINGBAR (RatingBar) ]",
            modifier = Modifier.fillMaxWidth()
        )

        // Placeholder 3: Slider (Componente de Compose)
        Text(
            text = "[ AQUÍ VA EL SLIDER (Slider) ]",
            modifier = Modifier.fillMaxWidth()
        )

        // El Toast lo añadiremos en la lógica del Slider
        // El Snackbar se lanzará desde el FAB (en MainScreen)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    DemoComponentsT052Theme {
        MainScreen()
    }
}

