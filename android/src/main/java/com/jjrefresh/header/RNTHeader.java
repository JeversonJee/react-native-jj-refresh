package com.jjrefresh.header;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.facebook.react.views.view.ReactViewGroup;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

public class RNTHeader extends ReactViewGroup implements RefreshHeader {
  private RefreshKernel cRefreshKernel;
  private int cBackgroundColor;
  private Integer cPrimaryColor;
  private SpinnerStyle cSpinnerStyle = SpinnerStyle.Translate;

  public RNTHeader(Context context) {
    super(context);
    initView(context);
  }

  private void initView(Context context) {
    setMinimumHeight(60);
  }

  public void setView(View view) {
    addView(view);
  }

  @NonNull
  @Override
  public View getView() {
    return this;
  }

  @NonNull
  @Override
  public SpinnerStyle getSpinnerStyle() {
    return this.cSpinnerStyle;
  }

  @SuppressLint("RestrictedApi")
  @Override
  public void setPrimaryColors(int... colors) {
    if (colors.length > 0) {
      if (!(getBackground() instanceof BitmapDrawable) && cPrimaryColor == null) {
        setPrimaryColors(colors[0]);
        cPrimaryColor = null;
      }
    }
  }

  @SuppressLint("RestrictedApi")
  @Override
  public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
    cRefreshKernel = kernel;
    cRefreshKernel.requestDrawBackgroundFor(this, cBackgroundColor);
  }

  public RNTHeader setPrimaryColor(@ColorInt int primaryColor) {
    cBackgroundColor = cPrimaryColor = primaryColor;
    if (cRefreshKernel != null) {
      cRefreshKernel.requestDrawBackgroundFor(this, cPrimaryColor);
    }
    return this;
  }

  public RNTHeader setSpinnerStyle(SpinnerStyle style) {
    this.cSpinnerStyle = style;
    return this;
  }

  @SuppressLint("RestrictedApi")
  @Override
  public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

  }

  @SuppressLint("RestrictedApi")
  @Override
  public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

  }

  @SuppressLint("RestrictedApi")
  @Override
  public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

  }

  @SuppressLint("RestrictedApi")
  @Override
  public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
    return 500;
  }

  @SuppressLint("RestrictedApi")
  @Override
  public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

  }

  @Override
  public boolean isSupportHorizontalDrag() {
    return false;
  }

  @SuppressLint("RestrictedApi")
  @Override
  public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

  }
}
