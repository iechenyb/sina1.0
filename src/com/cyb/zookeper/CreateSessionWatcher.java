package com.cyb.zookeper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年7月11日
 */
public class CreateSessionWatcher implements Watcher {
	Log log = LogFactory.getLog(CreateSessionWatcher.class);

	private void doSomething() {

		System.out.println("do something");
	}

	@Override
	public void process(WatchedEvent event) {

		System.out.println("收到事件：" + event);
		if (event.getState() == KeeperState.SyncConnected) {

			if (event.getType() == EventType.None && null == event.getPath()) {
				doSomething();
			}
		}
	}

}
