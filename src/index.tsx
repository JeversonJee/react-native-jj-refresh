// @ts-ignore
import {
  requireNativeComponent,
  findNodeHandle,
  UIManager,
  Platform,
} from 'react-native';
import { FC, forwardRef, useImperativeHandle, useRef } from 'react';
import * as React from 'react';
import type { IJJRefreshProps, IPullingPercent } from './const';

const UnimplementedView = require('react-native/Libraries/Components/UnimplementedViews/UnimplementedView');

const JJRefreshCtrl =
  Platform.OS === 'ios'
    ? requireNativeComponent('RNTRefreshView')
    : UnimplementedView;

const components: FC<IJJRefreshProps> = forwardRef((props, ref) => {
  let refreshRef = useRef(null);

  const _onStateIdle = () => {
    const { onStateIdle } = props;
    onStateIdle && onStateIdle();
  };

  const _onStatePulling = (e) => {
    const { onStatePulling } = props;
    onStatePulling && onStatePulling(e);
  };

  const _onStateWillRefresh = () => {
    const { onStateWillRefresh } = props;
    onStateWillRefresh && onStateWillRefresh();
  };

  const _onStateNoMoreData = () => {
    const { onStateNoMoreData } = props;
    onStateNoMoreData && onStateNoMoreData();
  };

  const _onPullingPercent = (p) => {
    const { onPullingPercent } = props;
    onPullingPercent && onPullingPercent(p);
  };

  const _onStateRefreshing = () => {
    const { onStateRefreshing } = props;
    onStateRefreshing && onStateRefreshing();
  };

  const endRefresh = () => {
    dispatchCommand('enRefresh');
  };

  const beginRefresh = () => {
    dispatchCommand('beginRefresh');
  };

  const dispatchCommand = (commandName: string, params?: []) => {
    UIManager.dispatchViewManagerCommand(
      findNode(),
      getCommandId('RNTRefreshView', commandName),
      params
    );
  };

  const findNode = () => {
    return findNodeHandle(refreshRef.current);
  };

  const getCommandId = (moduleName: string, nativeMethodName: string) => {
    let commandId =
      UIManager.getViewManagerConfig(moduleName).Commands.nativeMethodName;
    return commandId ?? 0;
  };

  useImperativeHandle(ref, () => ({
    startRefresh: beginRefresh,
    finishRefresh: endRefresh,
  }));
  return (
    <JJRefreshCtrl
      {...props}
      ref={refreshRef}
      onStatePulling={(event: any) => _onStatePulling(event)}
      onStateIdle={_onStateIdle}
      onRefresh={_onStateRefreshing}
      refreshing={props.refreshing}
      onStateWillRefresh={_onStateWillRefresh}
      onStateNoMoreData={_onStateNoMoreData}
      onPullingPercent={(event: IPullingPercent) => _onPullingPercent(event)}
    />
  );
});
export default components;
