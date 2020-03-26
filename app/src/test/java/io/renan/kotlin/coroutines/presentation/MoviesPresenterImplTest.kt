package io.renan.kotlin.coroutines.presentation

import android.util.Log
import com.nhaarman.mockitokotlin2.*
import io.renan.kotlin.coroutines.data.model.Movie
import io.renan.kotlin.coroutines.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Log::class)
class MoviesPresenterImplTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)
    private val mockRepository = mock<MovieRepository>()
    private val view = mock<MoviesView>()

    private val presenter by lazy {
        MoviesPresenterImpl(mockRepository)
    }

    @Before
    fun setUp() {
        PowerMockito.mockStatic(Log::class.java)
        Dispatchers.setMain(testDispatcher)
        presenter.setView(view)
    }

    @Test
    fun testGetDataShowsResults() = testCoroutineScope.runBlockingTest {
        //arrange
        whenever(mockRepository.getMovies()).thenReturn(listOf())

        //act
        presenter.getData()
        //assert
        verify(mockRepository, times(1)).getMovies()
        verify(view, times(1)).showMovies(any())
    }

}