package graeme.hosford.avatarcharacterdatabase.ui.character.common.model

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CharacterUiModelTest {

    @Test
    fun characterUiModel_equals_returnsTrue_forModels_withSameValues() {
        val model1 = TestCharacterUiModel()
        val model2 = TestCharacterUiModel()

        assertTrue(model1 == model2)
    }

    @Test
    fun characterUiModel_equals_returnsFalse_forModels_withAnyDifferingValue() {
        val model1 = TestCharacterUiModel(id = 1L)
        val model2 = TestCharacterUiModel(id = 2L)
        assertFalse(model1 == model2)

        val model3 = TestCharacterUiModel(name = "Katara")
        val model4 = TestCharacterUiModel(name = "Toph")
        assertFalse(model3 == model4)

        val model5 = TestCharacterUiModel(photoUrl = "fakeUrl")
        val model6 = TestCharacterUiModel(photoUrl = "otherUrl")
        assertFalse(model5 == model6)
    }

    private inner class TestCharacterUiModel(
        id: Long = 1L,
        name: String = "Aang",
        photoUrl: String? = null
    ) : CharacterUiModel(id, name, photoUrl)

}