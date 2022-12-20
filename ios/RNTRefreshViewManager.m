//
// Created by Jeverson Jee on 2022/12/13.
//


#import <React/RCTDefines.h>
#import <React/RCTUIManager.h>
#import <React/RCTLog.h>
#import "RNTRefreshHeader.h"

@interface JJRefreshViewManager : RCTViewManager<RNTRefreshHeaderDelegate>

@end

@implementation JJRefreshViewManager {
    RNTRefreshHeader *_header;
}

RCT_EXPORT_MODULE(RNTRefreshView)
//  暴露属性
RCT_EXPORT_VIEW_PROPERTY(onStateIdle, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onStatePulling, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onRefresh, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onStateWillRefresh, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onStateNoMoreData, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPullingPercent, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(refreshing, BOOL)

RCT_EXPORT_METHOD(endRefresh:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        UIView *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[RNTRefreshHeader class]]) {
            RCTLogError(@"Invalid view returned from registry, expecting RCTBridge, got: %@", view);
            return;
        }
        [(RNTRefreshHeader *)view endRefreshing];
    }];
}

RCT_EXPORT_METHOD(beginRefresh:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        UIView *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[RNTRefreshHeader class]]) {
            RCTLogError(@"Invalid view returned from registry, expecting RCTBridge, got: %@", view);
            return;
        }
        [(RNTRefreshHeader *)view beginRefreshing];
    }];
}

- (UIView *)view {
    _header = [RNTRefreshHeader new];
    _header.delegate = self;
    return _header;
}
/**
 *  @brief as we use the uikit
 */
+ (BOOL)requiresMainQueueSetup {
    return true;
}

#pragma mark - refreshHeaderDelegate
- (void)refreshHeaderStateDidChanged:(MJRefreshState)state {
    if (!_header.reactTag) return;
    switch (state) {
        case MJRefreshStateIdle:
            _header.onStateIdle(@{@"target": _header.reactTag});
            break;
        case MJRefreshStatePulling:
            _header.onStatePulling(@{@"target": _header.reactTag});
            break;
        case MJRefreshStateWillRefresh:
            _header.onStateWillRefresh(@{@"target": _header.reactTag});
            break;
        case MJRefreshStateRefreshing:
            _header.onRefresh(@{@"target": _header.reactTag});
            break;
        case MJRefreshStateNoMoreData:
            _header.onStateNoMoreData(@{@"target": _header.reactTag});
            break;
        default:
            break;
    }
}
- (void)pullingPercentDidChanged:(CGFloat)pullingPercent {
    if (!_header.reactTag) return;
    RCTLogInfo([NSString stringWithFormat:@"%@", @{@"target": _header.reactTag, @"percent": [NSNumber numberWithFloat:pullingPercent]}]);
    _header.onPullingPercent(@{@"target": _header.reactTag, @"percent": [NSNumber numberWithFloat:pullingPercent]});
}
@end
