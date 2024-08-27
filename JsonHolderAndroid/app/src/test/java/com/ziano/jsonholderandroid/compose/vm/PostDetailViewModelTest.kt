package com.ziano.jsonholderandroid.compose.vm

import androidx.lifecycle.SavedStateHandle
import com.ziano.jsonholderandroid.common.data.model.Comment
import com.ziano.jsonholderandroid.common.data.model.NetResponse
import com.ziano.jsonholderandroid.common.data.model.Post
import com.ziano.jsonholderandroid.common.data.repositroy.PostRepository
import com.ziano.jsonholderandroid.compose.base.ViewStatus
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions


/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/8/3
 * @desc
 */
class PostDetailViewModelTest {

    val MOCK_POST_ID = 99

    lateinit var savedStateHandle: SavedStateHandle
    lateinit var dispatcher: TestDispatcher
    lateinit var mockPost: Post
    lateinit var mockComments: MutableList<Comment>

    @Before
    fun setUp() {
        savedStateHandle = mockk<SavedStateHandle>()
        mockPost = mockk<Post>()
        mockComments = mutableListOf()
        repeat(10) {
            mockComments.add(mockk<Comment>())
        }
        dispatcher = StandardTestDispatcher()

        every { savedStateHandle.get<Int>("postId") } returns MOCK_POST_ID
    }

    @Test
    fun testInitThenCallGetDetailFunctionWithRightParam() = runTest {
        val postRepository = mockk<PostRepository>()
        coEvery { postRepository.getPostDetail(MOCK_POST_ID) } returns flowOf((NetResponse.Success(mockPost)))
        coEvery { postRepository.getComments(MOCK_POST_ID) } returns flowOf(NetResponse.Success(mockComments))

        PostDetailViewModel(savedStateHandle, postRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        coVerify { postRepository.getPostDetail(MOCK_POST_ID) }
        coVerify { postRepository.getComments(MOCK_POST_ID) }
    }

    @Test
    fun testGetPostDetailSuccessThenStateChangeToSuccess() = runTest {
        val postRepository = mockk<PostRepository>()
        coEvery { postRepository.getPostDetail(MOCK_POST_ID) } returns flowOf((NetResponse.Success(mockPost)))
        coEvery { postRepository.getComments(MOCK_POST_ID) } returns flowOf(NetResponse.Success(mockComments))

        val viewModel = PostDetailViewModel(savedStateHandle, postRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        val expectedState = PostDetailViewState(status = ViewStatus.success, detail = mockPost, comments = mockComments)
        Assertions.assertEquals(expectedState.status, viewModel.uiState.value.status, "Failed --> Status is not correct")
        Assertions.assertEquals(expectedState.detail, viewModel.uiState.value.detail, "Failed --> PostDetail is not correct")
        Assertions.assertEquals(expectedState.comments, viewModel.uiState.value.comments, "Failed --> Comments is not correct")
    }

    @Test
    fun testGetPostDetailSuccessButGetCommentsFailedThenStateChangeToSuccess() = runTest {
        val postRepository = mockk<PostRepository>()
        val getCommentsFailedMsg = "Get comments fail"

        coEvery { postRepository.getPostDetail(MOCK_POST_ID) } returns flowOf((NetResponse.Success(mockPost)))
        coEvery { postRepository.getComments(MOCK_POST_ID) } returns flowOf(NetResponse.Failed(getCommentsFailedMsg))

        val viewModel = PostDetailViewModel(savedStateHandle, postRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        val expectedState = PostDetailViewState(status = ViewStatus.success, detail = mockPost)
        Assertions.assertEquals(expectedState.status, viewModel.uiState.value.status, "Failed --> Status is not correct")
        Assertions.assertEquals(expectedState.detail, viewModel.uiState.value.detail, "Failed --> PostDetail is not correct")
        Assertions.assertEquals(viewModel.uiState.value.comments.size, 0, "Failed --> Comments is not correct")
    }

    @Test
    fun testGetPostDetailFailedThenStateChangeToError() = runTest {
        val postRepository = mockk<PostRepository>()
        val getPostDetailFailMsg = "Get post detail fail"
        coEvery { postRepository.getPostDetail(MOCK_POST_ID) } returns flowOf(NetResponse.Failed(getPostDetailFailMsg))
        coEvery { postRepository.getComments(MOCK_POST_ID) } returns flowOf(NetResponse.Success(mockComments))

        val viewModel = PostDetailViewModel(savedStateHandle, postRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        val expectedState = PostDetailViewState(status = ViewStatus.error, errorMsg = getPostDetailFailMsg)
        coVerify(exactly = 0) { postRepository.getComments(MOCK_POST_ID) }
        Assertions.assertEquals(viewModel.uiState.value.status, expectedState.status, "Failed --> Status is not correct")
        Assertions.assertEquals(viewModel.uiState.value.detail, null, "Failed --> PostDetail is not correct")
        Assertions.assertEquals(viewModel.uiState.value.comments.size, 0, "Failed --> Comments is not correct")
        Assertions.assertEquals(viewModel.uiState.value.errorMsg, expectedState.errorMsg, "Failed --> The errorMsg is not correct")
    }

    @After
    fun tearDown() {
    }
}