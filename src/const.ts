import type { ViewProps } from 'react-native';

export interface IJJRefreshProps extends ViewProps {
  ref: any;

  /**
   * 闲置状态回掉
   */
  onStateIdle?: () => void;

  /**
   * 下拉回掉
   */
  onStatePulling?: (e: any) => void;

  /**
   * 正在刷新
   */
  onStateRefreshing?: () => void;

  /**
   * 即将刷新
   */
  onStateWillRefresh?: () => void;

  /**
   * 无更多数据
   */
  onStateNoMoreData?: () => void;

  /**
   * 拉出百分比
   */
  onPullingPercent?: (param: IPullingPercent) => void;

  refreshing: boolean;
}

export interface IPullingPercent {
  target: string;
  percent: number;
}
