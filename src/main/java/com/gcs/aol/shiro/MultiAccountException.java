package com.gcs.aol.shiro;

import org.apache.shiro.authc.AccountException;

/**
 * 多个帐号异常
 */
public class MultiAccountException extends AccountException {
	private static final long serialVersionUID = 345243654232543623L;

	public MultiAccountException(String message) {
		super(message);
	}

	public MultiAccountException(Throwable cause) {
		super(cause);
	}

	public MultiAccountException(String message, Throwable cause) {
		super(message, cause);
	}

}
