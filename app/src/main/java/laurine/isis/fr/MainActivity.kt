package laurine.isis.fr

import BottomAppBarExample
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import laurine.isis.fr.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val viewModel : MainViewModel by viewModels()

            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    NavHost(
                     navController = navController,
                     startDestination = "image") {
                      composable("image") {
                          Screen(windowSizeClass, navController)
                      }
                      composable("film") {
                          BottomAppBarExample(windowSizeClass, navController, viewModel)
                      }
                    }
                }
            }
        }
    }
}

@Composable
fun Search(name: String, viewModel: MainViewModel){
    val movies by viewModel.movies.collectAsState()

    if (movies.isEmpty()) viewModel.searchMovies(name)

    LazyColumn{
        items(movies){
            movie -> Text(text= movie.original_title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefautPreview(){
    MyApplicationTheme {
        Search("Android", MainViewModel())
    }
}

@Composable
fun Texte(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Etudiante en FIE4",
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Ecole d'ingénieur ISIS",
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Composable
fun Lien(){
    Column(){
        Row(Modifier.padding(top = 20.dp)) {
            Image(
                painterResource(id = R.drawable.mail),
                contentDescription = "Mail",
                Modifier.size(30.dp)
            )
            Text(text = "laurine.rat@outlook.fr")
        }
        Row() {
            Image(
                painterResource(id = R.drawable.linkedin),
                contentDescription = "Linkedin",
                Modifier.size(30.dp)
            )
            Text(text = "www.linkedin.com")
        }}
}

@Composable
fun photoProfil(){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(id = R.drawable.sans_titre),
            contentDescription = "profil",
            contentScale = ContentScale.Crop, // redimensionnement de l'image
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape) //cercle // taille l'espace disponible
        )
        Text(text = "Laurine Rat",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(10.dp))
    }}

@Composable
fun bouton(navController: NavController) {
    androidx.compose.material3.Button(
        onClick = {
            navController.navigate("film") // naviguer vers l'acceuil de l'appli (affichage des films par défault)
        }
    ) {
        Text(text = "Démarrer")
    }
}

@Composable
fun Screen(windowClass: WindowSizeClass, navController: NavController) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()// Remplit tout
                    .padding(10.dp)
            ) {
                photoProfil()
                Texte()
                Lien()
                bouton(navController)

            }
        }
        else -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Column (){
                    photoProfil()

                }
                Column () {
                    Texte()
                    Lien()
                    bouton(navController)
                }
            }
        }
    }
}