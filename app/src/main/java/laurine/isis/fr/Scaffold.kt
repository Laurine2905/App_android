import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import laurine.isis.fr.MainViewModel
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavController
import laurine.isis.fr.Film
import laurine.isis.fr.Persons
import laurine.isis.fr.Serie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBarExample(windowSizeClass: WindowSizeClass, navController: NavController, viewModel: MainViewModel) {
    var value by remember { mutableStateOf("films") }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(top = 1.dp, bottom = 1.dp), // Ajustez le padding ici
                contentColor = LocalContentColor.current,
            ) {
                IconButton(onClick = { value = "films" }) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = "Localized description",
                    )
                }
                IconButton(onClick = { value = "series" }) {
                    Icon(
                        Icons.Filled.AccountBox,
                        contentDescription = "Localized description",
                    )
                }
                IconButton(onClick = { value = "acteurs" }) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Localized description",
                    )
                }
            }
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                 when (value) {
                        "films" -> Film (windowSizeClass, navController, viewModel)
                        "series" -> Serie (windowSizeClass, navController, viewModel)
                        "acteurs" -> Persons (windowSizeClass, navController, viewModel)
                        else -> ""
                    }
            }
        }
    }
}