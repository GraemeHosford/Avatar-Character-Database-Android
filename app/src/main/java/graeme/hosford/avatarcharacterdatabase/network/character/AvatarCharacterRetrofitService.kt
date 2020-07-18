package graeme.hosford.avatarcharacterdatabase.network.character

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AvatarCharacterRetrofitService {

    @GET("/api/v1/characters")
    suspend fun getAllCharacters(
        @Query("perPage") perPage: Int,
        @Query("page") pageNum: Int
    ): List<CharacterResponse>

    @GET("/api/v1/characters/{id}")
    suspend fun getCharacterById(@Path("id") characterId: String): CharacterResponse

}