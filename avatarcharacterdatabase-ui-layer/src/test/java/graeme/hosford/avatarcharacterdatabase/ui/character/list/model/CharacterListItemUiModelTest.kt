package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

import graeme.hosford.avatarcharacterdatabase.character.list.model.CharacterListItemUiModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CharacterListItemUiModelTest {

    @Test
    fun areContentsTheSame_returnsTrue_whenAllContentsEqual() {
        val model1 = getUiModel()
        val model2 = getUiModel()

        assertTrue(model1.areContentsTheSame(model2))
    }

    @Test
    fun areContentsTheSame_returnsFalse_whenAnyValueNotEqual() {
        val model1 = getUiModel(id = 1L)
        val model2 = getUiModel(id = 2L)
        assertFalse(model1.areContentsTheSame(model2))

        val model3 = getUiModel(networkId = "Test")
        val model4 = getUiModel(networkId = "other")
        assertFalse(model3.areContentsTheSame(model4))

        val model5 = getUiModel(name = "Sokka")
        val model6 = getUiModel(name = "Toph")
        assertFalse(model5.areContentsTheSame(model6))

        val model7 = getUiModel(photoUrl = "Test")
        val model8 = getUiModel(photoUrl = "othertest")
        assertFalse(model7.areContentsTheSame(model8))
    }

    private fun getUiModel(
        id: Long = 1L,
        networkId: String = "Test",
        name: String = "Aang",
        photoUrl: String? = null
    ) = CharacterListItemUiModel(id, networkId, name, photoUrl)

}