package com.example.login

import com.example.login.framework.network.api.response.Contact
import com.example.login.framework.network.api.response.ContactResponse
import com.example.login.framework.network.api.state.ContactListState
import com.example.login.repository.ContactRepository
import com.example.login.repository.wrapper.ResponseWrapper
import com.example.login.viewmodels.ContactViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
@ExperimentalCoroutinesApi
class ContactViewModelTest {

    private lateinit var viewModel: ContactViewModel

    @Mock
    private lateinit var repository: ContactRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher) // Use test dispatcher for coroutine control
        viewModel = ContactViewModel(repository, testDispatcher)
    }

    @Test
    fun `getContactListAsync should emit Success when repository returns data`() = runTest {
        // Arrange
        val dummyData = ContactResponse(
            page = 1,
            per_page = 1,
            total = 1,
            total_pages = 1,
            data = listOf(
                Contact(1, "john.doe@email.com", "John", "Doe", "https://image.url")
            )
        )

        Mockito.`when`(repository.getContactListAsync(0, 0))
            .thenReturn(ResponseWrapper.ResponseSuccess(dummyData))

        // Act
        viewModel.getContactListAsync(0, 0)
        advanceUntilIdle() // Let coroutine complete

        // Assert
        val state = viewModel.contactState.first { it is ContactListState.Success }
        assertTrue(state is ContactListState.Success)
        assertEquals(dummyData, (state as ContactListState.Success).data)
    }

    @Test
    fun `getContactListAsync should emit Failure when repository throws exception`() = runTest {
        // Arrange
        Mockito.`when`(repository.getContactListAsync(0, 0))
            .thenThrow(RuntimeException("error"))

        // Act
        viewModel.getContactListAsync(0, 0)
        advanceUntilIdle()

        // Assert
        val state = viewModel.contactState.first { it is ContactListState.Failure }
        assertTrue(state is ContactListState.Failure)
        assertEquals("error", (state as ContactListState.Failure).message)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
