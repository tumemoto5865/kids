package com.kdc.web.login;

public class WebLoginForm {

	private String loginId;
	private String loginPass;
	private boolean loginErrorFlg;
	
	/**
	 * @return loginId
	 */
	public String getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId �Z�b�g���� loginId
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	/**
	 * @return loginPass
	 */
	public String getLoginPass() {
		return loginPass;
	}
	/**
	 * @param loginPass �Z�b�g���� loginPass
	 */
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	/**
	 * @return loginErrorFlg
	 */
	public boolean isLoginErrorFlg() {
		return loginErrorFlg;
	}
	/**
	 * @param loginErrorFlg �Z�b�g���� loginErrorFlg
	 */
	public void setLoginErrorFlg(boolean loginErrorFlg) {
		this.loginErrorFlg = loginErrorFlg;
	}

}
