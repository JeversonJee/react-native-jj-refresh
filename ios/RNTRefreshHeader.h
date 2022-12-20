//
// Created by Jeverson Jee on 2022/12/13.
//

#import <Foundation/Foundation.h>
#import <MJRefresh/MJRefreshHeader.h>
#import <React/RCTComponent.h>
#import <React/RCTScrollableProtocol.h>


@protocol RNTRefreshHeaderDelegate

@optional
- (void)refreshHeaderStateDidChanged:(MJRefreshState)state;
- (void)pullingPercentDidChanged:(CGFloat)pullingPercent;
@end

@interface RNTRefreshHeader : MJRefreshHeader<RCTCustomRefreshContolProtocol>

/**
 * @brief: 暴露的事件
 * @description: 用于监听组件的状态
 */
/** 普通闲置状态 */
@property (nonatomic, copy) RCTDirectEventBlock onStateIdle;
/** 松开就可以进行刷新的状态 */
@property (nonatomic, copy) RCTDirectEventBlock onStatePulling;
/** 即将刷新的状态 */
@property (nonatomic, copy) RCTDirectEventBlock onStateWillRefresh;
/** 所有数据加载完毕，没有更多的数据了 */
@property (nonatomic, copy) RCTDirectEventBlock onStateNoMoreData;
/** 下拉百分比 */
@property (nonatomic, copy) RCTDirectEventBlock onPullingPercent;
/** 正在刷新中的状态 */
@property (nonatomic, copy) RCTDirectEventBlock onRefresh;

@property (nonatomic, weak) id<RNTRefreshHeaderDelegate> delegate;
@end
