package laurine.isis.fr

import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {
    @GET("search/movie")
    suspend fun getFilmsParMotCle(@Query("api_key") apikey : String, @Query("query")motcle: String) : TmdbResult
}