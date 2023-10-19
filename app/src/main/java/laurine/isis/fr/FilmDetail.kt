package laurine.isis.fr

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FilmDetailScreen(film: Movie) {
    // Utilisez les détails du film pour afficher les informations que vous souhaitez
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Titre: ${film.title}", fontWeight = FontWeight.Bold)
        Text(text = "Date de sortie: ${film.release_date}")
        Text(text = "Description: ${film.overview}")
        // Ajoutez d'autres informations sur le film ici
    }
}
