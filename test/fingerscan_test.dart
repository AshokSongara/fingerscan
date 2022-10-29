import 'package:flutter_test/flutter_test.dart';
import 'package:fingerscan/fingerscan.dart';
import 'package:fingerscan/fingerscan_platform_interface.dart';
import 'package:fingerscan/fingerscan_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFingerscanPlatform 
    with MockPlatformInterfaceMixin
    implements FingerscanPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FingerscanPlatform initialPlatform = FingerscanPlatform.instance;

  test('$MethodChannelFingerscan is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFingerscan>());
  });

  test('getPlatformVersion', () async {
    Fingerscan fingerscanPlugin = Fingerscan();
    MockFingerscanPlatform fakePlatform = MockFingerscanPlatform();
    FingerscanPlatform.instance = fakePlatform;
  
    expect(await fingerscanPlugin.getPlatformVersion(), '42');
  });
}
