import 'package:freezed_annotation/freezed_annotation.dart';

part 'listview_state.freezed.dart';

@freezed
class ListViewState<T> with _$ListViewState<T> {
  const factory ListViewState.loading() = Loading;

  const factory ListViewState.empty() = Empty;

  const factory ListViewState.success(T data) = Success<T>;

  const factory ListViewState.error(String error) = Error;
}
