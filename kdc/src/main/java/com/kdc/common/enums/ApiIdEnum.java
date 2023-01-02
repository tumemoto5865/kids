package com.kdc.common.enums;

import com.kdc.common.util.CommonConst;

/**
 * API����ID Enum
 */
public enum ApiIdEnum {
	/** ���O�C�� */
	LOGIN(CommonConst.API_ID_LOGIN),
	/** ���[�U�o�^ */
	REGISTER_USER(CommonConst.API_ID_REGISTER_USER),
	/** �ʒu��񑗐M */
	SEND_LOCATION(CommonConst.API_ID_SEND_LOCATION),
	/** ���[�U�ʒu���擾 */
	GET_LOCATIONS(CommonConst.API_ID_GET_LOCATIONS),
	/** �ꏊ���擾 */
	GET_PLACES(CommonConst.API_ID_GET_PLACES),
	/** �O���[�v���擾 */
	GET_GROUPS(CommonConst.API_ID_GET_GROUPS),
	/** ���[�U�������擾 */
	GET_RECORDS(CommonConst.API_ID_GET_RECORDS),
	/** �ݒ�擾 */
	GET_CONFIG(CommonConst.API_ID_GET_CONFIG),
	/** SOS���M */
	SEND_SOS_ALERT(CommonConst.API_ID_SEND_SOS_ALERT),
	/** �ꏊ�o�^ */
	REGISTER_PLACE(CommonConst.API_ID_REGISTER_PLACE),
	/** �O���[�v�o�^ */
	REGISTER_GROUP(CommonConst.API_ID_REGISTER_GROUP),
	/** �ʒm���o�^ */
	REGISTER_NOTIFICATION(CommonConst.API_ID_REGISTER_NOTIFICATION),
	/** �ʒm���擾 */
	GET_NOTIFICATIONS(CommonConst.API_ID_GET_NOTIFICATIONS),
	
	/** ���[�U���ύX */
	SETTING_USER(CommonConst.API_ID_SETTING_USER),
	/** �A�C�R���摜�_�E�����[�h */
	DL_USERICON(CommonConst.API_ID_DL_USERICON),
	/** ���҃R�[�h���s */
	GET_INVITATION(CommonConst.API_ID_GET_INVITATION),
	/** ���҃R�[�h�F�� */
	CHECK_INVITATION(CommonConst.API_ID_CHECK_INVITATION),
	/** APK�_�E�����[�h */
	DL_APK(CommonConst.API_ID_DL_APK),
	/** �i�Ǘ��җp�@�\�j�S���[�U���擾 */
	ADMIN_GET_ALLUSER(CommonConst.API_ID_ADMIN_GET_ALLUSER),
	/** �i�Ǘ��җp�@�\�j�����[�U���ύX */
	ADMIN_SETTING_USER(CommonConst.API_ID_ADMIN_SETTING_USER),
	/** �i�Ǘ��җp�@�\�j���[�U�폜 */
	ADMIN_DELUSER(CommonConst.API_ID_ADMIN_DELUSER),
	/** �T�[�o�����iAPI�ȊO��Push�ʒm��Ԃ�����ԍ��j */
	SERVER_PROCESS(CommonConst.API_ID_SERVER_PROCESS),
	/** ���O�A�E�g */
	LOGOUT(CommonConst.API_ID_LOGOUT),
	/** ���[�U�`�F�b�N */
	CHECK_USER(CommonConst.API_ID_CHECK_USER),;

	final int code;

	private ApiIdEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ApiIdEnum valueOf(int rm) {
		switch (rm) {

		case CommonConst.API_ID_LOGIN:
			return LOGIN;

		case CommonConst.API_ID_REGISTER_USER:
			return REGISTER_USER;

		case CommonConst.API_ID_SEND_LOCATION:
			return SEND_LOCATION;

		case CommonConst.API_ID_GET_LOCATIONS:
			return GET_LOCATIONS;

		case CommonConst.API_ID_GET_PLACES:
			return GET_PLACES;

		case CommonConst.API_ID_GET_RECORDS:
			return GET_RECORDS;

		case CommonConst.API_ID_GET_CONFIG:
			return GET_CONFIG;

		case CommonConst.API_ID_SEND_SOS_ALERT:
			return SEND_SOS_ALERT;

		case CommonConst.API_ID_REGISTER_PLACE:
			return REGISTER_PLACE;

		case CommonConst.API_ID_SETTING_USER:
			return SETTING_USER;

		case CommonConst.API_ID_DL_USERICON:
			return DL_USERICON;

		case CommonConst.API_ID_GET_INVITATION:
			return GET_INVITATION;

		case CommonConst.API_ID_CHECK_INVITATION:
			return CHECK_INVITATION;

		case CommonConst.API_ID_DL_APK:
			return DL_APK;

		case CommonConst.API_ID_ADMIN_GET_ALLUSER:
			return ADMIN_GET_ALLUSER;

		case CommonConst.API_ID_ADMIN_SETTING_USER:
			return ADMIN_SETTING_USER;

		case CommonConst.API_ID_ADMIN_DELUSER:
			return ADMIN_DELUSER;

		case CommonConst.API_ID_SERVER_PROCESS:
		return SERVER_PROCESS;
			
		case CommonConst.API_ID_LOGOUT:
		return LOGOUT;
		
		case CommonConst.API_ID_CHECK_USER:
		return CHECK_USER;
		
		case CommonConst.API_ID_REGISTER_NOTIFICATION:
			return REGISTER_NOTIFICATION;
		
		case CommonConst.API_ID_GET_NOTIFICATIONS:
			return GET_NOTIFICATIONS;
		

		default:
			throw new IllegalArgumentException("argument out of range");
		}
	}

}
