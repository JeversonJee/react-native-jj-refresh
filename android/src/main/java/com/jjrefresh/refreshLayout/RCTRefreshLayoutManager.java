package com.jjrefresh.refreshLayout;

import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTModernEventEmitter;
import com.jjrefresh.header.RNTHeader;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.simple.SimpleMultiListener;

import java.util.Map;

public class RCTRefreshLayoutManager extends ViewGroupManager<RCTRefreshLayout> {

  protected static final String RCT_MODULE_NAME = "RNTRefreshView";

  private static final String COMMAND_FINISH_REFRESH_NAME = "finishRefresh";
  private static final String COMMAND_FINISH_REFRESH_ID = "0";

  private ReactApplicationContext mReactApplicationContext;
  private RCTRefreshLayout refreshLayout;
  private RCTModernEventEmitter modernEventEmitter;

  public RCTRefreshLayoutManager(ReactApplicationContext reactApplicationContext) {
    mReactApplicationContext = reactApplicationContext;
  }

  @NonNull
  @Override
  public String getName() {
    return RCT_MODULE_NAME;
  }

  @NonNull
  @Override
  protected RCTRefreshLayout createViewInstance(@NonNull ThemedReactContext reactContext) {
    refreshLayout = new RCTRefreshLayout(reactContext);
    refreshLayout.setEnableLoadMore(false);
    modernEventEmitter = reactContext.getJSModule(RCTModernEventEmitter.class);
    return refreshLayout;
  }

