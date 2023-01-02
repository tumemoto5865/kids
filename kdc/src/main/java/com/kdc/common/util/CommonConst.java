package com.kdc.common.util;

/**
 * ���ʒ萔�N���X.
 */
public class CommonConst {

	/** �L�b�Y�R���g���[���V�X�e���^�C�g�� */
	public static final String KDC_SYSTEM_TITLE = "kidcontrol";
	/** �L�b�Y�R���g���[���V�X�e���\���� */
	public static final String KDC_SYSTEM_DISP_NAME = "�L�b�Y�R���g���[��";

	/** �A�N�Z�X�|�C���gURL���ʕ�:API */
	public static final String API_BASE_URL = "/api";
	/** �A�N�Z�X�|�C���gURL���ʕ�:�T�[�o */
	public static final String WEB_BASE_URL = "/web";

	/** �V�X�e�����W���[���t�@�C���i�[�f�B���N�g���p�X */
	// TODO:�{�Ԋ��ɍ��킹�ĕύX
	public static final String SYSTEM_MODULE_PATH = "C:/Shared01/kidcontrol/module/";
	//public static final String SYSTEM_MODULE_PATH = "/Shared01/kidcontrol/module/";

	/** �V�X�e���ݒ�t�@�C���� */
	public static final String SYSTEM_INI_FILE_NAME = "kidcontrol.ini";

	/** �A�v���C���X�g�[���p�b�P�[�W�iAPK�j�t�@�C���� */
	public static final String APK_FILE_NAME = "kidcontrol.apk";

	/** ���[�U�f�t�H���g�A�C�R���摜�i�[�f�B���N�g�� */
	public static final String USER_ICON_DIR = "icon/user/";
	/** �ꏊ�A�C�R���摜�i�[�f�B���N�g�� */
	public static final String PLACE_ICON_DIR = "icon/place/";
	/** �}�[�J�[�A�C�R���摜�i�[�f�B���N�g�� */
	public static final String MARKER_ICON_DIR = "icon/marker/";
	/** �A�C�R���摜�\���p�iBase64�j */
	public static final String ICON_IMG_HEADER = "data:image/png;base64,";
	/** �A�C�R���摜�̕� */
	public static final int ICON_WIDTH = 40;
	/** �A�C�R���摜�̍��� */
	public static final int ICON_HEIGHT = 40;

	/** ���ʃR�[�h:���폈�� */
	public static final Integer RESULT_CD_SUCCESS = 0;
	/** ���ʃR�[�h:�������s */
	public static final Integer RESULT_CD_FAILED = 1;

	/** ID�w�b�_����:�ꏊID */
	public static final String ID_HEADER_PLACE = "P";
	/** ID�w�b�_����:�ݒ�ID */
	public static final String ID_HEADER_CONFIG = "C";

	/** �t���O����:ON */
	public static final Integer FLG_ON = 1;
	/** �t���O����:OFF */
	public static final Integer FLG_OFF = 0;
	/** �O���[�vID(�����l�F�P�j */
	public static final String GROUP_ID_1 = "1";

