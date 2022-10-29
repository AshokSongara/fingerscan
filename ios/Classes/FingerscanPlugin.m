#import "FingerscanPlugin.h"
#if __has_include(<fingerscan/fingerscan-Swift.h>)
#import <fingerscan/fingerscan-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "fingerscan-Swift.h"
#endif

@implementation FingerscanPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFingerscanPlugin registerWithRegistrar:registrar];
}
@end
