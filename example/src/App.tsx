import * as React from 'react';
import {
  StyleSheet,
  ScrollView,
  Text,
  View,
  SafeAreaView,
  Button,
} from 'react-native';
import { useRef } from 'react';
import ActivitiesControl from './components/activitiesControl';

export default function App() {
  let activitiesControl = useRef();

  const _finishRefresh = () => {
    // @ts-ignore
    activitiesControl.current && activitiesControl.current.finishRefresh();
  };
  return (
    <SafeAreaView>
      <ScrollView
        refreshControl={
          // <RefreshControl refreshing={refreshing} onRefresh={_onRefreshing} />
          <ActivitiesControl ref={activitiesControl} />
        }
      >
        <View style={styles.container}>
          <View style={styles.box}>
            <Text>下拉刷新</Text>
            <Button title="关闭刷新" onPress={_finishRefresh} />
          </View>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: '100%',
    height: 400,
    backgroundColor: '#000',
    marginVertical: 20,
  },
});
