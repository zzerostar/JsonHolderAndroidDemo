// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'comment_list_controller.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

String _$commentListControllerHash() =>
    r'1bf14e5cf3915e217af0014e9f85df861579c53a';

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

abstract class _$CommentListController
    extends BuildlessAutoDisposeAsyncNotifier<List<Comment>> {
  late final int postId;

  FutureOr<List<Comment>> build(
    int postId,
  );
}

/// See also [CommentListController].
@ProviderFor(CommentListController)
const commentListControllerProvider = CommentListControllerFamily();

/// See also [CommentListController].
class CommentListControllerFamily extends Family {
  /// See also [CommentListController].
  const CommentListControllerFamily();

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'commentListControllerProvider';

  /// See also [CommentListController].
  CommentListControllerProvider call(
    int postId,
  ) {
    return CommentListControllerProvider(
      postId,
    );
  }

  @visibleForOverriding
  @override
  CommentListControllerProvider getProviderOverride(
    covariant CommentListControllerProvider provider,
  ) {
    return call(
      provider.postId,
    );
  }

  /// Enables overriding the behavior of this provider, no matter the parameters.
  Override overrideWith(CommentListController Function() create) {
    return _$CommentListControllerFamilyOverride(this, create);
  }
}

class _$CommentListControllerFamilyOverride implements FamilyOverride {
  _$CommentListControllerFamilyOverride(this.overriddenFamily, this.create);

  final CommentListController Function() create;

  @override
  final CommentListControllerFamily overriddenFamily;

  @override
  CommentListControllerProvider getProviderOverride(
    covariant CommentListControllerProvider provider,
  ) {
    return provider._copyWith(create);
  }
}

/// See also [CommentListController].
class CommentListControllerProvider
    extends AutoDisposeAsyncNotifierProviderImpl<CommentListController,
        List<Comment>> {
  /// See also [CommentListController].
  CommentListControllerProvider(
    int postId,
  ) : this._internal(
          () => CommentListController()..postId = postId,
          from: commentListControllerProvider,
          name: r'commentListControllerProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$commentListControllerHash,
          dependencies: CommentListControllerFamily._dependencies,
          allTransitiveDependencies:
              CommentListControllerFamily._allTransitiveDependencies,
          postId: postId,
        );

  CommentListControllerProvider._internal(
    super.create, {
    required super.name,
    required super.dependencies,
    required super.allTransitiveDependencies,
    required super.debugGetCreateSourceHash,
    required super.from,
    required this.postId,
  }) : super.internal();

  final int postId;

  @override
  FutureOr<List<Comment>> runNotifierBuild(
    covariant CommentListController notifier,
  ) {
    return notifier.build(
      postId,
    );
  }

  @override
  Override overrideWith(CommentListController Function() create) {
    return ProviderOverride(
      origin: this,
      override: CommentListControllerProvider._internal(
        () => create()..postId = postId,
        from: from,
        name: null,
        dependencies: null,
        allTransitiveDependencies: null,
        debugGetCreateSourceHash: null,
        postId: postId,
      ),
    );
  }

  @override
  (int,) get argument {
    return (postId,);
  }

  @override
  AutoDisposeAsyncNotifierProviderElement<CommentListController, List<Comment>>
      createElement() {
    return _CommentListControllerProviderElement(this);
  }

  CommentListControllerProvider _copyWith(
    CommentListController Function() create,
  ) {
    return CommentListControllerProvider._internal(
      () => create()..postId = postId,
      name: name,
      dependencies: dependencies,
      allTransitiveDependencies: allTransitiveDependencies,
      debugGetCreateSourceHash: debugGetCreateSourceHash,
      from: from,
      postId: postId,
    );
  }

  @override
  bool operator ==(Object other) {
    return other is CommentListControllerProvider && other.postId == postId;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, postId.hashCode);

    return _SystemHash.finish(hash);
  }
}

mixin CommentListControllerRef
    on AutoDisposeAsyncNotifierProviderRef<List<Comment>> {
  /// The parameter `postId` of this provider.
  int get postId;
}

class _CommentListControllerProviderElement
    extends AutoDisposeAsyncNotifierProviderElement<CommentListController,
        List<Comment>> with CommentListControllerRef {
  _CommentListControllerProviderElement(super.provider);

  @override
  int get postId => (origin as CommentListControllerProvider).postId;
}
// ignore_for_file: type=lint
// ignore_for_file: subtype_of_sealed_class, invalid_use_of_internal_member, invalid_use_of_visible_for_testing_member, inference_failure_on_uninitialized_variable, inference_failure_on_function_return_type, inference_failure_on_untyped_parameter, deprecated_member_use_from_same_package
