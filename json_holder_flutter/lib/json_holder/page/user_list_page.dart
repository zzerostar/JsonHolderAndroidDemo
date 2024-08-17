import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:json_holder_flutter/json_holder/model/user.dart';
import 'package:json_holder_flutter/json_holder/page/state_processor_mixin.dart';
import 'package:json_holder_flutter/json_holder/repository/user_repository.dart';
import 'package:json_holder_flutter/utils/ui_util.dart';

class UserListPage extends ConsumerWidget with StateProcessorMixin {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final state = ref.watch(userListProvider);
    return Scaffold(
      body: process(state, buildContent),
    );
  }

  Widget buildContent(List<User> users) {
    return ListView.separated(
        itemBuilder: (context, index) {
          User user = users[index];
          return Container(
            padding: EdgeInsets.symmetric(horizontal: 12, vertical: 16),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Icon(
                      Icons.person,
                      size: 19,
                    ),
                    UiUtil.horizontalMargin(5),
                    Text(
                      user.name,
                      style:
                          TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
                UiUtil.verticalMargin(3),
                Row(
                  children: [
                    Icon(Icons.email, size: 16),
                    UiUtil.horizontalMargin(5),
                    Text(
                      user.email,
                      style: TextStyle(fontSize: 13, color: Colors.black45),
                    ),
                  ],
                ),
                UiUtil.verticalMargin(3),
                Row(
                  children: [
                    Icon(Icons.phone, size: 16),
                    UiUtil.horizontalMargin(5),
                    Text(
                      user.phone,
                      style: TextStyle(fontSize: 13, color: Colors.black45),
                    ),
                  ],
                ),
                UiUtil.verticalMargin(3),
                Row(
                  children: [
                    Icon(Icons.location_on_rounded, size: 16),
                    UiUtil.horizontalMargin(5),
                    Text(
                      getAddress(user.address),
                      style: TextStyle(fontSize: 13, color: Colors.black45),
                    ),
                  ],
                ),

                UiUtil.verticalMargin(3),
                Row(
                  children: [
                    Icon(Icons.business, size: 16),
                    UiUtil.horizontalMargin(5),
                    Text(
                      user.company.name,
                      style: TextStyle(fontSize: 13, color: Colors.black45),
                    ),
                  ],
                ),

              ],
            ),
          );
        },
        separatorBuilder: (BuildContext context, int index) {
          return Container(
            width: double.infinity,
            height: 1,
            color: Colors.grey,
          );
        },
        itemCount: users.length);
  }

  String getAddress(Address address) {
    return "${address.street} ${address.suite} ${address.city} ";
  }
}
