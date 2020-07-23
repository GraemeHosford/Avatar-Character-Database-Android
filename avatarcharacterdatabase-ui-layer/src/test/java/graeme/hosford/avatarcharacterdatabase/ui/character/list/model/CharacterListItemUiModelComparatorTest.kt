package graeme.hosford.avatarcharacterdatabase.ui.character.list.model

import graeme.hosford.avatarcharacterdatabase.character.list.model.CharacterListItemUiModel
import graeme.hosford.avatarcharacterdatabase.character.list.model.CharacterListItemUiModelComparator
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class CharacterListItemUiModelComparatorTest {

    @Test
    fun nameComparator_sortsModels_inCorrectOrder() {
        val comparator = CharacterListItemUiModelComparator.nameComparator

        val unsortedList = listOf(
            getUiModel(name = "Katara"),
            getUiModel(name = "Aang"),
            getUiModel(name = "Toph"),
            getUiModel(name = "Iroh"),
            getUiModel(name = "Zuko")
        )

        val sortedList = listOf(
            getUiModel(name = "Aang"),
            getUiModel(name = "Iroh"),
            getUiModel(name = "Katara"),
            getUiModel(name = "Toph"),
            getUiModel(name = "Zuko")
        )

        assertThat(unsortedList.sortedWith(comparator), equalTo(sortedList))
    }

    private fun getUiModel(
        id: Long = 1L,
        networkId: String = "Test",
        name: String = "Aang",
        photoUrl: String? = null
    ) = CharacterListItemUiModel(id, networkId, name, photoUrl)

}