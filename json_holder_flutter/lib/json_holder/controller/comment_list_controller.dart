import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:json_holder_flutter/json_holder/model/comment.dart';
import 'package:json_holder_flutter/json_holder/repository/comment_repository.dart';
import 'package:json_holder_flutter/utils/log_util.dart';

part 'comment_list_controller.g.dart';

@riverpod
class CommentListController extends _$CommentListController {
  @override
  Future<List<Comment>> build(int postId) async {
    LogUtil.d("CommentListController build");
    CommentRepository commentRepository = ref.read(commentRepoProvider);
    return await commentRepository.getCommentList(postId);
  }
}
