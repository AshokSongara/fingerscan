import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'fingerscan_platform_interface.dart';

/// An implementation of [FingerscanPlatform] that uses method channels.
class MethodChannelFingerscan extends FingerscanPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('fingerscan');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
