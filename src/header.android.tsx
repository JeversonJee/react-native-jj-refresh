import type { FC } from 'react';
import { requireNativeComponent } from 'react-native';
import type { IAndroidHeaderProps } from './const.android';

const HeaderView = requireNativeComponent('RNTHeaderView');

const components: FC<IAndroidHeaderProps> = (props) => {
  return <HeaderView {...props} />;
};
const AndHeader = components;
export default AndHeader;
