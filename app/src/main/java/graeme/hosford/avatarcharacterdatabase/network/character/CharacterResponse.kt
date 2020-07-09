package graeme.hosford.avatarcharacterdatabase.network.character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "_id") val characterId: String,
    @Json(name = "name") val characterName: String,
    @Json(name = "allies") val allies: List<String>?,
    @Json(name = "enemies") val enemies: List<String>?,
    @Json(name = "photoUrl") val photoUrl: String?,
    @Json(name = "gender") val gender: String?,
    @Json(name = "eye") val eyeColour: String?,
    @Json(name = "hair") val hairColour: String?,
    @Json(name = "skin") val skinColour: String?,
    @Json(name = "weapon") val weapon: String?,
    @Json(name = "love") val loves: String?,
    @Json(name = "profession") val profession: String?,
    @Json(name = "position") val position: String?,
    @Json(name = "predecessor") val predecessor: String?,
    @Json(name = "affiliation") val affiliation: String?,
    @Json(name = "first") val first: String?,
    @Json(name = "voicedBy") val voicedBy: String?
)