package com.jjrefresh.refreshLayout;

public enum Events {
  Idle("onStateIdle"), //  空闲状态
  PULLING("onStatePulling"), //  下拉状态
  REFRESHING("onRefresh"), //  刷新事件回掉
  RELEASE_2_REFRESH("onStateWillRefresh"); //  释放刷新事件

  private final String cname;

  Events(String name) {
    cname = name;
  }

  public String getCname() {
    return cname;
  }
}
