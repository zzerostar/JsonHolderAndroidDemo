import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

mixin StateProcessorMixin on ConsumerWidget {
  Widget process<T>(AsyncValue<T> asyncValue,
      Widget Function(T data) builder) {
    return asyncValue.when(
      data: (value) => builder(value),
      error: (error, _) => Center(child: Text(error.toString())),
      loading: () => Center(child: CircularProgressIndicator()),
    );
  }
}
