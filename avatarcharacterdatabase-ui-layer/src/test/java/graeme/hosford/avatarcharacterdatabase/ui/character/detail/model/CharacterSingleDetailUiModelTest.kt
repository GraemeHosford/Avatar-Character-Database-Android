package graeme.hosford.avatarcharacterdatabase.ui.character.detail.model

import androidx.annotation.StringRes
import graeme.hosford.avatarcharacterdatabase.R
import graeme.hosford.avatarcharacterdatabase.character.detail.model.CharacterSingleDetailUiModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CharacterSingleDetailUiModelTest {

    @Test
    fun characterSingleDetailUiModel_areContentsTheSame_returnsTrue_whenContents_areEqual() {
        val model1 = testCharacterSingleDetailUiModel()
        val model2 = testCharacterSingleDetailUiModel()

        assertTrue(model1.areContentsTheSame(model2))
    }

    @Test
    fun characterSingleDetailUiModel_areContentsTheSame_returnsFalse_whenContents_areNotEqual() {
        val model1 = testCharacterSingleDetailUiModel(id = 1L)
        val model2 = testCharacterSingleDetailUiModel(id = 2L)
        assertFalse(model1.areContentsTheSame(model2))

        val model3 = testCharacterSingleDetailUiModel(titleRes = R.string.character_detail_position)
        val model4 =
            testCharacterSingleDetailUiModel(titleRes = R.string.character_detail_affiliation)
        assertFalse(model3.areContentsTheSame(model4))

        val model5 = testCharacterSingleDetailUiModel(detail = "Fighter")
        val model6 = testCharacterSingleDetailUiModel(detail = "Something")
        assertFalse(model5.areContentsTheSame(model6))
    }

    private fun testCharacterSingleDetailUiModel(
        id: Long = 1L,
        @StringRes titleRes: Int = R.string.character_detail_position,
        detail: String = "Avatar"
    ) = CharacterSingleDetailUiModel(id, titleRes, detail)

}