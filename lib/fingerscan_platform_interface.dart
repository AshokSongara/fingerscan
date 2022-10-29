import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'fingerscan_method_channel.dart';

abstract class FingerscanPlatform extends PlatformInterface {
  /// Constructs a FingerscanPlatform.
  FingerscanPlatform() : super(token: _token);

  static final Object _token = Object();

  static FingerscanPlatform _instance = MethodChannelFingerscan();

  /// The default instance of [FingerscanPlatform] to use.
  ///
  /// Defaults to [MethodChannelFingerscan].
  static FingerscanPlatform get instance => _instance;
  
  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FingerscanPlatform] when
  /// they register themselves.
  static set instance(FingerscanPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
