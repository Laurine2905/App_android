package laurine.isis.fr

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun SearchMovies(name: String, viewModel: MainViewModel) {
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty() && name.isNotEmpty()) {
        viewModel.searchMovies(name)
    }

    LazyColumn {
        items(movies) { movie ->
            Text(text = movie.original_title)
        }
    }
}

@Composable
fun SearchSeries(name: String, viewModel: MainViewModel) {
    val series by viewModel.series.collectAsState()

    if (series.isEmpty() && name.isNotEmpty()) {
        viewModel.searchSeries(name)
    }

    LazyColumn {
        items(series) { serie ->
            Text(text = serie.original_name)
        }
    }
}

@Composable
fun SearchPersons(name: String, viewModel: MainViewModel) {
    val persons by viewModel.persons.collectAsState()

    if (persons.isEmpty() && name.isNotEmpty()) {
        viewModel.searchPersons(name)
    }

    LazyColumn {
        items(persons) { person ->
            Text(text = person.name)
        }
    }
}
