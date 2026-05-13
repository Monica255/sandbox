package com.example.sanbox.module.paging

import androidx.paging.AsyncPagingDataDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.network.data.pagination.model.BreedResponse
import com.example.network.data.pagination.model.CatBreedResponse
import com.example.network.data.pagination.repo.PagingRepository
import com.example.sanbox.modules.pagination.model.Breed
import com.example.sanbox.modules.pagination.presenter.PagingViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PagingViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private val repository = mockk<PagingRepository>()

    private lateinit var viewModel: PagingViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PagingViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPagingHistory update paging data`() = runTest {
        class NoopListCallback : ListUpdateCallback {
            override fun onInserted(p0: Int, p1: Int) {
            }

            override fun onRemoved(p0: Int, p1: Int) {
            }

            override fun onMoved(p0: Int, p1: Int) {
            }

            override fun onChanged(p0: Int, p1: Int, p2: Any?) {
            }
        }

        val expectedData = List(5) {
            CatBreedResponse(
                breed = "meng",
                country = "indo"
            )
        }

        coEvery { repository.getBreed(any(), any()) } returns BreedResponse(data = expectedData)

        val differ = AsyncPagingDataDiffer(
            diffCallback = object : DiffUtil.ItemCallback<Breed>() {
                override fun areItemsTheSame(
                    old: Breed,
                    new: Breed,
                ): Boolean = old == new

                override fun areContentsTheSame(
                    old: Breed,
                    new: Breed,
                ): Boolean = old == new

            },
            updateCallback = NoopListCallback(),
            mainDispatcher = Dispatchers.Main,
        )

        val job = launch {
            viewModel.pagingData.collectLatest { pagingData ->
                differ.submitData(pagingData)
            }
        }


        advanceUntilIdle()
        val items = differ.snapshot().items
        assertEquals(5, items.size)
        assertEquals(expectedData[0].breed, items[1].breed)

        job.cancel()
    }
}