package com.mshell.shellfeed.source

import com.mshell.shellfeed.core.data.ShellFeedRepositoryImpl
import com.mshell.shellfeed.core.data.source.Resource
import com.mshell.shellfeed.core.data.source.remote.ApiResponse
import com.mshell.shellfeed.core.data.source.remote.RemoteDataSource
import com.mshell.shellfeed.core.domain.model.NewsDetail
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ShellFeedRepositoryTest {
    private val remote = mockk<RemoteDataSource>(relaxed = true)
    private lateinit var repository: ShellFeedRepositoryImpl

    @Before
    fun setup() {
        repository = ShellFeedRepositoryImpl(remote)
    }

    @Test
    fun `map list`() = runTest {
        every { remote.getTopHeadlines("us") } returns
                flowOf(
                    ApiResponse.Success(
                        listOf(
                            NewsDetail(),
                            NewsDetail()
                        )
                    )
                )

        val result = repository.getTopHeadlines("us").first()

        assertTrue(result is Resource.Success)
    }
}