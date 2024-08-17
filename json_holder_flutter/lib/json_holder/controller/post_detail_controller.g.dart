// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'post_detail_controller.dart';

// **************************************************************************
// RiverpodGenerator
// **************************************************************************

String _$postDetailControllerHash() =>
    r'6dedb376deba946a89686c7589cd47cb4374e9e0';

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

abstract class _$PostDetailController
    extends BuildlessAutoDisposeAsyncNotifier<Post> {
  late final int postId;

  FutureOr<Post> build(
    int postId,
  );
}

/// See also [PostDetailController].
@ProviderFor(PostDetailController)
const postDetailControllerProvider = PostDetailControllerFamily();

/// See also [PostDetailController].
class PostDetailControllerFamily extends Family {
  /// See also [PostDetailController].
  const PostDetailControllerFamily();

  static const Iterable<ProviderOrFamily>? _dependencies = null;

  static const Iterable<ProviderOrFamily>? _allTransitiveDependencies = null;

  @override
  Iterable<ProviderOrFamily>? get dependencies => _dependencies;

  @override
  Iterable<ProviderOrFamily>? get allTransitiveDependencies =>
      _allTransitiveDependencies;

  @override
  String? get name => r'postDetailControllerProvider';

  /// See also [PostDetailController].
  PostDetailControllerProvider call(
    int postId,
  ) {
    return PostDetailControllerProvider(
      postId,
    );
  }

  @visibleForOverriding
  @override
  PostDetailControllerProvider getProviderOverride(
    covariant PostDetailControllerProvider provider,
  ) {
    return call(
      provider.postId,
    );
  }

  /// Enables overriding the behavior of this provider, no matter the parameters.
  Override overrideWith(PostDetailController Function() create) {
    return _$PostDetailControllerFamilyOverride(this, create);
  }
}

class _$PostDetailControllerFamilyOverride implements FamilyOverride {
  _$PostDetailControllerFamilyOverride(this.overriddenFamily, this.create);

  final PostDetailController Function() create;

  @override
  final PostDetailControllerFamily overriddenFamily;

  @override
  PostDetailControllerProvider getProviderOverride(
    covariant PostDetailControllerProvider provider,
  ) {
    return provider._copyWith(create);
  }
}

/// See also [PostDetailController].
class PostDetailControllerProvider
    extends AutoDisposeAsyncNotifierProviderImpl<PostDetailController, Post> {
  /// See also [PostDetailController].
  PostDetailControllerProvider(
    int postId,
  ) : this._internal(
          () => PostDetailController()..postId = postId,
          from: postDetailControllerProvider,
          name: r'postDetailControllerProvider',
          debugGetCreateSourceHash:
              const bool.fromEnvironment('dart.vm.product')
                  ? null
                  : _$postDetailControllerHash,
          dependencies: PostDetailControllerFamily._dependencies,
          allTransitiveDependencies:
              PostDetailControllerFamily._allTransitiveDependencies,
          postId: postId,
        );

  PostDetailControllerProvider._internal(
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
  FutureOr<Post> runNotifierBuild(
    covariant PostDetailController notifier,
  ) {
    return notifier.build(
      postId,
    );
  }

  @override
  Override overrideWith(PostDetailController Function() create) {
    return ProviderOverride(
      origin: this,
      override: PostDetailControllerProvider._internal(
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
  AutoDisposeAsyncNotifierProviderElement<PostDetailController, Post>
      createElement() {
    return _PostDetailControllerProviderElement(this);
  }

  PostDetailControllerProvider _copyWith(
    PostDetailController Function() create,
  ) {
    return PostDetailControllerProvider._internal(
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
    return other is PostDetailControllerProvider && other.postId == postId;
  }

  @override
  int get hashCode {
    var hash = _SystemHash.combine(0, runtimeType.hashCode);
    hash = _SystemHash.combine(hash, postId.hashCode);

    return _SystemHash.finish(hash);
  }
}

mixin PostDetailControllerRef on AutoDisposeAsyncNotifierProviderRef<Post> {
  /// The parameter `postId` of this provider.
  int get postId;
}

class _PostDetailControllerProviderElement
    extends AutoDisposeAsyncNotifierProviderElement<PostDetailController, Post>
    with PostDetailControllerRef {
  _PostDetailControllerProviderElement(super.provider);

  @override
  int get postId => (origin as PostDetailControllerProvider).postId;
}
// ignore_for_file: type=lint
// ignore_for_file: subtype_of_sealed_class, invalid_use_of_internal_member, invalid_use_of_visible_for_testing_member, inference_failure_on_uninitialized_variable, inference_failure_on_function_return_type, inference_failure_on_untyped_parameter, deprecated_member_use_from_same_package
