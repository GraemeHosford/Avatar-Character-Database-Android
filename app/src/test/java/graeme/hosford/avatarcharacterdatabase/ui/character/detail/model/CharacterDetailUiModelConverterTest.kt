package graeme.hosford.avatarcharacterdatabase.ui.character.detail.model

import graeme.hosford.avatarcharacterdatabase.R
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CharacterDetailUiModelConverterTest {

    private lateinit var converter: CharacterDetailUiModelConverter

    @Before
    fun setup() {
        converter = CharacterDetailUiModelConverter()
    }

    @Test
    fun converter_toUiModel_correctlyConverts_entityToUiModel() {
        val entity = getCharacterEntity(
            1L,
            name = "Aang",
            photoUrl = "url",
            eyeColour = "Gray",
            loves = "Katara"
        )

        val expectedModel = CharacterDetailUiModel(
            1L,
            "Aang",
            "url",
            listOf(
                CharacterSingleDetailUiModel(
                    -2L, R.string.character_detail_eye_colour, "Gray"
                ),
                CharacterSingleDetailUiModel(
                    -6L, R.string.character_detail_loves, "Katara"
                )
            )
        )

        val model = converter.toUiModel(entity)

        assertThat(model, equalTo(expectedModel))
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