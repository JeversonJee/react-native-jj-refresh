package com.jjrefresh.refreshLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.module.annotations.ReactModule;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ReactModule(name= RCTSpinnerModule.MODULE_NAME)
public class RCTSpinnerModule extends ReactContextBaseJavaModule {

  public RCTSpinnerModule(ReactApplicationContext context) {
    super(context);
  }

  protected static final String MODULE_NAME = "RCTSpinnerModule";

  @NonNull
  @Override
  public String getName() {
    return MODULE_NAME;
  }

  @Nullable
  @Override
  public Map<String, Object> getConstants() {
    return Collections.unmodifiableMap(new HashMap<String, Object>() {{
      put("translate", SpinnerStyle.Translate);
      put("fixBehind", SpinnerStyle.FixedBehind);
      put("fixFront", SpinnerStyle.FixedFront);
      put("scale", SpinnerStyle.Scale);
      put("matchLayout", SpinnerStyle.MatchLayout);
    }});
  }
}
