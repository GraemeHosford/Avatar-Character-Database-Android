package graeme.hosford.avatarcharacterdatabase.network.character

import retrofit2.http.GET
import retrofit2.http.Query

interface AvatarCharacterRetrofitService {

    companion object {
        const val CHARACTERS_PER_PAGE = 100
    }

    @GET("/api/v1/characters")
    suspend fun getAllCharacters(@Query("perPage") perPage: Int): List<CharacterResponse>

}