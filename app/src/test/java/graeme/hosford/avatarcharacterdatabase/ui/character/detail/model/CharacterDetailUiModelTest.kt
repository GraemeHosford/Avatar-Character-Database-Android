package graeme.hosford.avatarcharacterdatabase.ui.character.detail.model

import graeme.hosford.avatarcharacterdatabase.R
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CharacterDetailUiModelTest {

    @Test
    fun areContentsTheSame_returnsTrue_onSameValues() {
        val model1 = getCharacterDetailUiModel()
        val model2 = getCharacterDetailUiModel()
        assertTrue(model1.areContentsTheSame(model2))
    }

    @Test
    fun areContentsTheSame_returnsFalse_onAnyDifferenceInValues() {
        val model1 = getCharacterDetailUiModel(id = 1L)
        val model2 = getCharacterDetailUiModel(id = 2L)
        assertFalse(model1.areContentsTheSame(model2))

        val model3 = getCharacterDetailUiModel(name = "Aang")
        val model4 = getCharacterDetailUiModel(name = "Sokka")
        assertFalse(model3.areContentsTheSame(model4))

        val model5 = getCharacterDetailUiModel(photoUrl = "test")
        val model6 = getCharacterDetailUiModel(photoUrl = "other")
        assertFalse(model5.areContentsTheSame(model6))

        val model7 = getCharacterDetailUiModel(details = listOf())
        val model8 = getCharacterDetailUiModel(
            details = listOf(
                CharacterSingleDetailUiModel(
                    -9L,
                    R.string.character_detail_eye_colour,
                    "blue"
                )
            )
        )
        assertFalse(model7.areContentsTheSame(model8))
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
}