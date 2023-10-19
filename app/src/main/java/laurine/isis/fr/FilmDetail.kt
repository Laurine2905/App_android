package laurine.isis.fr

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun FilmDetailScreen(filmDetail: FilmDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(enabled = true, state = rememberScrollState())
    ) {
        Text("${filmDetail.title}", fontWeight = FontWeight.Bold,style = typography.h4)
        Image(
            painter = rememberImagePainter(
                data = "https://image.tmdb.org/t/p/w500${filmDetail.backdrop_path}",
            ),
            contentDescription = filmDetail.title,
            modifier = Modifier
                .fillMaxWidth()
                .size(180.dp) // You can adjust the height as needed
        )
        Text("")
        Row () {
            Column {
                Image(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/w500${filmDetail.poster_path}",
                    ),
                    contentDescription = filmDetail.title,
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Column {
                Row { Text(
                    text = "${(filmDetail.release_date)}",
                    modifier = Modifier.padding(8.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                ) }
                Row {
                    Text(
                        text = "Genres: ${filmDetail.genres.joinToString(", ") { it.name }}",
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(8.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )}
            }
        }
        Text(text = "Synopsis :", fontWeight = FontWeight.Bold, style = typography.h4)
        Text(text = "${filmDetail.overview}", textAlign = TextAlign.Justify)
        Text(text = "Têtes d'affiche :", fontWeight = FontWeight.Bold, style = typography.h4)
    }
}
