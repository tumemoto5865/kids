package com.kdc.common.entity.api.base;

public class UserInfoEntity extends UserInfoCoreEntity {

	private String userName;
	private String iconId;
	private String lineColor;
	private String markerColor;
	
	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName �Z�b�g���� userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return iconId
	 */
	public String getIconId() {
		return iconId;
	}
	/**
	 * @param iconId �Z�b�g���� iconId
	 */
	public void setIconId(String iconId) {
		this.iconId = iconId;
	}
	/**
	 * @return lineColor
	 */
	public String getLineColor() {
		return lineColor;
	}
	/**
	 * @param lineColor �Z�b�g���� lineColor
	 */
	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}
	/**
	 * @return markerColor
	 */
	public String getMarkerColor() {
		return markerColor;
	}
	/**
	 * @param markerColor �Z�b�g���� markerColor
	 */
	public void setMarkerColor(String markerColor) {
		this.markerColor = markerColor;
	}

}