	/*-------- �񋓒l --------*/
	/** API����ID:���O�C�� */
	public static final int API_ID_LOGIN = 1;
	/** API����ID:���[�U�o�^ */
	public static final int API_ID_REGISTER_USER = 2;
	/** API����ID:�ʒu��񑗐M */
	public static final int API_ID_SEND_LOCATION = 3;
	/** API����ID:���[�U�ʒu���擾 */
	public static final int API_ID_GET_LOCATIONS = 4;
	/** API����ID:�ꏊ���擾 */
	public static final int API_ID_GET_PLACES = 5;
	/** API����ID:���[�U�������擾 */
	public static final int API_ID_GET_RECORDS = 6;
	/** API����ID:�ݒ�擾 */
	public static final int API_ID_GET_CONFIG = 7;
	/** API����ID:SOS���M */
	public static final int API_ID_SEND_SOS_ALERT = 8;
	/** API����ID:�ꏊ�o�^ */
	public static final int API_ID_REGISTER_PLACE = 9;
	/** API����ID:���[�U���ύX */
	public static final int API_ID_SETTING_USER = 10;
	/** API����ID:�A�C�R���摜�_�E�����[�h */
	public static final int API_ID_DL_USERICON = 11;
	/** API����ID:���҃R�[�h���s */
	public static final int API_ID_GET_INVITATION = 12;
	/** API����ID:���҃R�[�h�F�� */
	public static final int API_ID_CHECK_INVITATION = 13;
	/** API����ID:APK�_�E�����[�h */
	public static final int API_ID_DL_APK = 14;
	/** API����ID:���O�A�E�g */
	public static final int API_ID_LOGOUT = 15;
	/** API����ID:�O���[�v���擾 */
	public static final int API_ID_GET_GROUPS = 16;
	/** API����ID:�O���[�v�o�^ */
	public static final int API_ID_REGISTER_GROUP = 17;
	/** API����ID:�ʒm���o�^�@*/
	public static final int API_ID_REGISTER_NOTIFICATION = 18;
	/** API����ID:�ʒm���擾�@*/
	public static final int API_ID_GET_NOTIFICATIONS = 19;	
	/** API����ID:�`�F�b�N���[�U */
	public static final int API_ID_CHECK_USER = 20;
	/** API����ID:�i�Ǘ��җp�@�\�j�S���[�U���擾 */
	public static final int API_ID_ADMIN_GET_ALLUSER = 101;
	/** API����ID:�i�Ǘ��җp�@�\�j�����[�U���ύX */
	public static final int API_ID_ADMIN_SETTING_USER = 102;
	/** API����ID:�i�Ǘ��җp�@�\�j���[�U�폜 */
	public static final int API_ID_ADMIN_DELUSER = 103;
	/** API����ID:�T�[�o�����iAPI�ȊO��Push�ʒm��Ԃ�����ԍ��j */
	public static final int API_ID_SERVER_PROCESS = 999;

	/** �Ǘ����x��:�Q�X�g */
	public static final int AUTH_LEVEL_GUEST = 1;
	/** �Ǘ����x��:�q�� */
	public static final int AUTH_LEVEL_CHILD = 2;
	/** �Ǘ����x��:��l */
	public static final int AUTH_LEVEL_ADULT = 3;
	/** �Ǘ����x��:�Ǘ��� */
	public static final int AUTH_LEVEL_ADMIN = 4;

	/** Push�ʒm���:���[�U�A�C�R�� */
	public static final int PUSH_NOFITICATION_USERICON = 1;
	/** Push�ʒm���:SOS */
	public static final int PUSH_NOFITICATION_SOS = 2;
	/** Push�ʒm���:�o�b�e���[ */
	public static final int PUSH_NOFITICATION_BATTERY = 3;
	/** Push�ʒm���:�ڑ��ؒf */
	public static final int PUSH_NOFITICATION_DISCONNECT = 4;
	/** Push�ʒm���:�ڑ��� */
	public static final int PUSH_NOFITICATION_RECONNECT = 5;
	/** Push�ʒm���:�ꏊ�i���i�ʏ�ꏊ�j */
	public static final int PUSH_NOFITICATION_PLACE_NORMAL_IN = 6;
	/** Push�ʒm���:�ꏊ�i���i�댯�ȏꏊ�j */
	public static final int PUSH_NOFITICATION_PLACE_DANGER_IN = 7;
	/** Push�ʒm���:�ꏊ�ޏo�i�ʏ�ꏊ�j */
	public static final int PUSH_NOFITICATION_PLACE_NORMAL_OUT = 8;
	/** Push�ʒm���:�ꏊ�ޏo�i�댯�ȏꏊ�j */
	public static final int PUSH_NOFITICATION_PLACE_DANGER_OUT = 9;
	/** Push�ʒm���:�ݒ�ύX�i�T�[�o�����j */
	public static final int PUSH_NOFITICATION_CHANGE_CONFIG = 10;

