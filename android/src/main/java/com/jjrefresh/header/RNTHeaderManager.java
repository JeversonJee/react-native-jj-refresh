package com.jjrefresh.header;

import android.graphics.Color;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class RNTHeaderManager extends ViewGroupManager<RNTHeader> {
  ReactApplicationContext reactApplicationContext;

  @NonNull
  @Override
  public String getName() {
    return "RNTHeaderView";
  }

  public RNTHeaderManager(ReactApplicationContext reactApplicationContext) {
    this.reactApplicationContext = reactApplicationContext;
  }

  @NonNull
  @Override
  protected RNTHeader createViewInstance(@NonNull ThemedReactContext reactContext) {
    return new RNTHeader(reactContext);
  }

  @ReactProp(name = "primaryColor")
  public void setPrimaryColor(RNTHeader view, String primaryColor) {
    if(primaryColor!=null && !"".equals(primaryColor)){
      view.setPrimaryColor(Color.parseColor(primaryColor));
    }
  }
}
