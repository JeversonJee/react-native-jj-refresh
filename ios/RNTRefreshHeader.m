//
// Created by Jeverson Jee on 2022/12/13.
//

#import "RNTRefreshHeader.h"


@implementation RNTRefreshHeader

- (void)setState:(MJRefreshState)state {
    if ([self.delegate respondsToSelector:@selector(refreshHederStateDidChanged:)]) {
        [self.delegate refreshHederStateDidChanged:state];
    }
}

- (void)setPullingPercent:(CGFloat)pullingPercent {
    [super setPullingPercent:pullingPercent];
    if ([self.delegate respondsToSelector:@selector(pullingPercentDidChanged:)]) {
        [self.delegate pullingPercentDidChanged:pullingPercent];
    }
}

@end
