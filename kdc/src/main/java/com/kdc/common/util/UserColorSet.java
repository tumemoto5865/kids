package com.kdc.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * ���[�U�\���F�N���X.
 */
public class UserColorSet {

	private String colorName;
	private String lineColor;
	private int markerColorId;

	// private Color colorObj;

	public UserColorSet(String colorName, String lineColor, int markerColorId) {
		this.colorName = colorName;
		this.lineColor = lineColor;
		this.markerColorId = markerColorId;
		// this.colorObj = new
		// Color(Integer.parseInt(StringUtils.substring(lineColor, 2, 8), 16));
	}

	/**
	 * @return colorName
	 */
	public String getColorName() {
		return colorName;
	}

	/**
	 * @param colorName
	 *            �Z�b�g���� colorName
	 */
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	/**
	 * @return lineColor
	 */
	public String getLineColor() {
		return lineColor;
	}

	/**
	 * @param lineColor
	 *            �Z�b�g���� lineColor
	 */
	public void setLineColor(String lineColor) {
		this.lineColor = lineColor;
	}

	/**
	 * @return markerColorId
	 */
	public int getMarkerColorId() {
		return markerColorId;
	}

	/**
	 * @param markerColorId
	 *            �Z�b�g���� markerColorId
	 */
	public void setMarkerColorId(int markerColorId) {
		this.markerColorId = markerColorId;
	}

	/**
	 * @return ���C���J���[��RGB�̈�
	 */
	public String getLineColorRGBHex() {
		return StringUtils.substring(this.lineColor, 2, 8);
	}

	/**
	 * @return ���C���J���[�̃A���t�@�i���߁j�̈�
	 */
	public String getLineColorAlphaHex() {
		return StringUtils.substring(this.lineColor, 0, 2);
	}

	/**
	 * @return ���C���J���[�̐ԗ̈�
	 */
	public String getLineColorRedHex() {
		return StringUtils.substring(this.lineColor, 2, 4);
	}

	/**
	 * @return ���C���J���[�̗Η̈�
	 */
	public String getLineColorGreenHex() {
		return StringUtils.substring(this.lineColor, 4, 6);
	}

	/**
	 * @return ���C���J���[�̐̈�
	 */
	public String getLineColorBlueHex() {
		return StringUtils.substring(this.lineColor, 6, 8);
	}

	/**
	 * @return ���C���J���[�̓��ߗ̈�
	 */
	public double getLineColorAlpha() {
		return ((int) Long.parseLong(this.getLineColorAlphaHex(), 16)) / (double) 255.0;
	}

	/**
	 * @return ���C���J���[�Ԃ�16�i����Long�^�ɕϊ�
	 */
	public int getLineColorRed() {
		return (int) Long.parseLong(this.getLineColorRedHex(), 16);
	}

	/**
	 * @return ���C���J���[�΂�16�i����Long�^�ɕϊ�
	 */
	public int getLineColorGreen() {
		return (int) Long.parseLong(this.getLineColorGreenHex(), 16);
	}

	/**
	 * @return ���C���J���[��16�i����Long�^�ɕϊ�
	 */
	public int getLineColorBlue() {
		return (int) Long.parseLong(this.getLineColorBlueHex(), 16);
	}

	// /**
	// * @return �Ԃ�RGB�l
	// */
	// public int getMarkerColorRed() {
	// return this.colorObj.getRed();
	// }
	//
	// /**
	// * @return �΂�RGB�l
	// */
	// public int getMarkerColorGreen() {
	// return this.colorObj.getGreen();
	// }
	//
	// /**
	// * @return ��RGB�l
	// */
	// public int getMarkerColorBlue() {
	// return this.colorObj.getBlue();
	// }
	//
	// /**
	// * @return �}�[�J�[�J���[RGB16�i��
	// */
	// public String getMarkerColorRGBHex() {
	// return new
	// StringBuilder().append(this.getMarkerColorRedHex()).append(this.getMarkerColorGreenHex())
	// .append(this.getMarkerColorBlueHex()).toString();
	// }
	//
	// /**
	// * @return 16�i��2���̕�����ɕϊ������Ԃ�RGB�l
	// */
	// public String getMarkerColorRedHex() {
	// return String.format("%02x", this.getMarkerColorRed());
	// }
	//
	// /**
	// * @return 16�i��2���̕�����ɕϊ������΂�RGB�l
	// */
	// public String getMarkerColorGreenHex() {
	// return String.format("%02x", this.getMarkerColorGreen());
	// }
	//
	// /**
	// * @return 16�i��2���̕�����ɕϊ�������RGB�l
	// */
	// public String getMarkerColorBlueHex() {
	// return String.format("%02x", this.getMarkerColorBlue());
	// }

}
