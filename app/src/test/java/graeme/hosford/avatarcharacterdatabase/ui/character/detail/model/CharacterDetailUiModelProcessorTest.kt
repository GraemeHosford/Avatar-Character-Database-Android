package graeme.hosford.avatarcharacterdatabase.ui.character.detail.model

import graeme.hosford.avatarcharacterdatabase.R
import graeme.hosford.avatarcharacterdatabase.entity.CharacterEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class CharacterDetailUiModelProcessorTest {

    private lateinit var processor: CharacterDetailUiModelProcessor

    @RelaxedMockK
    private lateinit var converter: CharacterDetailUiModelConverter

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        processor = CharacterDetailUiModelProcessor(converter)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun processorProcess_returns_CorrectModel() = runBlocking {
        val entity = getCharacterEntity(
            1L,
            name = "Aang",
            photoUrl = "url",
            eyeColour = "Gray",
            loves = "Katara"
        )

        val expectedModel = getCharacterDetailUiModel(
            details = listOf(
                CharacterSingleDetailUiModel(-2L, R.string.character_detail_eye_colour, "Gray"),
                CharacterSingleDetailUiModel(-6L, R.string.character_detail_loves, "Katara")
            )
        )

        every { converter.toUiModel(entity) } returns expectedModel

        val model = processor.process(entity)

        assertThat(model, equalTo(expectedModel))
    }

    private fun getCharacterDetailUiModel(
        id: Long = 1L,
        name: String = "Aang",
        photoUrl: String = "url",
        details: List<CharacterSingleDetailUiModel> = listOf(
            CharacterSingleDetailUiModel(
                -1L,
                R.string.character_detail_loves,
                "katara"
            )
        )
    ) = CharacterDetailUiModel(id, name, photoUrl, details)

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