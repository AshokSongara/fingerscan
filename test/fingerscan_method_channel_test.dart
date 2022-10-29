import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:fingerscan/fingerscan_method_channel.dart';

void main() {
  MethodChannelFingerscan platform = MethodChannelFingerscan();
  const MethodChannel channel = MethodChannel('fingerscan');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
