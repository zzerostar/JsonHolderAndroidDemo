package com.ziano.jsonholderandroid.compose.vm

import com.ziano.jsonholderandroid.common.data.model.NetResponse
import com.ziano.jsonholderandroid.common.data.model.Post
import com.ziano.jsonholderandroid.common.data.repositroy.PostRepository
import com.ziano.jsonholderandroid.compose.base.ListViewStatus
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/8/27
 * @desc
 */
class PostListViewModelTest {

    val dispatcher = StandardTestDispatcher()

    lateinit var mockFirstPagePosts: MutableList<Post>

    lateinit var mockSecondPagePosts: MutableList<Post>

    lateinit var mockTwoPagePosts: MutableList<Post>

    @Before
    fun setUp() {
        mockFirstPagePosts = mutableListOf()
        mockSecondPagePosts = mutableListOf()
        mockTwoPagePosts = mutableListOf()

        repeat(20) {
            val post = mockk<Post>()
            mockFirstPagePosts.add(post)
            mockTwoPagePosts.add(post)
        }

        repeat(20) {
            val post = mockk<Post>()
            mockSecondPagePosts.add(post)
            mockTwoPagePosts.add(post)
        }
    }

    @Test
    fun testInitAndGetFirstPagePost() {
        val postRepository = mockk<PostRepository>()
        coEvery { postRepository.getPostList(0) } returns flowOf(NetResponse.Success(mockFirstPagePosts))

        PostListViewModel(postRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        coVerify { postRepository.getPostList(0) }
    }

    @Test
    fun testLoadFirstPageSuccessThenStateChangeToSuccess() {
        val postRepository = mockk<PostRepository>()
        coEvery { postRepository.getPostList(0) } returns flowOf(NetResponse.Success(mockFirstPagePosts))

        val viewModel = PostListViewModel(postRepository, dispatcher)
        Assertions.assertEquals(viewModel.uiState.value.status, ListViewStatus.refreshing)
        dispatcher.scheduler.advanceUntilIdle()

        val expectedState = PostListViewState(status = ListViewStatus.success, data = mockFirstPagePosts)
        Assertions.assertEquals(viewModel.uiState.value.status, expectedState.status)
        Assertions.assertEquals(viewModel.uiState.value.data, expectedState.data)

    }

    @Test
    fun testLoadFirstPageFailThenStateChangeToError() {
        val getPostFailedMsg = "Get first page post fail"
        val postRepository = mockk<PostRepository>()
        coEvery { postRepository.getPostList(0) } returns flowOf(NetResponse.Failed(getPostFailedMsg))

        val viewModel = PostListViewModel(postRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()


        val expectedState = PostListViewState(status = ListViewStatus.error, errorMsg = getPostFailedMsg)
        Assertions.assertEquals(viewModel.uiState.value.status, expectedState.status)
        Assertions.assertEquals(viewModel.uiState.value.errorMsg, expectedState.errorMsg)
    }

    @Test
    fun testLoadSecondPageSuccessThenDataSizeIncreased() {
        //prepare first page of loaded data
        val postRepository = mockk<PostRepository>()
        coEvery { postRepository.getPostList(0) } returns flowOf(NetResponse.Success(mockFirstPagePosts))
        coEvery { postRepository.getPostList(mockFirstPagePosts.size) } returns flowOf(NetResponse.Success(mockSecondPagePosts))

        val viewModel = PostListViewModel(postRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        //preparation End

        //Get second page
        viewModel.handleIntent(PostListViewIntent.Appending())
        Assertions.assertEquals(ListViewStatus.appending, viewModel.uiState.value.status)
        dispatcher.scheduler.advanceUntilIdle()

        val expectedState = PostListViewState(status = ListViewStatus.success, data = mockTwoPagePosts)
        Assertions.assertEquals(expectedState.status, viewModel.uiState.value.status)
        Assertions.assertEquals(expectedState.data, viewModel.uiState.value.data)

    }

    @Test
    fun testLoadSecondPageFailThenStateRemainFirstPage() {
        //prepare first page of loaded data
        val getSecondPageFailedMsg = "Get second page fail"
        val postRepository = mockk<PostRepository>()
        coEvery { postRepository.getPostList(0) } returns flowOf(NetResponse.Success(mockFirstPagePosts))
        coEvery { postRepository.getPostList(mockFirstPagePosts.size) } returns flowOf(NetResponse.Failed(getSecondPageFailedMsg))

        val viewModel = PostListViewModel(postRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        //Get second page
        viewModel.handleIntent(PostListViewIntent.Appending())
        dispatcher.scheduler.advanceUntilIdle()

        val expectedState = PostListViewState(status = ListViewStatus.success, data = mockFirstPagePosts)
        Assertions.assertEquals(expectedState.status, viewModel.uiState.value.status)
        Assertions.assertEquals(expectedState.data, viewModel.uiState.value.data)
    }

    @Test
    fun testRefreshWhenLoadedTwoPagesThenDataRefresh() {
        //Prepare two pages of loaded data
        val postRepository = mockk<PostRepository>()
        coEvery { postRepository.getPostList(0) } returns flowOf(NetResponse.Success(mockFirstPagePosts))
        coEvery { postRepository.getPostList(mockFirstPagePosts.size) } returns flowOf(NetResponse.Success(mockSecondPagePosts))

        val viewModel = PostListViewModel(postRepository, dispatcher)
        dispatcher.scheduler.advanceUntilIdle()

        viewModel.handleIntent(PostListViewIntent.Appending())
        dispatcher.scheduler.advanceUntilIdle()

        //Refresh page
        viewModel.handleIntent(PostListViewIntent.Refreshing())
        dispatcher.scheduler.advanceUntilIdle()

        val expectedState = PostListViewState(status = ListViewStatus.success, data = mockFirstPagePosts)
        Assertions.assertEquals(expectedState.status, viewModel.uiState.value.status)
        Assertions.assertEquals(expectedState.data, viewModel.uiState.value.data)

    }

    @After
    fun tearDown() {
    }
}