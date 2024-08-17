import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:json_holder_flutter/json_holder/controller/photo_list_controller.dart';
import 'package:json_holder_flutter/json_holder/model/photo.dart';
import 'package:json_holder_flutter/json_holder/page/state_processor_mixin.dart';
import 'package:json_holder_flutter/widget/custom_image_view.dart';

class PhotoListPage extends ConsumerWidget with StateProcessorMixin {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final state = ref.watch(photoListProvider);

    return Scaffold(
        body: process(
            state,
            (photos) => GridView.builder(
              addAutomaticKeepAlives: false,
                addRepaintBoundaries:false,
                  gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                      crossAxisCount: 2, childAspectRatio: 1),
                  itemBuilder: (BuildContext context, int index) {
                    Photo photo = photos[index];
                    return CustomImage(photo.url);
                  },
                  itemCount: photos.length,
                )));
  }
}
