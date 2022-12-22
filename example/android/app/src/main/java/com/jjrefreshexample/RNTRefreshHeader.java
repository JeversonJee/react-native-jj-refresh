package com.jjrefreshexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import androidx.annotation.NonNull;

import com.facebook.react.views.view.ReactViewGroup;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

public class RNTRefreshHeader extends ReactViewGroup implements RefreshHeader {

  private SpinnerStyle cSpinnerStyle = SpinnerStyle.Translate;
  private Integer cPrimaryColor;
  private int cHeaderBgColor;
  private RefreshKernel cRefreshKernel;

  public RNTRefreshHeader(Context context) {
    super(context);
    ini
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
    cRefreshKernel.requestDrawBackgroundFor(this.cRefreshKernel.getRefreshLayout().getRefreshHeader(), cHeaderBgColor);
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
