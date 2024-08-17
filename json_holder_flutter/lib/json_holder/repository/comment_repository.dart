import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:sprintf/sprintf.dart';
import 'package:json_holder_flutter/json_holder/model/comment.dart';
import 'package:json_holder_flutter/net/dio_client.dart';

part "comment_repository.g.dart";

const String PATH_COMMENT_LIST = "/posts/%d/comments";

@riverpod
CommentRepository commentRepo(CommentRepoRef ref) {
  return CommentRepository(ref.read(dioClientProvider));
}

class CommentRepository {
  DioClient dioClient;

  CommentRepository(this.dioClient);

  Future<List<Comment>> getCommentList(int postId) async {
    final response = await dioClient.get(sprintf(PATH_COMMENT_LIST, [postId]));
    List<Comment> comments =
        response.data.map<Comment>((e) => Comment.fromJson(e)).toList();
    return comments;
  }
}
