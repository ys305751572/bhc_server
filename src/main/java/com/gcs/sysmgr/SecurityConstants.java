/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, gcsdylan.com
 * Filename:		com.gcs.sysmgr.SecurityConstants.java
 * Class:			SecurityConstants
 * Date:			2012-8-9
 * Author:			<a href="mailto:gcsdylan@gmail.com">gcsdylan</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.gcs.sysmgr;

/**
 * 
 * @author <a href="mailto:gcsdylan@gmail.com">gcsdylan</a> Version 1.1.0
 * @since 2012-8-9 上午10:47:08
 */

public interface SecurityConstants {
	/**
	 * 登录用户
	 */
	public final static String LOGIN_USER = "login_user";

	public final static String USER_OBJ = "user_obj";
	/**
	 * 验证码
	 */
	public final static String CAPTCHA_KEY = "captcha_key";

	/**
	 * 日志参数
	 */
	public final static String LOG_ARGUMENTS = "log_arguments";

	/**
	 * ResponseBody注解返回的mapModel。
	 */
	public final static String MODEL_MAP = "model_map";

	/**
	 * 安全管理模块的短标识符
	 */
	public final static String SECURITY_NS = "Security";

	/**
	 * 安全管理模块的URL地址
	 */
	public final static String SECURITY_URL = "/management/index/sysMgr";

	/**
	 * 安全管理模块的ID
	 */
	public final static long SECURITY_ID = 2L;
}