	/** �ʒm�^�C�v:���̂� */
	public static final int NOTIFICATION_TYPE_SOUND_ONLY = 0;
	/** �ʒm�^�C�v:�o�C�u�̂� */
	public static final int NOTIFICATION_TYPE_VIBRATION_ONLY = 1;
	/** �ʒm�^�C�v:���{�o�C�u */
	public static final int NOTIFICATION_TYPE_SOUND_AND_VIBRATION = 2;

	/** �d�g��:Wi-Fi */
	public static final int RECEPTION_STATUS_WIFI = 1;
	/** �d�g��:LTE */
	public static final int RECEPTION_STATUS_LTE = 2;
	/** �d�g��:4G */
	public static final int RECEPTION_STATUS_4G = 3;
	/** �d�g��:3G */
	public static final int RECEPTION_STATUS_3G = 4;
	/** �d�g��:5G */
	public static final int RECEPTION_STATUS_5G = 5;
	/** �d�g��:WiMAX */
	public static final int RECEPTION_STATUS_WIMAX = 6;

	/** �ڑ���:�ؒf */
	public static final int CONNECTION_STATUS_DISCONNECT = 0;
	/** �ڑ���:�ڑ� */
	public static final int CONNECTION_STATUS_CONNECT = 1;

	/** �ꏊ�o�^���[�h:�o�^ */
	public static final int REGISTER_PLACE_MODE_INSERT = 1;
	/** �ꏊ�o�^���[�h:�ύX */
	public static final int REGISTER_PLACE_MODE_UPDATE = 2;
	/** �ꏊ�o�^���[�h:�폜 */
	public static final int REGISTER_PLACE_MODE_DELETE = 3;

	/** �ꏊ���:�ʏ�ꏊ */
	public static final int PLACE_TYPE_NORMAL = 0;
	/** �ꏊ���:�댯�ȏꏊ */
	public static final int PLACE_TYPE_DANGER = 1;

	/** �F�؃��[�h:���O�C�� */
	public static final int AUTHENTICATION_MODE_LOGIN = 1;
	/** �F�؃��[�h:���[�U�o�^ */
	public static final int AUTHENTICATION_MODE_REGISTER_USER = 2;
	/** �F�؃��[�h:�V�K�O���[�v�쐬 */
	public static final int AUTHENTICATION_MODE_NEW_GROUP = 3;
	/** �F�؁@���҃R�[�h�F1111111�i���[�U�[�����݂��Ȃ��ꍇ�ɃX���[������j **/
	public static final String INVITATION_CD = "1111111";

	/** �O���[�v�o�^���[�h:�o�^ */
	public static final int REGISTER_GROUP_MODE_INSERT = 1;
	/** �O���[�v�o�^���[�h:�ύX */
	public static final int REGISTER_GROUP_MODE_UPDATE = 2;
	/** �O���[�v�o�^���[�h:�폜 */
	public static final int REGISTER_GROUP_MODE_DELETE = 3;
	
	/** �ʒm���o�^���[�h:�o�^ */
	public static final int REGISTER_NOTIFICATION_MODE_INSERT = 1;
	/** �O���[�v�o�^���[�h:�ύX */
	public static final int REGISTER_NOTIFICATION_MODE_UPDATE = 2;
	/** �O���[�v�o�^���[�h:�폜 */
	public static final int REGISTER_NOTIFICATION_MODE_DELETE = 3;

	/*-------- �ݒ�敪 --------*/
	/** �ݒ�敪ID:�����p�X���[�h */
	public static final int CONFIG_ID_INIT_PASSWORD = 0;
	/** �ݒ�敪ID:SOS�J�E���g�_�E���b�� */
	public static final int CONFIG_ID_SOS_COUNTDOWN = 1;
	/** �ݒ�敪ID:�[���ؒf���ԁi���j */
	public static final int CONFIG_ID_DISCONNECT_TIME = 2;
	/** �ݒ�敪ID:�o�[�W������� */
	public static final int CONFIG_ID_VERSION_NO = 3;
	/** �ݒ�敪ID:Push�ʒm�đ��M�ҋ@���ԁi���j */
	public static final int CONFIG_ID_PUSH_NOTIFICATION_INTERVAL_TIME = 4;
	/** �ݒ�敪ID:���҃R�[�h�L�����ԁi���j */
	public static final int CONFIG_ID_INVITATION_CODE_EXPIRATION_TIME = 5;

