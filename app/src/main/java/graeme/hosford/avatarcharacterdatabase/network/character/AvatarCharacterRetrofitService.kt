package graeme.hosford.avatarcharacterdatabase.network.character

import retrofit2.http.GET
import retrofit2.http.Query

interface AvatarCharacterRetrofitService {

    @GET("/api/v1/characters")
    fun getAllCharacters(@Query("perPage") perPage: Int): List<CharacterResponse>

}