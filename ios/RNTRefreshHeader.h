//
// Created by Jeverson Jee on 2022/12/13.
//

#import <Foundation/Foundation.h>
#import <MJRefresh/MJRefreshHeader.h>
#import <React/RCTComponent.h>


@protocol RNTRefreshHeaderDelegate

@optional
- (void)refreshHederStateDidChanged:(MJRefreshState)state;
- (void)pullingPercentDidChanged:(CGFloat)pullingPercent;
@end

@interface RNTRefreshHeader : MJRefreshHeader

/**
 * @brief: 暴露的事件
 * @description: 用于监听组件的状态
 */
/** 普通闲置状态 */
@property (nonatomic, copy) RCTBubblingEventBlock onStateIdle;
/** 松开就可以进行刷新的状态 */
@property (nonatomic, copy) RCTBubblingEventBlock onStatePulling;
/** 正在刷新中的状态 */
@property (nonatomic, copy) RCTBubblingEventBlock onStateRefreshing;
/** 即将刷新的状态 */
@property (nonatomic, copy) RCTBubblingEventBlock onStateWillRefresh;
/** 所有数据加载完毕，没有更多的数据了 */
@property (nonatomic, copy) RCTBubblingEventBlock onStateNoMoreData;

@property (nonatomic, copy) RCTBubblingEventBlock onPullingPercent;

@property (nonatomic, weak) id<RNTRefreshHeaderDelegate> delegate;
@end
