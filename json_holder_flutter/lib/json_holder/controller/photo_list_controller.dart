

import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:json_holder_flutter/json_holder/model/photo.dart';
import 'package:json_holder_flutter/json_holder/repository/photo_repository.dart';

part 'photo_list_controller.g.dart';

@riverpod
Future<List<Photo>> photoList(PhotoListRef ref) async {
  var photoRepo = ref.read(photoRepoProvider);
  return photoRepo.getPhotoList(1);

}