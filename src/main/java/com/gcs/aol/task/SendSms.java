package com.gcs.aol.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gcs.aol.entity.SmsList;
import com.gcs.aol.service.ISmsListManager;
import com.gcs.utils.SendSmsUtil;

/**
 * 发送短信
 * 
 * @author zb
 * 
 */
public class SendSms {
	@Autowired
	ISmsListManager listManager;

	public void sendSmsMsg() {
		List<SmsList> sendSmsList = listManager.queryByProperty("state", "0");
		for (SmsList sms : sendSmsList) {
			if (StringUtils.isNotBlank(sms.getTelephone())
					&& StringUtils.isNotBlank(sms.getContent())) {
				try {
					SendSmsUtil.sendSms(SendSmsUtil.APIKEY,
							SendSmsUtil.USERNAME, SendSmsUtil.USERPASS, sms
									.getTelephone(), sms.getContent()+SendSmsUtil.ENDSTRING);
					sms.setState("1");
					sms.setSend_time(new Date());
					listManager.save(sms);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
