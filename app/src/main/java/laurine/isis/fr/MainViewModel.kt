package laurine.isis.fr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class MainViewModel : ViewModel() {

    val movies = MutableStateFlow<List<Movie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val persons = MutableStateFlow<List<Person>>(listOf())
    val apikey ="2ab74ba6ba3af991e8495015b7df64d5"

    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TmdbAPI::class.java)

    fun searchMovies(motcle: String){
        viewModelScope.launch {
            movies.value = service.getFilmsParMotCle(apikey, motcle).results
        }
    }

    fun filmsTendance(){
        viewModelScope.launch {
            movies.value = service.derniersFilms(apikey).results
        }
    }

    fun acteursTendance(){
        viewModelScope.launch {
            persons.value = service.dernieresPersons(apikey).results
        }
    }

    fun seriesTendance(){
        viewModelScope.launch {
            series.value = service.dernieresSeries(apikey).results
        }
    }
}