package com.jjrefresh.refreshLayout;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.jjrefresh.header.RNTHeaderManager;

import java.util.Arrays;
import java.util.List;

public class RCTRefreshLayoutPackage implements ReactPackage {

  @NonNull
  @Override
  public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
    return Arrays.<NativeModule>asList(
      new RCTSpinnerModule(reactContext)
    );
  }

  @NonNull
  @Override
  public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
    return Arrays.<ViewManager>asList(
      new RCTRefreshLayoutManager(reactContext),
      new RNTHeaderManager(reactContext)
    );
  }
}
