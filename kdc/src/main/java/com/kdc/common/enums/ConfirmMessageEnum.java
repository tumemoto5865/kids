package com.kdc.common.enums;

import com.kdc.common.util.KdcCommonUtils;

/**
 * �m�F���b�Z�[�W�ꗗ
 * 
 * @author umemoto
 * @since 2017/01/11
 * @version
 *
 */
public enum ConfirmMessageEnum {
	// �񋓎q(�W�����b�Z�[�W)
	DEFAULT_COMMIT("�o�^���܂����H"),
	DELETE_USER("���̃��[�U���폜���܂��B��낵���ł����H"),
	DELETE_PLACE("�I�������ꏊ���폜���܂��B��낵���ł����H"),;

	// �t�B�[���h
	final String message;

	// �R���X�g���N�^
	private ConfirmMessageEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * �\�����镶������擾.
	 * 
	 * @return ���b�Z�[�W������A{@code null}�̏ꍇ�A������"null"��Ԃ�
	 */
	public String getMessageString() {
		if (KdcCommonUtils.isEmpty(this.message)) {
			return "null";
		}
		return new StringBuilder().append("\"").append(this.message).append("\"").toString();
	}

}
