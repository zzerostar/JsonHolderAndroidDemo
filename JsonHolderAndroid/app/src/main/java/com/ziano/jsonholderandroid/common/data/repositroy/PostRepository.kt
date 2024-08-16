package com.ziano.jsonholderandroid.common.data.repositroy

import com.ziano.jsonholderandroid.common.data.api.PostService
import com.ziano.jsonholderandroid.common.data.local.dao.CommentDao
import com.ziano.jsonholderandroid.common.data.local.dao.PostDao
import com.ziano.jsonholderandroid.common.data.model.Comment
import com.ziano.jsonholderandroid.common.data.model.NetResponse
import com.ziano.jsonholderandroid.common.data.model.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/24
 * @desc
 */
class PostRepository @Inject constructor(val postAPI: PostService, val postDao: PostDao, val commentDao: CommentDao) : BaseRepository() {

    suspend fun getPostList(startIndex: Int): Flow<NetResponse<List<Post>>> {
        return request(object : NetRequest<List<Post>> {
            override suspend fun fetchFromRemote(): Response<List<Post>> {
                return postAPI.getPosts(startIndex, 20)
            }

            override suspend fun cacheRemoteData(data: List<Post>) {
                postDao.insertPosts(data)
            }

            override suspend fun getFromLocal(): List<Post> {
                return postDao.queryByIdRange(startIndex, startIndex + 20)
            }
        })
    }

    suspend fun getPostDetail(id: Int): Flow<NetResponse<Post>> {
        return request(object : NetRequest<Post> {
            override suspend fun fetchFromRemote(): Response<Post> {
                return postAPI.getPostDetail(id)
            }

            override suspend fun cacheRemoteData(data: Post) {
                postDao.insertPost(data)
            }

            override suspend fun getFromLocal(): Post {
                return postDao.queryPostById(id)
            }
        })
    }

    suspend fun getComments(postId: Int): Flow<NetResponse<List<Comment>>> {
        return request(object : NetRequest<List<Comment>> {
            override suspend fun fetchFromRemote(): Response<List<Comment>> {
                return postAPI.getPostComments(postId)
            }

            override suspend fun cacheRemoteData(data: List<Comment>) {
                commentDao.insertComments(data)
            }

            override suspend fun getFromLocal(): List<Comment> {
                return commentDao.queryByPostId(postId)
            }
        })
    }


}


