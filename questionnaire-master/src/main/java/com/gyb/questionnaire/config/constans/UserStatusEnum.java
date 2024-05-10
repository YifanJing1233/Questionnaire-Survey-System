package com.gyb.questionnaire.config.constans;

/**
 * 用户状态枚举
 *
 */
public enum UserStatusEnum {
	DELETED(0),NORMAL(1),LOCKED(2);

	UserStatusEnum(int status) {
		this.status = status;
	}

	private final int status;

	public int getStatus(){
		return this.status;
	}
}
