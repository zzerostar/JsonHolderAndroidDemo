import 'package:dio/dio.dart';
import 'package:easy_refresh/easy_refresh.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:json_holder_flutter/json_holder/model/post.dart';
import 'package:json_holder_flutter/utils/log_util.dart';

part 'list_view_state_notifier.g.dart';

typedef RequestFun<Result> = Future<Result> Function(int, int);

@riverpod
class ListViewStateNotifier<T> extends _$ListViewStateNotifier<T> {
  final EasyRefreshController _refreshController = EasyRefreshController(
      controlFinishRefresh: true, controlFinishLoad: true);

  EasyRefreshController get refreshController => _refreshController;

  late RequestFun<List<T>> request;

  int _curPage = 0;
  int page = 1;
  int size = 10;

  List<T> _data = [];

  @override
  Future<List<T>> build(
    RequestFun<List<T>> request,
  ) async {
    this.request = request;
    init();
    return _data;
  }


  void init() {
    refresh();
  }

  Future<void> refresh() async {
    _curPage = page;
    final List<T> list = await request!(_curPage, size);
    _data = list;
    state = AsyncData(_data);

    _curPage++;
    _refreshController.finishRefresh();
  }

  Future<void> loadMore() async {
    try {
      final List<T> list = await request!(_curPage, size);
      _data.addAll(list);
      state = AsyncData(_data);

      _refreshController.finishLoad(IndicatorResult.success);
    } catch (e) {
      _refreshController.finishLoad(IndicatorResult.fail);
      // state = ListViewState.error(e.toString());
    }
  }
}
