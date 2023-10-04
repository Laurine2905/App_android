package laurine.isis.fr

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key") apikey : String, @Query("query")motcle: String) : Movies

    @GET("trending/movie/week")
    suspend fun derniersFilms(@Query("api_key") apikey: String): Movies

    @GET("trending/tv/week")
    suspend fun dernieresSeries(@Query("api_key") apikey: String): Series

}