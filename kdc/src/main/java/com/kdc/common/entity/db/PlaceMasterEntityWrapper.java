package com.kdc.common.entity.db;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;


public class PlaceMasterEntityWrapper extends PlaceMasterEntity {

	private BigDecimal latitude;
	private BigDecimal longitude;
	
	/**
	 * @return latitude
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude �Z�b�g���� latitude
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	/**
	 * @param latitude �Z�b�g���� latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = new BigDecimal(latitude);
	}	
	/**
	 * @return longitude
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude �Z�b�g���� longitude
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	/**
	 * @param longitude �Z�b�g���� longitude
	 */
	public void setLongitude(String longitude) {
		this.longitude = new BigDecimal(longitude);
	}
	/**
	 * @return longitudeandlatitude
	 */
	@Deprecated
	public String getLongitudeandlatitude() {
		return String.format("(%s,%s)", this.latitude, this.longitude);
	}
	/**
	 * @param latitude �Z�b�g���� latitude
	 * @param longitude �Z�b�g���� longitude
	 */
	public void setLongitudeandlatitude(BigDecimal latitude, BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	/**
	 * @param longitudeandlatitude �Z�b�g���� longitudeandlatitude
	 */
	@Deprecated
	public void setLongitudeandlatitude(String longitudeandlatitude) {
		super.setLongitudeandlatitude(longitudeandlatitude);
		longitudeandlatitude = StringUtils.remove(longitudeandlatitude, '(');
		longitudeandlatitude = StringUtils.remove(longitudeandlatitude, ')');
		String temp[] = longitudeandlatitude.split(",");
		this.latitude = new BigDecimal(temp[0]);
		this.longitude = new BigDecimal(temp[1]);
	}
	
}
