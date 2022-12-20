export interface IRefreshControlProps {
  /**
   * currentInstance
   */
  ref: any;
  /**
   * 闲置提示文字
   */
  idleTips?: string;

  /**
   * 松开即将刷新提示文字
   */
  pull2RefreshTips?: string;

  /**
   * 正在刷新提示文字
   */
  refreshingTips?: string;

  /**
   * 动画时间
   */
  duration?: number;
}
