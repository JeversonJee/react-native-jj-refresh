import React, { forwardRef, FC } from 'react';
import { useRef, useState } from 'react';
import { Animated, Easing, Text, View } from 'react-native';
import JJRefreshControl from 'react-native-jj-refresh';
import type { IRefreshControlProps } from './const';
import Icon from 'react-native-vector-icons/Ionicons';
import { SkypeIndicator } from 'react-native-indicators';
import { useImperativeHandle } from 'react';
const AnimatedIcon = Animated.createAnimatedComponent(Icon);

const JJActivitiesControl: FC<IRefreshControlProps> = forwardRef(
  (props, ref) => {
    const {
      idleTips = '下拉刷新',
      pull2RefreshTips = '释放刷新',
      refreshingTips = '刷新数据中',
      duration = 100,
    } = props;
    let refreshRef = useRef(null);
    const [tips, setTips] = useState(idleTips);
    const [refreshing, setRefreshing] = useState(false);
    const rotateAim = useRef(new Animated.Value(0)).current;
    const rotateIn = () => {
      Animated.timing(rotateAim, {
        toValue: 1,
        useNativeDriver: true,
        duration: duration,
        easing: Easing.linear,
      }).start();
    };
    const rotateOut = () => {
      Animated.timing(rotateAim, {
        toValue: 0,
        useNativeDriver: true,
        duration: duration,
        easing: Easing.linear,
      }).start();
    };
    // @ts-ignore
    const _onPulling = (e) => {
      if (e.nativeEvent.percent === 0) {
        rotateOut;
        return;
      }
      rotateIn();

      setTips(pull2RefreshTips);
    };

    const _onRefreshing = () => {
      setRefreshing(true);
      setTips(refreshingTips);
    };

    const startRefresh = () => {
      // @ts-ignore
      refreshRef.current && refreshRef.current.startRefresh();
    };

    const finishRefresh = () => {
      //  need updated the initial value
      rotateOut();
      setRefreshing(false);
      setTips(idleTips);
      // @ts-ignore
      refreshRef.current && refreshRef.current.finishRefresh();
    };

    useImperativeHandle(ref, () => ({
      startRefresh: startRefresh,
      finishRefresh: finishRefresh,
    }));
    return (
      <JJRefreshControl
        ref={refreshRef}
        refreshing={refreshing}
        onStatePulling={(e) => _onPulling(e)}
        onStateRefreshing={_onRefreshing}
      >
        <View
          style={{
            height: 100,
            flexDirection: 'row',
            alignItems: 'center',
            justifyContent: 'center',
          }}
        >
          {refreshing ? (
            <SkypeIndicator style={{ flex: 0 }} size={24} color={'#2783cf'} />
          ) : (
            <AnimatedIcon
              style={{
                transform: [
                  {
                    rotate: rotateAim.interpolate({
                      inputRange: [0, 1],
                      outputRange: ['0deg', '180deg'],
                    }),
                  },
                ],
              }}
              name="md-arrow-up"
              color="#ddd"
              size={24}
            />
          )}
          <Text>{tips}</Text>
        </View>
      </JJRefreshControl>
    );
  }
);

export default JJActivitiesControl;
