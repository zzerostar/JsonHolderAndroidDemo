import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:json_holder_flutter/constants/app_colors.dart';
import 'package:json_holder_flutter/json_holder/controller/comment_list_controller.dart';
import 'package:json_holder_flutter/json_holder/controller/post_detail_controller.dart';
import 'package:json_holder_flutter/json_holder/model/comment.dart';
import 'package:json_holder_flutter/utils/ui_util.dart';

class PostDetailPage extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final int postId = ModalRoute.of(context)!.settings.arguments as int;
    final detailState = ref.watch(postDetailControllerProvider(postId));
    final commentState = ref.watch(commentListControllerProvider(postId));

    return Scaffold(
        appBar: AppBar(
          title: Text("Post Detail"),
        ),
        body: detailState.when(
      data: (post) => CustomScrollView(
        slivers: [
          SliverToBoxAdapter(
            child: Container(
              padding: EdgeInsets.symmetric(vertical: 12, horizontal: 16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "ID: ${post.id}",
                    style: TextStyle(
                        fontSize: 18,
                        color: Colors.black,
                        fontWeight: FontWeight.bold),
                  ),
                  UiUtil.verticalMargin(10),
                  Text(
                    post.title,
                    style: TextStyle(
                      fontSize: 13,
                      color: Colors.black,
                    ),
                  ),
                  UiUtil.verticalMargin(10),
                  Text(
                    post.body,
                    style: TextStyle(
                      fontSize: 13,
                      color: Colors.black,
                    ),
                  ),
                  UiUtil.verticalMargin(18),
                ],
              ),
            ),
          ),
          SliverToBoxAdapter(
            child: Visibility(
                visible: commentState.value?.isNotEmpty ?? false,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(height: 12, color: AppColors.divier),
                    Container(
                      margin: EdgeInsets.only(left: 16, top: 12),
                      child: Text(
                        "Comments: ",
                        style: TextStyle(
                            fontSize: 20, fontWeight: FontWeight.bold),
                      ),
                    )
                  ],
                )),
          ),
          _buildComments(commentState)
        ],
      ),
      error: (err, stack) => Text("error"),
      loading: () => Center(
        child: CircularProgressIndicator(),
      ),
    ));
  }

  Widget _buildComments(AsyncValue<List<Comment>> asyncData) {
    return asyncData.when(
        data: (comments) => SliverList.separated(
              itemBuilder: (BuildContext context, int index) {
                Comment comment = comments[index];
                return Container(
                  padding: EdgeInsets.symmetric(vertical: 12, horizontal: 16),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        "ID: ${comment.id}",
                        style: TextStyle(
                            fontSize: 18,
                            color: Colors.black,
                            fontWeight: FontWeight.bold),
                      ),
                      UiUtil.verticalMargin(3),
                      Text(
                        comment.email,
                        style: TextStyle(
                          fontSize: 13,
                          color: Colors.grey,
                        ),
                      ),
                      UiUtil.verticalMargin(10),
                      Text(
                        comment.body,
                        style: TextStyle(
                          fontSize: 13,
                          color: Colors.black,
                        ),
                      ),
                    ],
                  ),
                );
              },
              itemCount: comments.length,
              separatorBuilder: (BuildContext context, int index) {
                return Container(
                  width: double.infinity,
                  height: 1,
                  color: Colors.grey,
                );
              },
            ),
        error: (e, _) => SliverToBoxAdapter(),
        loading: () => SliverToBoxAdapter());
  }
}
