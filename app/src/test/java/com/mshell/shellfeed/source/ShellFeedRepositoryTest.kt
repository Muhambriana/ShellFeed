package com.mshell.shellfeed.source

import com.mshell.shellfeed.core.data.ShellFeedRepositoryImpl
import com.mshell.shellfeed.core.data.source.Resource
import com.mshell.shellfeed.core.data.source.remote.ApiResponse
import com.mshell.shellfeed.core.data.source.remote.RemoteDataSource
import com.mshell.shellfeed.utils.DataDummy
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
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