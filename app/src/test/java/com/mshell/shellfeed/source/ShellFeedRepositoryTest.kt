package com.mshell.shellfeed.source

import com.mshell.shellfeed.core.data.ShellFeedRepositoryImpl
import com.mshell.shellfeed.core.data.source.Resource
import com.mshell.shellfeed.core.data.source.remote.ApiResponse
import com.mshell.shellfeed.core.data.source.remote.RemoteDataSource
import com.mshell.shellfeed.utils.DataDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ShellFeedRepositoryTest {
    val newsResponse = DataDummy.generateDummyNews()
    private val remote = mockk<RemoteDataSource>(relaxed = true)
    private lateinit var repository: ShellFeedRepositoryImpl

    @Before
    fun setup() {
        repository = ShellFeedRepositoryImpl(remote)
    }

    @Test
    fun `loading response`() = runTest {
        val expectedProgress = 50
        val expectedData = null

        every { remote.getTopHeadlines("us") } returns
                flowOf(
                    ApiResponse.Progress(50)
                )

        val result = repository.getTopHeadlines("us").first()
        assertTrue(result is Resource.Loading)

        val progress = result.progress
        assertEquals(expectedProgress, progress)

        val data = result.data
        assertEquals(expectedData, data)
    }

    @Test
    fun `error response`() = runTest {
        val expectedErrorMessage = "403 Forbidden"
        val expectedData = null

        every { remote.getTopHeadlines("us") } returns
                flowOf(
                    ApiResponse.Error("403 Forbidden")
                )

        val result = repository.getTopHeadlines("us").first()
        assertTrue(result is Resource.Error)

        val errorMessage = result.errorMessage
        assertEquals(expectedErrorMessage, errorMessage)

        val data = result.data
        assertEquals(expectedData, data)
    }

    @Test
    fun `empty data response`() = runTest {
        val expectedData = null

        every { remote.getTopHeadlines("us") } returns
                flowOf(
                    ApiResponse.Empty
                )

        val result = repository.getTopHeadlines("us").first()
        assertTrue(result is Resource.Success)

        val data = (result as Resource.Success).data
        assertEquals(expectedData, data)
    }

    @Test
    fun `success api response`() = runTest {
        every { remote.getTopHeadlines("us") } returns
                flowOf(
                    ApiResponse.Success(
                        DataDummy.generateDummyNews()
                    )
                )

        val result = repository.getTopHeadlines("us").first()
        assertTrue(result is Resource.Success)

        val data = (result as Resource.Success).data
        assertNotNull(data)
        assertEquals(newsResponse,data)
    }


    @After
    fun tearDown() {
        unmockkAll()
    }
}