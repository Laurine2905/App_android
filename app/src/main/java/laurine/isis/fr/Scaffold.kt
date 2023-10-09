import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import laurine.isis.fr.Film
import laurine.isis.fr.MainViewModel
import laurine.isis.fr.Persons
import laurine.isis.fr.Serie
import androidx.compose.material3.TextField
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import laurine.isis.fr.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBarExample(windowSizeClass: WindowSizeClass, navController: NavController, viewModel: MainViewModel) {
    var value by remember { mutableStateOf("films") }
    var searchVisible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            // TopAppBar avec le champ de recherche
            TopAppBar(
                title = {
                    if (searchVisible) {

                        // Champ de texte pour la recherche
                        TextField(
                            value = searchQuery,
                            onValueChange = {
                                searchQuery = it
                                // Appeler votre fonction de recherche ici avec la query
                                if(value=="series") {
                                    viewModel.searchSeries(searchQuery)
                                }
                                if(value=="films") {
                                    viewModel.searchMovies(searchQuery)
                                }
                                if(value=="acteurs") {
                                    viewModel.searchPersons(searchQuery)
                                }
                            },
                            placeholder = { Text("Rechercher") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        // Afficher le titre de l'application
                        Text(text = "Fav'app")
                    }
                },
                // Icône loupe pour afficher/masquer le champ de recherche
                actions = {
                    IconButton(onClick = {
                        searchVisible = !searchVisible
                        // Réinitialiser la recherche lorsque le champ est masqué
                        if (!searchVisible) {
                            searchQuery = ""
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                }
            )
        },
        bottomBar = {
            // BottomAppBar avec les boutons
            BottomAppBar(
                contentPadding = PaddingValues(top = 1.dp, bottom = 1.dp),
                contentColor = LocalContentColor.current,
            ) {
                IconButton(onClick = { value = "films" }) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_movie_24),
                        contentDescription = "profil"
                    )
                }
                IconButton(onClick = { value = "series" }) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_tv_24),
                        contentDescription = "profil"
                    )
                }
                IconButton(onClick = { value = "acteurs" }) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = "profil"
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            // Contenu principal de votre écran
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (value) {
                    "films" -> Film(windowSizeClass, navController, viewModel)
                    "series" -> Serie(windowSizeClass, navController, viewModel)
                    "acteurs" -> Persons(windowSizeClass, navController, viewModel)
                    else -> Text(text = "Contenu inconnu")
                }
            }
        }
    }
}