	/*-------- �f�t�H���g�l --------*/
	// /** �f�t�H���g�l:�O���[�vID */
	// public static final String DEFAULT_GROUP_ID = "1";

	/** �f�t�H���g�l:�ʒm������ */
	public static final Integer DEFAULT_NOTIFICATION_TIME_SOUND = 3;
	/** �f�t�H���g�l:�ʒm�o�C�u���� */
	public static final Integer DEFAULT_NOTIFICATION_TIME_VIBRATION = 3;
	/** �f�t�H���g�l:�o�b�e���[�ʒm��l */
	public static final Integer DEFAULT_NOTIFICATION_BATTERY = 30;

	/** �f�t�H���g�l:GoogleMap�����\�����W */
	public static final String DEFAULT_MAP_LATITUDE = "35.632899";
	public static final String DEFAULT_MAP_LONGITUDE = "139.880458";
	public static final String DEFAULT_MAP_ZOOM = "15";

	/** �f�t�H���g�l:GoogleMap��ɔz�u����ꏊ�̉~�̔��a */
	public static final Integer DEFAULT_PLACE_RADIUS = 10;
	public static final Integer PLACE_RADIUS_MIN = 10;
	public static final Integer PLACE_RADIUS_MAX = 1000;

	/*-------- ���̑��萔�l --------*/
	/** ���҃R�[�h�̌��� */
	public static final int INVITATION_CODE_LENGTH = 7;
	/** ���[�UID�̍ő啶���� */
	public static final int USER_ID_MAX_LENGTH = 15;
	/** �p�X���[�h�̍ő啶���� */
	public static final int PASSWORD_MAX_LENGTH = 15;
	/** ���[�U���̍ő啶���� */
	public static final int USER_NAME_MAX_LENGTH = 15;
	/** �O���[�vID�̍ő啶���� */
	public static final int GROUP_ID_MAX_LENGTH = 15;
	/** �ꏊ���̍ő啶���� */
	public static final int PLACE_NAME_MAX_LENGTH = 15;
	/** �O���[�v���̍ő啶���� */
	public static final int GROUP_NAME_MAX_LENGTH = 15;
	/** �ʒm���̍ő啶���� */
	public static final int NOTIFICATION_DATE_MAX_LENGTH = 8;
	/** �ʒm���Ԃ̍ő啶���� */
	public static final int NOTIFICATION_TIME_MAX_LENGTH = 4;
	/** �d�b�ԍ��̌����i�n�C�t�������j */
	public static final int TELEPHONE_NUMBER_LENGTH = 11;

	/** ���M�Ԋu�̗j��������ݒ�\���� */
	public static final int SEND_INTERVAL_CONFIG_COUNT = 5;

	/*-------- ���������p --------*/
	/** PostgreSQL SQLSTATE:��Ӑ���ᔽ */
	public static final String SQLSTATE_UNIQUE_VIOLATION = "23505";

	/** WGS84���n�n �ԓ����a */
	public static final double WGS84_EQU_RAD = 6378137.000000;
	/** WGS84���n�n �ɔ��a */
	public static final double WGS84_POL_RAD = 6356752.314245;

	/*-------- 2018/11/20�d�l�ǉ� �������[�U�`�F�b�N�p --------*/
	/** �������[�U���݃`�F�b�N�p:���݂��Ȃ� */
	public static final int CHECK_USER_NOT_EXIST = 0;
	/** �������[�U���݃`�F�b�N�p:���݂��� */
	public static final int CHECK_USER_EXIST = 1;
}
