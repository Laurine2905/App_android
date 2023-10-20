package laurine.isis.fr

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI {
    @GET("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key") apikey : String, @Query("query")motcle: String) : Movies

    @GET("search/tv")
    suspend fun getSeriesParMotCle(@Query("api_key") apikey : String, @Query("query")motcle: String) : Series

    @GET("search/person")
    suspend fun getPersonsParMotCle(@Query("api_key") apikey : String, @Query("query")motcle: String) : Persons

    @GET("trending/movie/week")
    suspend fun derniersFilms(@Query("api_key") apikey: String): Movies

    @GET("trending/tv/week")
    suspend fun dernieresSeries(@Query("api_key") apikey: String): Series

    @GET("trending/person/week")
    suspend fun dernieresPersons(@Query("api_key") apikey: String): Persons

    @GET("movie/{movie_id}?append_to_response=credits")
    suspend fun getFilmDetails(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String ): FilmDetail

    @GET("tv/{serie_id}?append_to_response=credits")
    suspend fun getSerieDetails(@Path("serie_id") serieId: Int, @Query("api_key") apiKey: String ): SerieDetails


}