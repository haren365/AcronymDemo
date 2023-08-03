package com.acronymdemo.viewmodel

import androidx.lifecycle.Observer
import com.acronymdemo.model.AcronymDataModel
import com.acronymdemo.model.Definition
import com.acronymdemo.model.Meaning
import com.acronymdemo.repository.AcronymsRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class AcronymViewModelTest {
    private lateinit var acronymViewModel: AcronymViewModel

    @MockK
    private lateinit var acronymsRepository: AcronymsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        acronymViewModel = AcronymViewModel(acronymsRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `when successful response from server`() = runBlocking {
        val word = "hello"
        val expectedOutcome = listOf(
            AcronymDataModel(
                "hello",
                listOf(Meaning("noun", listOf(Definition("a greeting")), listOf(""), listOf("")))
            )
        )
        coEvery { acronymsRepository.getAcronyms(word) } returns expectedOutcome

        val observer = mockk<Observer<List<AcronymDataModel>>>(relaxed = true)
        acronymViewModel.fetchAcronyms(word)

        verify { observer.onChanged(expectedOutcome) }
    }

    @Test
    fun `when getting error in response`() = runBlocking {
        val word = "invalid_word"
        val errorMessage = "acronyms not found"

        coEvery { acronymsRepository.getAcronyms(word) } throws Exception(errorMessage)
        val observer = mockk<Observer<String>>(relaxed = true)
        acronymViewModel.error.observeForever(observer)

        acronymViewModel.fetchAcronyms(word)
        verify { observer.onChanged(errorMessage) }
    }
}