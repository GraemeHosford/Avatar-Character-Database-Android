package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

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

class CharacterListItemUiModelProcessorTest {

    private lateinit var processor: CharacterListItemUiModelProcessor

    @RelaxedMockK
    private lateinit var converter: CharacterListItemUiModelConverter

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        processor = CharacterListItemUiModelProcessor(converter)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun process_returnsCorrect_listItems() = runBlocking {
        val entity = getCharacterEntity(1L, characterId = "test1", name = "Aang", photoUrl = "url")
        val entities = listOf(entity)

        val model = CharacterListItemUiModel(1L, "test1", "Aang", "url")
        val expectedModels = listOf(model)

        every { converter.toUiModel(entity) } returns model

        val models = processor.process(entities)

        assertThat(models, equalTo(expectedModels))
    }

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