import 'dart:async';

import 'package:easy_refresh/easy_refresh.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:json_holder_flutter/json_holder/model/post.dart';
import 'package:json_holder_flutter/json_holder/repository/post_repository.dart';

part 'post_list_controller.g.dart';

const int PAGE_SIZE = 20;

@riverpod
class PostListController extends _$PostListController {
  final EasyRefreshController _refreshController = EasyRefreshController(
      controlFinishRefresh: true, controlFinishLoad: true);

  EasyRefreshController get refreshController => _refreshController;

  int page = 1;

  List<Post> _data = [];

  @override
  Future<List<Post>> build() async {
    await refresh(loading: true);
    return _data;
  }

  Future refresh({bool loading = false}) async {
    if(loading) {
      state = const AsyncLoading();
    }
    page = 1;
    try {
      PostRepository postRepository = ref.read(postRepoProvider);

      List<Post> posts =
          await postRepository.getPostList(page, PAGE_SIZE);

      _data = posts;
      state = AsyncData(posts);
      page++;
      refreshController.finishRefresh();
    } catch (e, stackTrace) {
      state = AsyncError(e, stackTrace);
      refreshController.finishRefresh();
    }
  }

  Future loadMore() async {
    try {
      PostRepository postRepository = ref.read(postRepoProvider);

      List<Post> posts =
          await postRepository.getPostList(page, PAGE_SIZE);

      _data.addAll(posts);
      state = AsyncData(_data);
      page++;
      refreshController.finishLoad(IndicatorResult.success);
    } catch (e) {
      refreshController.finishLoad(IndicatorResult.fail);
    }
  }
}
