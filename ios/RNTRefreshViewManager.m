//
// Created by Jeverson Jee on 2022/12/13.
//


#import <Foundation/Foundation.h>
#import <React/RCTDefines.h>
#import <React/RCTUIManager.h>
#import <React/RCTViewManager.h>
#import "RNTRefreshHeader.h"

@interface JJRefreshViewManager : RCTViewManager<RNTRefreshHeaderDelegate>

@end

@implementation JJRefreshViewManager {
    RNTRefreshHeader *_header;
}

RCT_EXPORT_MODULE()
//  暴露属性
RCT_EXPORT_VIEW_PROPERTY(onStateIdle, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onStatePulling, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onStateRefreshing, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onStateWillRefresh, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onStateNoMoreData, RCTBubblingEventBlock)

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
    _header = [RNTRefreshHeader headerWithRefreshingTarget:self refreshingAction:@selector(loadNewData)];
    _header.delegate = self;
    return _header;
}

+ (BOOL)requiresMainQueueSetup {
    return true;
}

- (void)loadNewData {

}

#pragma mark - refreshHeaderDelegate
- (void)refreshHederStateDidChanged:(MJRefreshState)state {
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
            _header.onStateRefreshing(@{@"target": _header.reactTag});
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
    _header.onPullingPercent(@{@"target": _header.reactTag, @"percent": [NSNumber numberWithFloat:pullingPercent]});
}
@end