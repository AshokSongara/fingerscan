
import 'fingerscan_platform_interface.dart';

class Fingerscan {
  Future<String?> getPlatformVersion() {
    return FingerscanPlatform.instance.getPlatformVersion();
  }
}
