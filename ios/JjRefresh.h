
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNJjRefreshSpec.h"

@interface JjRefresh : NSObject <NativeJjRefreshSpec>
#else
#import <React/RCTBridgeModule.h>

@interface JjRefresh : NSObject <RCTBridgeModule>
#endif

@end
