package com.kdc.common.entity.db;

import java.sql.Timestamp;

public class CommonColumnsEntity {

	private int delflg;
	private String registeruserid;
	private Timestamp registerdate;
	private String updateuserid;
	private Timestamp updatedate;
	
	/**
	 * @return delflg
	 */
	public int getDelflg() {
		return delflg;
	}
	/**
	 * @param delflg �Z�b�g���� delflg
	 */
	public void setDelflg(int delflg) {
		this.delflg = delflg;
	}
	/**
	 * @return registeruserid
	 */
	public String getRegisteruserid() {
		return registeruserid;
	}
	/**
	 * @param registeruserid �Z�b�g���� registeruserid
	 */
	public void setRegisteruserid(String registeruserid) {
		this.registeruserid = registeruserid;
	}
	/**
	 * @return registerdate
	 */
	public Timestamp getRegisterdate() {
		return registerdate;
	}
	/**
	 * @param registerdate �Z�b�g���� registerdate
	 */
	public void setRegisterdate(Timestamp registerdate) {
		this.registerdate = registerdate;
	}
	/**
	 * @return updateuserid
	 */
	public String getUpdateuserid() {
		return updateuserid;
	}
	/**
	 * @param updateuserid �Z�b�g���� updateuserid
	 */
	public void setUpdateuserid(String updateuserid) {
		this.updateuserid = updateuserid;
	}
	/**
	 * @return updatedate
	 */
	public Timestamp getUpdatedate() {
		return updatedate;
	}
	/**
	 * @param updatedate �Z�b�g���� updatedate
	 */
	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
	
}
