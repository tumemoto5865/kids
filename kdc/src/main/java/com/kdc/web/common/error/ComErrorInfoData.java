package com.kdc.web.common.error;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

/**
 * �G���[�E�x�����F�f�[�^
 * 
 * @author shimizuh@kksse.co.jp
 * @since 2016/08/24
 * @version
 *
 */
@Component
public class ComErrorInfoData {

	// �G���[��񃊃X�g
	private ArrayList<ErrorInfo> errorInfoList;

	/**
	 * �G���[���N���X .
	 */
	public static class ErrorInfo {

		// �ʒu
		private String errorPosition;

		// �F�ύX��Z���N�^ID
		private String changeColorId;

		/**
		 * @return errorPosition
		 */
		public String getErrorPosition() {
			return errorPosition;
		}

		/**
		 * @param errorPosition
		 *            �Z�b�g���� errorPosition
		 */
		public void setErrorPosition(String errorPosition) {
			this.errorPosition = errorPosition;
		}

		/**
		 * @return changeColorId
		 */
		public String getChangeColorId() {
			return changeColorId;
		}

		/**
		 * @param changeColorId
		 *            �Z�b�g���� changeColorId
		 */
		public void setChangeColorId(String changeColorId) {
			this.changeColorId = changeColorId;
		}

	}

	/**
	 * @return errorInfoList
	 */
	public ArrayList<ErrorInfo> getErrorInfoList() {
		return errorInfoList;
	}

	/**
	 * @param errorInfoList
	 *            �Z�b�g���� errorInfoList
	 */
	public void setErrorInfoList(ArrayList<ErrorInfo> errorInfoList) {
		this.errorInfoList = errorInfoList;
	}
}
