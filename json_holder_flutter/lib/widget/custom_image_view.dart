import 'dart:io';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

class CustomImage extends StatelessWidget {
  final String imageUrl;
  final double? width;
  final double? height;
  final BoxFit fit;
  final String holderImg;
  final String errorImg;
  final Color? color;
  final double borderRadius;

  const CustomImage(this.imageUrl,
      {Key? key,
      this.width,
      this.height,
      this.fit = BoxFit.cover,
      this.borderRadius = 0,
      this.color,
      this.holderImg = 'assets/images/icon_empty.webp',
      this.errorImg = 'assets/images/icon_empty.webp'})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    Widget holderView = Image.asset(
      holderImg,
      width: width,
      height: height,
      fit: fit,
    );
    Widget errorView = Image.asset(
      errorImg,
      width: width,
      height: height,
      fit: fit,
    );

    Widget image;
    if (imageUrl.isEmpty || imageUrl.startsWith('http')) {
      image = CachedNetworkImage(
        imageUrl: imageUrl.replaceFirst('https', 'http'),
        width: width,
        height: height,
        fit: fit,
        color: color,
        placeholder: (_, __) => holderView,
        errorWidget: (_, __, dynamic error) => errorView,
      );
    } else if (imageUrl.startsWith('package')) {
      image = Image.asset(
        imageUrl,
        width: width,
        height: height,
        fit: fit,
        color: color,
      );
    } else {
      image = Image.file(File(imageUrl),
          width: width, height: height, fit: BoxFit.fill);
    }

    return ClipRRect(
      borderRadius: BorderRadius.circular(borderRadius),
      child: image, //设置圆角
    );
  }
}
