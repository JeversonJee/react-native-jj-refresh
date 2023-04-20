import type { ReactNode } from 'react';
import type { ViewProps } from 'react-native';

export interface IAndroidHeaderProps extends ViewProps {
  /**
   * 主题色
   */
  primaryColor: string;
}

export interface IAndroidRefreshProps extends ViewProps {
  ref?: any;

  headerComponent: ReactNode;

  renderHeader?: boolean | ReactNode;

  /**
   * 正在刷新中
   * @returns
   */
  onRefresh: () => void;

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

  headerHeight?: number;

  overScrollBounce?: boolean;

  overScrollDrag?: boolean;

  pureScroll?: boolean;

  primaryColor?: string;
}
