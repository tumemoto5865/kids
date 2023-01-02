package com.kdc.common.enums;

import com.kdc.common.util.CommonConst;

/**
 * ��ʈꗗEnum
 */
public enum WebPageEnum {
	/** ���O�C�� */
	LOGIN("login", "���O�C��"),
	/** ���C�� */
	MAIN("main", "���C��"),
//	/** ���ʐݒ� */
//	COMMONCONFIG("commonconfig", "���ʐݒ�"),
	/** �Ǘ��ݒ� */
	AUTHCONFIG("authconfig", "�Ǘ��ݒ�"),
	/** ���[�U�ݒ� */
	USERCONFIG("userconfig", "���[�U�ݒ�"),
	/** �ꏊ�Ǘ� */
	PLACECONFIG("placeconfig", "�ꏊ�ݒ�"),
	/** ���� */
	RECORD("record", "����");

	final String id;
	final String name;

	private WebPageEnum(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPageTitleLabel() {
		return new StringBuilder().append(CommonConst.KDC_SYSTEM_DISP_NAME).append("�@�@").append(this.name).append("���")
				.toString();
	}

}
