import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:json_holder_flutter/json_holder/model/user.dart';
import 'package:json_holder_flutter/net/dio_client.dart';

part "user_repository.g.dart";

const String PATH_USER_LIST = "/users";

@riverpod
Future<List<User>> userList(UserListRef ref) async {
  DioClient dioClient = ref.read(dioClientProvider);
  final response = await dioClient.get(PATH_USER_LIST);

  List<User> users = response.data.map<User>((e) => User.fromJson(e)).toList();
  return users;
}
