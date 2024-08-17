import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:json_holder_flutter/json_holder/model/post.dart';
import 'package:json_holder_flutter/json_holder/repository/post_repository.dart';

part 'post_detail_controller.g.dart';

@riverpod
class PostDetailController extends _$PostDetailController {

  @override
  Future<Post> build(int postId) async {
    return await ref.read(postRepoProvider).getPostDetail(postId);
  }
}
