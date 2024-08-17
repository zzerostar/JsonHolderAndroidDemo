// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'list_view_state_notifier.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

String _$listViewStateNotifierHash() =>
    r'23ace9d72b0edd61fc87fe944ce17a298fca40fe';

/// Copied from Dart SDK
class _SystemHash {
  _SystemHash._();

  static int combine(int hash, int value) {
    // ignore: parameter_assignments
    hash = 0x1fffffff & (hash + value);
    // ignore: parameter_assignments
    hash = 0x1fffffff & (hash + ((0x0007ffff & hash) << 10));
    return hash ^ (hash >> 6);
  }

  static int finish(int hash) {
    // ignore: parameter_assignments
    hash = 0x1fffffff & (hash + ((0x03ffffff & hash) << 3));
    // ignore: parameter_assignments
    hash = hash ^ (hash >> 11);
    return 0x1fffffff & (hash + ((0x00003fff & hash) << 15));
  }
}

abstract class _$ListViewStateNotifier<T>
    extends BuildlessAutoDisposeAsyncNotifier<List<T>> {
  late final RequestFun<List<T>> request;

  FutureOr<List<T>> build(
    RequestFun<List<T>> request,
  );
}

/// See also [ListViewStateNotifier].
@ProviderFor(ListViewStateNotifier)
const listViewStateNotifierProvider = ListViewStateNotifierFamily();

/// See also [ListViewStateNotifier].
class ListViewStateNotifierFamily extends Family {
  /// See also [ListViewStateNotifier].
  const ListViewStateNotifierFamily();

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'listViewStateNotifierProvider';

  /// See also [ListViewStateNotifier].
  ListViewStateNotifierProvider<T> call<T>(
    RequestFun<List<T>> request,
  ) {
    return ListViewStateNotifierProvider<T>(
      request,
    );
  }

  @visibleForOverriding
  @override
  ListViewStateNotifierProvider<Object?> getProviderOverride(
    covariant ListViewStateNotifierProvider<Object?> provider,
  ) {
    return call(
      provider.request,
    );
  }

  /// Enables overriding the behavior of this provider, no matter the parameters.
  Override overrideWith(ListViewStateNotifier Function() create) {
    return _$ListViewStateNotifierFamilyOverride(this, create);
  }
}

class _$ListViewStateNotifierFamilyOverride implements FamilyOverride {
  _$ListViewStateNotifierFamilyOverride(this.overriddenFamily, this.create);

  final ListViewStateNotifier Function() create;

  @override
  final ListViewStateNotifierFamily overriddenFamily;

  @override
  ListViewStateNotifierProvider getProviderOverride(
    covariant ListViewStateNotifierProvider provider,
  ) {
    return provider._copyWith(create);
  }
}

/// See also [ListViewStateNotifier].
class ListViewStateNotifierProvider<T>
    extends AutoDisposeAsyncNotifierProviderImpl<ListViewStateNotifier<T>,
        List<T>> {
  /// See also [ListViewStateNotifier].
  ListViewStateNotifierProvider(
    RequestFun<List<T>> request,
  ) : this._internal(
          () => ListViewStateNotifier<T>()..request = request,
          from: listViewStateNotifierProvider,
          name: r'listViewStateNotifierProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$listViewStateNotifierHash,
          dependencies: ListViewStateNotifierFamily._dependencies,
          allTransitiveDependencies:
              ListViewStateNotifierFamily._allTransitiveDependencies,
          request: request,
        );

  ListViewStateNotifierProvider._internal(
    super.create, {
    required super.name,
    required super.dependencies,
    required super.allTransitiveDependencies,
    required super.debugGetCreateSourceHash,
    required super.from,
    required this.request,
  }) : super.internal();

  final RequestFun<List<T>> request;

  @override
  FutureOr<List<T>> runNotifierBuild(
    covariant ListViewStateNotifier<T> notifier,
  ) {
    return notifier.build(
      request,
    );
  }

  @override
  Override overrideWith(ListViewStateNotifier<T> Function() create) {
    return ProviderOverride(
      origin: this,
      override: ListViewStateNotifierProvider<T>._internal(
        () => create()..request = request,
        from: from,
        name: null,
        dependencies: null,
        allTransitiveDependencies: null,
        debugGetCreateSourceHash: null,
        request: request,
      ),
    );
  }

  @override
  (RequestFun<List<T>>,) get argument {
    return (request,);
  }

  @override
  AutoDisposeAsyncNotifierProviderElement<ListViewStateNotifier<T>, List<T>>
      createElement() {
    return _ListViewStateNotifierProviderElement(this);
  }

  ListViewStateNotifierProvider _copyWith(
    ListViewStateNotifier Function() create,
  ) {
    return ListViewStateNotifierProvider._internal(
      () => create()..request = request,
      name: name,
      dependencies: dependencies,
      allTransitiveDependencies: allTransitiveDependencies,
      debugGetCreateSourceHash: debugGetCreateSourceHash,
      from: from,
      request: request,
    );
  }

  @override
  bool operator ==(Object other) {
    return other is ListViewStateNotifierProvider &&
        other.runtimeType == runtimeType &&
        other.request == request;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, request.hashCode);
    hash = _SystemHash.combine(hash, T.hashCode);

    return _SystemHash.finish(hash);
  }
}

mixin ListViewStateNotifierRef<T>
    on AutoDisposeAsyncNotifierProviderRef<List<T>> {
  /// The parameter `request` of this provider.
  RequestFun<List<T>> get request;
}

class _ListViewStateNotifierProviderElement<T>
    extends AutoDisposeAsyncNotifierProviderElement<ListViewStateNotifier<T>,
        List<T>> with ListViewStateNotifierRef<T> {
  _ListViewStateNotifierProviderElement(super.provider);

  @override
  RequestFun<List<T>> get request =>
      (origin as ListViewStateNotifierProvider<T>).request;
}
// ignore_for_file: type=lint
// ignore_for_file: subtype_of_sealed_class, invalid_use_of_internal_member, invalid_use_of_visible_for_testing_member, inference_failure_on_uninitialized_variable, inference_failure_on_function_return_type, inference_failure_on_untyped_parameter, deprecated_member_use_from_same_package
