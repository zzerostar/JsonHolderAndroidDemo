import 'package:riverpod_annotation/riverpod_annotation.dart';
import 'package:sprintf/sprintf.dart';
import 'package:json_holder_flutter/json_holder/model/photo.dart';
import 'package:json_holder_flutter/net/dio_client.dart';

part "photo_repository.g.dart";

const String PATH_PHOTO_LIST = "/albums/%d/photos";

@riverpod
PhotoRepository PhotoRepo(PhotoRepoRef ref) {
  return PhotoRepository(ref.read(dioClientProvider));
}

class PhotoRepository {
  DioClient dioClient;

  PhotoRepository(this.dioClient);

  Future<List<Photo>> getPhotoList(int albumId) async {
    final response = await dioClient.get(sprintf(PATH_PHOTO_LIST, [albumId]));
    List<Photo> photos =
        response.data.map<Photo>((e) => Photo.fromJson(e)).toList();
    return photos;
  }
}
