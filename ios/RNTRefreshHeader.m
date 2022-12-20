//
// Created by Jeverson Jee on 2022/12/13.
//

#import "RNTRefreshHeader.h"
#import <React/RCTLog.h>
@interface RNTRefreshHeader()
{
    bool _cachedRefreshing;
}
@end


@implementation RNTRefreshHeader

- (instancetype)init {
    if(self = [super init]) {
        [self setRefreshingTarget:self refreshingAction:@selector(refreshStatevalueChanged)];
    }
    return self;
}

- (void)setRefreshing:(BOOL)refreshing {
    _cachedRefreshing = refreshing;
    RCTLog(@"current refreshing state is %d", refreshing);
}

- (void)setState:(MJRefreshState)state {
    MJRefreshCheckState;
    _cachedRefreshing = (state == MJRefreshStateRefreshing);
    if ([(id)self.delegate respondsToSelector:@selector(refreshHeaderStateDidChanged:)]) {
        [self.delegate refreshHeaderStateDidChanged:state];
    }
}

- (void)setPullingPercent:(CGFloat)pullingPercent {
    [super setPullingPercent:pullingPercent];
    if ([(id)self.delegate respondsToSelector:@selector(pullingPercentDidChanged:)]) {
        [self.delegate pullingPercentDidChanged:pullingPercent];
    }
}

- (BOOL)isRefreshing {
    return _cachedRefreshing;
}

- (void)refreshStatevalueChanged {
    
}

@end
