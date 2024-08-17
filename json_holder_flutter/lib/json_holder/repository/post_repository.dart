import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:json_holder_flutter/json_holder/model/post.dart';
import 'package:json_holder_flutter/net/dio_client.dart';

part 'post_repository.g.dart';

const String PATH_POST_LIST = '/posts';
const String PATH_POST_DETAIL = '/posts/';

@riverpod
PostRepository postRepo(PostRepoRef ref) {
  return PostRepository(ref.read(dioClientProvider));
}

class PostRepository {
  DioClient dioClient;

  PostRepository(this.dioClient);

  Future<List<Post>> getPostList(int page, int size) async {
    final response = await dioClient.get(PATH_POST_LIST);
    List<Post> posts =
        response.data.map<Post>((e) => Post.fromJson(e)).toList();
    List<Post> filteredPosts = posts
        .where((e) => e.id > (page - 1) * 20 && e.id <= page * 20)
        .toList();
    return filteredPosts;
  }

  Future<Post> getPostDetail(int id) async {
    final response = await dioClient.get(PATH_POST_DETAIL + id.toString());
    return Post.fromJson(response.data);
  }
}
