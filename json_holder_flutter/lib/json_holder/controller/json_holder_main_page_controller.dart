import 'package:flutter/cupertino.dart';
import 'package:riverpod_annotation/riverpod_annotation.dart';

part 'json_holder_main_page_controller.g.dart';

@riverpod
class JsonHolderMainPageController extends _$JsonHolderMainPageController {
  PageController _pageController = PageController();

  get pageController => _pageController;

  @override
  int build() => 0;

  void setIndex(int index) {
    state = index;
    // _pageController.animateToPage(index, duration: duration, curve: curve)
    _pageController.jumpToPage(index);
  }
}
