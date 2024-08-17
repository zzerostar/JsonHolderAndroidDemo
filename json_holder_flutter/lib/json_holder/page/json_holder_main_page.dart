import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:json_holder_flutter/json_holder/controller/json_holder_main_page_controller.dart';
import 'package:json_holder_flutter/json_holder/page/photo_list_page.dart';
import 'package:json_holder_flutter/json_holder/page/post_list_page.dart';
import 'package:json_holder_flutter/json_holder/page/user_list_page.dart';
import 'package:json_holder_flutter/utils/keep_alive_wrapper.dart';

class JsonHolderMainPage extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    return Scaffold(
      body: PageView(
        controller: ref
            .read(jsonHolderMainPageControllerProvider.notifier)
            .pageController,
        onPageChanged: (index) => onPageChanged(ref, index),
        children: [
          KeepAliveWrapper(child: PostListPage()),
          KeepAliveWrapper(child: PhotoListPage()),
          KeepAliveWrapper(child: UserListPage()),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const [
          BottomNavigationBarItem(
              icon: Icon(Icons.local_post_office_outlined), label: "Post"),
          BottomNavigationBarItem(icon: Icon(Icons.photo), label: "Photo"),
          BottomNavigationBarItem(icon: Icon(Icons.person), label: "User"),
        ],
        onTap: (index) => onTap(ref, index),
        currentIndex: ref.watch(jsonHolderMainPageControllerProvider),
        selectedItemColor: Colors.orange,
      ),
    );
  }

  void onPageChanged(WidgetRef ref, int index) {
    ref.read(jsonHolderMainPageControllerProvider.notifier).setIndex(index);
  }

  void onTap(WidgetRef ref, int index) {
    ref.read(jsonHolderMainPageControllerProvider.notifier).setIndex(index);
  }
}
