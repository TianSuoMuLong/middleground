package com.common.enums;

/**
 * @author hanrm
 *
 */
public enum ErrCode {
	error0000("0000", "SUCCESS"), 
	error1001("1001", "用户名或密码错误"), 
	error9999("9999", "系统繁忙，请稍后再试！");
	private String code;
	private String mesage;

	private ErrCode(String code, String mesage) {
		this.code = code;
		this.mesage = mesage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMesage() {
		return mesage;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}

}
