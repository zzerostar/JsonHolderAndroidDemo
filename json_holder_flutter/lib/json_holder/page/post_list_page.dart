import 'package:easy_refresh/easy_refresh.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:json_holder_flutter/json_holder/controller/post_list_controller.dart';
import 'package:json_holder_flutter/json_holder/model/post.dart';
import 'package:json_holder_flutter/json_holder/page/post_detail_page.dart';
import 'package:json_holder_flutter/json_holder/page/state_processor_mixin.dart';
import 'package:json_holder_flutter/utils/ui_util.dart';

class PostListPage extends ConsumerWidget with StateProcessorMixin {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final state = ref.watch(postListControllerProvider);

    return Scaffold(
      body: Container(
          child:
              process<List<Post>>(state, (value) => buildContent(value, ref))),
    );
  }

  Widget buildContent(List<Post> posts, WidgetRef ref) {
    return EasyRefresh(
        controller:
            ref.read(postListControllerProvider.notifier).refreshController,
        onRefresh: () async {
          ref.read(postListControllerProvider.notifier).refresh();
        },
        onLoad: () async {
          ref.read(postListControllerProvider.notifier).loadMore();
        },
        child: ListView.separated(
          itemBuilder: (context, index) {
            Post post = posts[index];
            return InkWell(
              onTap: () {
                Navigator.push(
                    context,
                    new MaterialPageRoute(
                        builder: (_) {
                          return PostDetailPage();
                        },
                        settings: RouteSettings(arguments: post.id)));
              },
              child: Container(
                padding: EdgeInsets.only(top: 12, bottom: 12, left: 16),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "ID: ${post.id}",
                      style: TextStyle(
                          color: Colors.black,
                          fontSize: 18,
                          fontWeight: FontWeight.bold),
                    ),
                    UiUtil.verticalMargin(10),
                    Text(
                      post.title,
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: 13,
                      ),
                    ),
                    UiUtil.verticalMargin(10),
                    Text(
                      post.body,
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: 13,
                      ),
                    ),
                  ],
                ),
              ),
            );
          },
          itemCount: posts.length,
          separatorBuilder: (BuildContext context, int index) {
            return Container(
              width: double.infinity,
              height: 1,
              color: Colors.grey,
            );
          },
        ));
  }
}
