package graeme.hosford.avatarcharacterdatabase.repo.character

import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.character.CharacterResponse
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CharacterResponseConverterTest {

    private lateinit var converter: CharacterResponseConverter

    @Before
    fun setup() {
        converter = CharacterResponseConverter()
    }

    @Test
    fun toEntity_correctlyConvertsResponse_toEntity() {
        val response = getResponse()

        val expectedEntity = getCharacterEntity(0L, characterId = "TestId", name = "Aang")

        val entity = converter.toEntity(response)
        assertThat(entity, equalTo(expectedEntity))
    }

    private fun getResponse(
        characterId: String = "TestId",
        characterName: String = "Aang"
    ) = CharacterResponse(
        characterId,
        characterName,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )

    private fun getCharacterEntity(
        id: Long,
        characterId: String = "FakeId",
        name: String,
        photoUrl: String? = null,
        gender: String? = null,
        eyeColour: String? = null,
        hairColour: String? = null,
        skinColour: String? = null,
        weapon: String? = null,
        loves: String? = null,
        profession: String? = null,
        position: String? = null,
        predecessor: String? = null,
        affiliation: String? = null,
        first: String? = null,
        voicedBy: String? = null
    ) = CharacterEntity(
        id, characterId, name, photoUrl, gender, eyeColour, hairColour, skinColour, weapon,
        loves, profession, position, predecessor, affiliation, first, voicedBy
    )

}