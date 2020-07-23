package graeme.hosford.avatarcharacterdatabase.repo.layer.common

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BaseRepoTest {

    private lateinit var testBaseRepo: TestBaseRepo

    @Before
    fun setup() {
        testBaseRepo = TestBaseRepo()
    }

    @Test
    fun fetchData_emitsResultsCorrectly() = runBlocking {
        val emits = arrayListOf<RepoState<String>>()

        testBaseRepo.test().toList(emits)

        assertTrue(emits[0] is RepoState.Loading)
        assertThat((emits[1] as RepoState.Completed).data, equalTo("Test"))
    }

    @Test
    fun fetchData_emitsRepoStateError_onError() = runBlocking {
        val emits = arrayListOf<RepoState<String>>()

        testBaseRepo.errorTest().toList(emits)

        assertTrue(emits[0] is RepoState.Loading)
        assertTrue(emits[1] is RepoState.Error)
    }


    private inner class TestService
    private inner class TestDao
    private inner class TestBaseRepo :
        BaseRepo<TestService, TestDao, String>(TestService(), TestDao()) {
        suspend fun test() = fetchData {
            emit(RepoState.completed("Test"))
        }

        suspend fun errorTest() = fetchData {
            throw Exception()
        }
    }
}