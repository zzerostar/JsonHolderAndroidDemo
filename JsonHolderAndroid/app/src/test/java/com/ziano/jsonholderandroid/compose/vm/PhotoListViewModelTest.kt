package com.ziano.jsonholderandroid.compose.vm

import com.ziano.jsonholderandroid.common.data.model.NetResponse
import com.ziano.jsonholderandroid.common.data.model.Photo
import com.ziano.jsonholderandroid.common.data.repositroy.PhotoRepository
import com.ziano.jsonholderandroid.compose.base.ViewStatus
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions


/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/8/26
 * @desc
 */
class PhotoListViewModelTest {

    lateinit var dispatcher: TestDispatcher

    lateinit var mockPhotos: MutableList<Photo>

    val MOCK_ALUBM_ID = 1

    @Before
    fun setUp() {
        mockPhotos = mutableListOf<Photo>()
        repeat(20) {
            mockPhotos.add(mockk<Photo>())
        }
        dispatcher = StandardTestDispatcher()
    }

    @Test
    fun testInitAndGetPhotoList() = runTest {
        val photoRepository = mockk<PhotoRepository>()
        coEvery { photoRepository.getPhotoList(MOCK_ALUBM_ID) } returns flowOf(NetResponse.Success<List<Photo>>(mockPhotos))

        PhotoListViewModel(photoRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        coVerify { photoRepository.getPhotoList(MOCK_ALUBM_ID) }
    }

    @Test
    fun testGetPhotosSuccessAndStateChangeToSuccess() = runTest {
        val photoRepository = mockk<PhotoRepository>()
        coEvery { photoRepository.getPhotoList(MOCK_ALUBM_ID) } returns flowOf(NetResponse.Success<List<Photo>>(mockPhotos))

        val viewModel = PhotoListViewModel(photoRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        val expectedState = PhotoListViewState(status = ViewStatus.success, data = mockPhotos)
        Assertions.assertEquals(expectedState.status, viewModel.uiState.value.status)
        Assertions.assertEquals(expectedState.data, viewModel.uiState.value.data)

    }

    @Test
    fun testGetPhotosFailedAndStateChangeToError() = runTest {
        val photoRepository = mockk<PhotoRepository>()
        val errorMsg = "Test fail error msg"
        coEvery { photoRepository.getPhotoList(1) } returns flowOf(NetResponse.Failed(errorMsg))

        val viewModel = PhotoListViewModel(photoRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        val expectedState = PhotoListViewState(status = ViewStatus.error, errorMsg = errorMsg)
        Assertions.assertEquals(expectedState.status, viewModel.uiState.value.status)
        Assertions.assertEquals(expectedState.errorMsg, viewModel.uiState.value.errorMsg)
    }

    @After
    fun tearDown() {
    }
}