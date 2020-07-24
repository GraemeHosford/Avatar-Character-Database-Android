package graeme.hosford.avatarcharacterdatabase.character.list.model

import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CharacterListItemUiModelConverterTest {

    private lateinit var converter: CharacterListItemUiModelConverter

    @Before
    fun setup() {
        converter = CharacterListItemUiModelConverter()
    }

    @Test
    fun toUiModel_returnsCorrectModel_fromEntity() {
        val entity = getCharacterEntity(
            1L,
            characterId = "networkId",
            name = "Aang",
            photoUrl = "test"
        )

        val expectedModel = CharacterListItemUiModel(
            1L, "networkId", "Aang", "test"
        )

        assertThat(converter.toUiModel(entity), equalTo(expectedModel))
    }

    private fun getCharacterEntity(
        id: Long,
        characterId: String = "FakeId",
        name: String,
        allies: List<String>? = null,
        enemies: List<String>? = null,
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
        id,
        characterId,
        name,
        allies,
        enemies,
        photoUrl,
        gender,
        eyeColour,
        hairColour,
        skinColour,
        weapon,
        loves,
        profession,
        position,
        predecessor,
        affiliation,
        first,
        voicedBy
    )

}