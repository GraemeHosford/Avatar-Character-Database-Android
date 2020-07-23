package graeme.hosford.avatarcharacterdatabase.repo.layer.character.detail

import graeme.hosford.avatarcharacterdatabase.entity.layer.CharacterEntity
import graeme.hosford.avatarcharacterdatabase.network.layer.character.CharacterResponse
import graeme.hosford.avatarcharacterdatabase.repo.layer.character.common.CharacterResponseConverter
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class SingleCharacterResponseProcessorTest {

    private lateinit var processor: SingleCharacterResponseProcessor

    @RelaxedMockK
    private lateinit var converter: CharacterResponseConverter

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        processor = SingleCharacterResponseProcessor(
                converter
            )
    }

    @Test
    fun process_correctlyConvertsResponse_toEntity() = runBlocking {
        val response = getResponse()
        val expectedEntity = getCharacterEntity(0L, characterId = "TestId", name = "Aang")

        every { converter.toEntity(response) } returns expectedEntity

        val entity = processor.process(response)

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