package com.jjrefresh.refreshLayout;

import android.content.Context;
import android.view.MotionEvent;

import com.facebook.react.uimanager.events.NativeGestureUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

public class RCTRefreshLayout extends SmartRefreshLayout {
  private static final float DEFAULT_CIRCLE_TARGET = 64;
  private boolean mRefreshing = false;
  private float mProgressViewOffset = 0;
  private int mTouchSlop;
  private float mPrevTouchX;
  private boolean mIntercepted;

  public RCTRefreshLayout(Context context) {
    super(context);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);

  }

  @Override
  public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    if (getParent() != null) {
      getParent().requestDisallowInterceptTouchEvent(disallowIntercept);
    }
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    if (shouldInterceptTouchEvent(ev) && super.onInterceptTouchEvent(ev)) {
      NativeGestureUtil.notifyNativeGestureStarted(this, ev);
      return true;
    }
    return false;
  }

  private boolean shouldInterceptTouchEvent(MotionEvent ev) {
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mPrevTouchX = ev.getX();
        mIntercepted = false;
        break;

      case MotionEvent.ACTION_MOVE:
        final float eventX = ev.getX();
        final float xDiff = Math.abs(eventX - mPrevTouchX);

        if (mIntercepted || xDiff > mTouchSlop) {
          mIntercepted = true;
          return false;
        }
    }
    return true;
  }
}
