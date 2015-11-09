package com.gcs.aol.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcs.aol.entity.AppPushList;
import com.gcs.aol.service.IAppPushListManager;
import com.gcs.utils.SendAppPush;

public class BusinessReport {
	@Autowired
	IAppPushListManager appPushListManager;

	public void sendAppPush() {
		List<AppPushList> pushList = appPushListManager.queryByProperty(
				"state", "0");
		for (AppPushList appPushList : pushList) {
			// 因无法判断设备是ios还是安卓，所以发送两次
			try {
				SendAppPush.sendAndroidMsg(appPushList.getUserid(), appPushList
						.getTitle());
				SendAppPush.sendIosMsg(appPushList.getUserid(), appPushList
						.getTitle());
				appPushList.setSendtime(new Date());
				appPushList.setState("1");
				appPushListManager.save(appPushList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