  @Nullable
  @Override
  public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
    MapBuilder.Builder builder = MapBuilder.builder();
    for (Events events : Events.values()) {
      builder.put(events.getCname(), MapBuilder.of("registrationName", events.getCname()));
    }
    return builder.build();
  }


  @Nullable
  @Override
  public Map<String, Integer> getCommandsMap() {
    return MapBuilder.of(
      COMMAND_FINISH_REFRESH_NAME,
      Integer.parseInt(COMMAND_FINISH_REFRESH_ID)
    );
  }

  /**
   * 最大显示下拉高度/Header 标准高度
   */
  @ReactProp(name = "maxDragRate", defaultFloat = 2.0f)
  public void setMaxDragRate(RCTRefreshLayout view, float maxDragRate) {
    view.setHeaderMaxDragRate(maxDragRate);
  }

  /**
   * 显示下拉高度/手势真实下拉的高度
   *
   * @param view
   * @param dragRate
   */
  @ReactProp(name = "dragRate", defaultFloat = 0.5f)
  public void setDragRate(RCTRefreshLayout view, float dragRate) {
    view.setDragRate(dragRate);
  }

  /**
   * 是否越界拖动
   *
   * @param view
   * @param overScrollDrag
   */
  @ReactProp(name = "overScrollDrag", defaultBoolean = true)
  public void setOverScrollDrag(RCTRefreshLayout view, boolean overScrollDrag) {
    view.setEnableOverScrollDrag(overScrollDrag);
  }

  /**
   * 设置为纯滚动
   *
   * @param view
   * @param pureScroll
   */
  @ReactProp(name = "pureScroll", defaultBoolean = false)
  public void setPureScroll(RCTRefreshLayout view, boolean pureScroll) {
    view.setEnablePureScrollMode(pureScroll);
  }

  /**
   * 设置主题色
   *
   * @param view
   * @param primaryColor
   */
  @ReactProp(name = "primaryColor", defaultInt = Color.TRANSPARENT)
  public void setPrimaryColor(RCTRefreshLayout view, int primaryColor) {
    view.setPrimaryColors(primaryColor);
  }

  /**
   * 设置刷新头的高度
   *
   * @param view
   * @param headerHeight
   */
  @ReactProp(name = "headerHeight")
  public void setHeaderHeight(RCTRefreshLayout view, float headerHeight) {
    if (headerHeight != 0) {
      view.setHeaderHeight(headerHeight);
    }
  }

  /**
   * 是否启用刷新头
   *
   * @param view
   * @param enableRefresh
   */
  @ReactProp(name = "enableRefresh", defaultBoolean = true)
  public void setEnableRefresh(RCTRefreshLayout view, boolean enableRefresh) {
    view.setEnableRefresh(enableRefresh);
  }

  /**
   * 是否启用自动刷新
   *
   * @param view
   * @param autoRefresh
   */
  @ReactProp(name = "autoRefresh")
  public void setAutoRefresh(RCTRefreshLayout view, ReadableMap autoRefresh) {
    boolean tmpRefreshFlag = false;
    Integer duration = null;
    if (autoRefresh.hasKey("refresh")) {
      tmpRefreshFlag = autoRefresh.getBoolean("refresh");
    }
    if (autoRefresh.hasKey("duration")) {
      duration = autoRefresh.getInt("duration");
    }
    if (tmpRefreshFlag) {
      final boolean b = (duration != null && duration > 0) ? view.autoRefresh(duration) : view.autoRefresh();
    }
  }

  @Override
  public void receiveCommand(@NonNull RCTRefreshLayout root, String commandId, @Nullable ReadableArray args) {
    switch (commandId) {
      case COMMAND_FINISH_REFRESH_ID:
        int deleayed = args.getInt(0);
        boolean success = args.getBoolean(1);
        RefreshLayout refreshLayout = (deleayed >= 0) ? root.finishRefresh(deleayed) : root.finishRefresh();
        break;
      default:
        break;
    }
  }

  @Override
  public void addView(RCTRefreshLayout parent, View child, int index) {
    switch (index) {
      case 0:
        RefreshHeader header;
        if (child instanceof RefreshHeader) {
          header = (RefreshHeader) child;
        } else {
          header = new RNTHeader(mReactApplicationContext);
          ((RNTHeader) header).setView(child);
        }
        parent.setRefreshHeader(header);
        break;
      case 1:
        parent.setRefreshContent(child);
        break;
      case 2:
        break;
      default:
        break;
    }
  }

  @Override
  protected void addEventEmitters(@NonNull ThemedReactContext reactContext, @NonNull RCTRefreshLayout view) {

    view.setOnRefreshListener(new OnRefreshListener() {
      @Override
      public void onRefresh(@NonNull RefreshLayout refreshLayout) {

      }
    });

    view.setOnMultiListener(new SimpleMultiListener() {
      private int getTargetId() {
        return view.getId();
      }

      @Override
      public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
        WritableMap writableMap = Arguments.createMap();
        writableMap.putDouble("percent", percent);
        writableMap.putDouble("offset", offset);
        writableMap.putInt("headerHeight", headerHeight);
        modernEventEmitter.receiveEvent(getTargetId(), getTargetId(), Events.PULLING.getCname(), writableMap);
      }

      @Override
      public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {
//        WritableMap writableMap = Arguments.createMap();
//        writableMap.putInt("headerHeight", headerHeight);
//        modernEventEmitter.receiveEvent(getTargetId(), getTargetId(), Events.RELEASE_2_REFRESH.getCname(), writableMap);
      }

      @Override
      public void onHeaderFinish(RefreshHeader header, boolean success) {

      }

      @Override
      public void onHeaderStartAnimator(RefreshHeader header, int footerHeight, int maxDragHeight) {

      }

      @Override
      public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
          case None:
            modernEventEmitter.receiveEvent(getTargetId(), getTargetId(), Events.Idle.getCname(), null);
            break;
          case PullDownToRefresh:
          case Refreshing:
            modernEventEmitter.receiveEvent(getTargetId(), getTargetId(), Events.REFRESHING.getCname(), null);
            break;
          case ReleaseToRefresh:
            modernEventEmitter.receiveEvent(getTargetId(), getTargetId(), Events.RELEASE_2_REFRESH.getCname(), null);
            break;
        }
      }
    });
  }
}
