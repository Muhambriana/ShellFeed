package com.mshell.shellfeed.ui

import com.mshell.shellfeed.core.data.source.Resource
import com.mshell.shellfeed.core.domain.repository.ShellFeedRepository
import com.mshell.shellfeed.ui.features.news_list.NewsViewModel
import com.mshell.shellfeed.utils.DataDummy
import com.mshell.shellfeed.utils.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: ShellFeedRepository = mockk()
    private lateinit var viewModel: NewsViewModel

    fun createViewModel() = NewsViewModel(repository)

    @Before
    fun setup() {
        every { repository.getTopHeadlines("us") } returns
                flowOf(
                    Resource.Loading()
                )
    }

    @Test
    fun `initial state is loading`() = runTest {
        every { repository.getTopHeadlines("us") } returns
                flowOf(
                    Resource.Loading()
                )

        viewModel = createViewModel()
        advanceUntilIdle()

        val result = viewModel.newsState.value
        assertTrue(result is Resource.Loading)
    }

    @Test
    fun `get news list returns error message`() = runTest {
        val expectedErrorMessage = "404 Not Found"

        every { repository.getTopHeadlines("us") } returns
                flowOf(
                    Resource.Error(errorMessage = "404 Not Found")
                )

        viewModel = createViewModel()
        advanceUntilIdle()

        val result = viewModel.newsState.value
        assertTrue(result is Resource.Error)
        assertEquals(expectedErrorMessage, result.errorMessage)
        assertNull(result.data)
    }

    @Test
    fun `get news list returns null data`() = runTest {
        every { repository.getTopHeadlines("us") } returns
                flowOf(
                    Resource.Success(null)
                )

        viewModel = createViewModel()
        advanceUntilIdle()

        val result = viewModel.newsState.value
        assertTrue(result is Resource.Success)
        assertNull(result.data)
    }

    @Test
    fun `get news list returns success`() = runTest {
        every { repository.getTopHeadlines("us") } returns
                flowOf(
                    Resource.Success(DataDummy.generateDummyNews())
                )

        viewModel = createViewModel()
        advanceUntilIdle()

        val result = viewModel.newsState.value
        assertTrue(result is Resource.Success)
        assertEquals(DataDummy.generateDummyNews(), result.data)
    }

    @Suppress("UnusedFlow")
    @Test
    fun `getTopHeadLines calls repository with correct country`() = runTest {
        every { repository.getTopHeadlines("us") } returns flowOf(Resource.Success(null))

        viewModel = createViewModel()
        advanceUntilIdle()

        verify { repository.getTopHeadlines("us") }
    }

    @Suppress("UnusedFlow")
    @Test
    fun `getTopHeadLines with different country`() = runTest {
        val dummyNews = DataDummy.generateDummyNews()
        // Stub init call
        every { repository.getTopHeadlines("us") } returns flowOf(Resource.Loading())
        // Stub the explicit call
        every { repository.getTopHeadlines("gb") } returns flowOf(Resource.Success(dummyNews))

        viewModel = createViewModel()
        viewModel.getTopHeadLines("gb")
        advanceUntilIdle()

        val result = viewModel.newsState.value
        assertTrue(result is Resource.Success)
        assertEquals(dummyNews, (result as Resource.Success).data)
        verify { repository.getTopHeadlines("gb") }
    }

    @After
    fun tearDown(){
        unmockkAll()
    }
}