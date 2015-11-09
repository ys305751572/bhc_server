package com.gcs.aol.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/** 
 * 自定义 密码验证类 
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	@Override  
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		//登录时输入的密码
		Object tokenCredentials = token.getPassword();
		//系统存储的密码
		Object accountCredentials = getCredentials(info);
		
		//将登录时输入的密码与系统密码校验，内容一致就返回true,不一致就返回false  
		return equals(tokenCredentials, accountCredentials);
	}
	
}
