package com.example.sanbox.module.fetchdata

import android.util.Log
import com.example.network.data.animal.model.AnimalResponse
import com.example.network.data.animal.model.AttributeResponse
import com.example.network.data.animal.model.FactResponse
import com.example.network.data.animal.repository.AnimalRepository
import com.example.sanbox.modules.fetchdata.presenter.AnimalViewModel
import com.example.sanbox.modules.fetchdata.utils.AnimalMapper.mapToDomain
import com.google.protobuf.any
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import pubsub.event

@ExperimentalCoroutinesApi
class AnimalViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    val repo: AnimalRepository = mockk<AnimalRepository>()
    private lateinit var viewModel: AnimalViewModel

    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        viewModel = AnimalViewModel(repo)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when fetch data update data state`() = runTest{
        val expectedData = FactResponse(
            List(5){
                AnimalResponse(
                    id = "qwe",
                    attributeResponse = AttributeResponse(
                        "aksjndaksd"
                    )
                )
            }
        )
        coEvery { repo.getAnimal() } returns expectedData

        viewModel.fetchData()
        advanceUntilIdle()

        assertEquals(expectedData.data.map { it.mapToDomain() }, viewModel.uiState.value.list)
    }
}