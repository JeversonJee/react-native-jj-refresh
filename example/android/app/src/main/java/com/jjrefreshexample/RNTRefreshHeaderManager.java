package com.jjrefreshexample;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNTRefreshHeaderManager extends ViewGroupManager<RNTRefreshHeader> {
  @NonNull
  @Override
  public String getName() {
    return "RNTRefreshHeader";
  }

  @NonNull
  @Override
  protected RNTRefreshHeader createViewInstance(@NonNull ThemedReactContext reactContext) {
    return new RNTRefreshHeader(reactContext);
  }


}

