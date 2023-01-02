package com.kdc.common.util;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import com.kdc.common.entity.api.base.DeviceInfoEntity;
import com.kdc.common.entity.api.base.UserInfoEntity;
import com.kdc.common.entity.db.PlaceMasterEntityWrapper;
import com.kdc.common.enums.ApiIdEnum;

/**
 * �L�b�Y�R���g���[���p���[�e�B���e�B�N���X.
 */
@Component
public class KdcCommonUtils {

	private static DateTimeFormatter DATEFORMAT_DATETIME = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private static DateTimeFormatter DATEFORMAT_DATE = DateTimeFormatter.ofPattern("yyyyMMdd");
	private static DateTimeFormatter DATEFORMAT_TIME = DateTimeFormatter.ofPattern("HHmm");
	private static DateTimeFormatter DATEFORMAT_TIME_DISP = DateTimeFormatter.ofPattern("H:mm");
	private static DateTimeFormatter DATEFORMAT_TIME_HMS_DISP = DateTimeFormatter.ofPattern("H:mm:ss");
	private static DateTimeFormatter DATEFORMAT_CALENDARDATE = DateTimeFormatter.ofPattern("yyyy.MM.dd E");

	private static double WGS84_ECC_POW2 = (Math.pow(CommonConst.WGS84_EQU_RAD, 2)
			- Math.pow(CommonConst.WGS84_POL_RAD, 2)) / Math.pow(CommonConst.WGS84_EQU_RAD, 2);
	private static double WGS84_MARC_CON = CommonConst.WGS84_EQU_RAD * (1 - WGS84_ECC_POW2);

	private static Map<String, String> propertyMap = new HashMap<>();

	public static UserColorSet[] USER_COLOR_LIST;

	/**
	 * �v���p�e�B�����擾.
	 * 
	 * @param key
	 * @return �ݒ�t�@�C���̃v���p�e�B���
	 */
	public static String getProperty(String key) {
		if (propertyMap.isEmpty()) {
			loadPropertiesFromIni();
		}
		if (propertyMap.containsKey(key)) {
			return propertyMap.get(key);
		} else {
			return loadPropertiesFromIni(key);
		}
	}

	/**
	 * �ݒ�t�@�C������v���p�e�B�������[�h����
	 */
	public static void loadPropertiesFromIni() {
		try {
			Properties properties = getPropertyFile();
			// Map�Ɋi�[
			for (Map.Entry<Object, Object> e : properties.entrySet()) {
				propertyMap.put(e.getKey().toString(), e.getValue().toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return;
	}

	/**
	 * �ݒ�t�@�C������L�[���w�肵�ăv���p�e�B�������[�h����
	 * 
	 * @param key
	 * @return �v���p�e�B���z��
	 */
	public static String loadPropertiesFromIni(String key) {
		String value = new String();
		try {
			value = getPropertyFile().getProperty(key, null);
			if (StringUtils.isNotEmpty(value)) {
				propertyMap.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return value;
	}

	/**
	 * �v���p�e�B�t�@�C�����擾����
	 * 
	 * @return �v���p�e�B���
	 * @throws IOException
	 */
	public static Properties getPropertyFile() throws IOException {
		Properties properties = new Properties();
		// �v���p�e�B�t�@�C���̃p�X���w�肷��
		String strpass = CommonConst.SYSTEM_MODULE_PATH + CommonConst.SYSTEM_INI_FILE_NAME;

		InputStream istream = new FileInputStream(strpass);
		InputStreamReader ireader = new InputStreamReader(istream, "SJIS");
		properties.load(ireader);
		istream.close();

		return properties;
	}

	/**
	 * ���ݓ�����Timestamp�l���擾
	 * 
	 * @return ���ݓ���Timestamp�̕�����
	 */
	public static Timestamp getNowTimestamp() {
		return Timestamp.valueOf(LocalDateTime.now().withNano(0));
	}

	/**
	 * ���ݓ���������(YYYYMMDDHHMISS)���擾
	 * 
	 * @return ���ݓ���yyyyMMddHHmmss�̕�����
	 */
	public static String getNowDateTimeString() {
		return LocalDateTime.now().format(DATEFORMAT_DATETIME);
	}

	/**
	 * ���ݓ��t������(YYYYMMDD)���擾
	 * 
	 * @return ���ݓ���yyyyMMdd�̕�����
	 */
	public static String getNowDateString() {
		return LocalDateTime.now().format(DATEFORMAT_DATE);
	}

	/**
	 * Timestamp�l�����������(YYYYMMDDHHMISS)�ɕϊ�
	 * 
	 * @param srcTimestamp
	 * @return ����yyyyMMddHHmmss�̕�����
	 */
	public static String timestampToDateTimeString(Timestamp srcTimestamp) {
		if (srcTimestamp == null) {
			return null;
		}
		return srcTimestamp.toLocalDateTime().format(DATEFORMAT_DATETIME);
	}

	/**
	 * Timestamp�l����t������(YYYYMMDD)�ɕϊ�
	 * 
	 * @param srcTimestamp
	 * @return ����yyyyMMdd�̕�����
	 */
	public static String timestampToDateString(Timestamp srcTimestamp) {
		if (srcTimestamp == null) {
			return null;
		}
		return srcTimestamp.toLocalDateTime().format(DATEFORMAT_DATE);
	}

	/**
	 * Time�l������������(HHMI)�ɕϊ�
	 * 
	 * @param srcTime
	 * @return ����HHmm�̕�����
	 */
	public static String timeToHourMinuteString(Time srcTime) {
		if (srcTime == null) {
			return null;
		}
		return srcTime.toLocalTime().format(DATEFORMAT_TIME);
	}

	/**
	 * Time�l��\���p����������(H:MI)�ɕϊ�
	 * 
	 * @param srcTime
	 * @return ����H:mm�̕�����
	 */
	public static String timeToHourMinuteStringForDisp(Time srcTime) {
		if (srcTime == null) {
			return null;
		}
		return srcTime.toLocalTime().format(DATEFORMAT_TIME_DISP);
	}

	/**
	 * Timestamp�l��\���p����������(H:MI:SS)�ɕϊ�
	 * 
	 * @param srcTimestamp
	 * @return ����H:mm:ss�̕�����
	 */
	public static String timeToTimeStringForDisp(Timestamp srcTimestamp) {
		if (srcTimestamp == null) {
			return null;
		}
		return srcTimestamp.toLocalDateTime().format(DATEFORMAT_TIME_HMS_DISP);
	}

	/**
	 * Timestamp�l���J�����_�[�\���p������ɕϊ�
	 * 
	 * @param srcTimestamp
	 * @return ����yyyy.MM.dd E�̕�����
	 */
	public static String timestampToCalendarDateString(Timestamp srcTimestamp) {
		if (srcTimestamp == null) {
			return null;
		}
		return srcTimestamp.toLocalDateTime().format(DATEFORMAT_CALENDARDATE);
	}

	/**
	 * ����������(YYYYMMDDHHMISS)��Timestamp�l�ɕϊ�
	 * 
	 * @param srcString
	 * @return ����yyyyMMddHHmmss��Timestamp�l
	 */
	public static Timestamp dateTimeStringToTimestamp(String srcString) {
		if (isEmpty(srcString)) {
			return null;
		}
		try {
			LocalDateTime dateTime = LocalDateTime.parse(srcString, DATEFORMAT_DATETIME);
			return Timestamp.valueOf(dateTime);
		} catch (DateTimeParseException e) {
			// ���ԕ����w�薳���̏ꍇ��00:00:00�����ŕϊ���
			return dateStringToTimestamp(srcString);
		}
	}

	/**
	 * ���t������(YYYYMMDD)��Timestamp�l�ɕϊ�
	 * 
	 * @param srcString
	 * @return ����yyyyMMdd��Timestamp�l
	 */
	public static Timestamp dateStringToTimestamp(String srcString) {
		if (isEmpty(srcString)) {
			return null;
		}
		try {
			LocalDate date = LocalDate.parse(srcString, DATEFORMAT_DATE);
			return Timestamp.valueOf(date.atTime(LocalTime.MIN));
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	/**
	 * ����������(HHMI)��Time�l�ɕϊ�
	 * 
	 * @param srcString
	 * @return ����HHmm��Time�l
	 */
	public static Time hourMinuteStringToTime(String srcString) {
		if (isEmpty(srcString)) {
			return null;
		}
		try {
			LocalTime time = LocalTime.parse(srcString, DATEFORMAT_TIME);
			return Time.valueOf(time);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	/**
	 * �\���p����������(HH:MI)��Time�l�ɕϊ�
	 * 
	 * @param srcString
	 * @return ����H:mm��Time�l
	 */
	public static Time hourMinuteStringForDispToTime(String srcString) {
		if (isEmpty(srcString)) {
			return null;
		}
		try {
			LocalTime time = LocalTime.parse(srcString, DATEFORMAT_TIME_DISP);
			return Time.valueOf(time);
		} catch (DateTimeParseException e) {
			// �ʏ�̎�����������ϊ���
			return hourMinuteStringToTime(srcString);
		}
	}

	/**
	 * ����������(YYYYMMDDHHMISS)���������`�����ǂ������肷��.
	 * 
	 * @param srcString
	 * @return ���茋��
	 */
	public static boolean checkDateTimeString(String srcString) {
		if (isEmpty(srcString)) {
			return false;
		}
		try {
			LocalDateTime.parse(srcString, DATEFORMAT_DATETIME);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	/**
	 * ���t������(YYYYMMDD)���������`�����ǂ������肷��.
	 * 
	 * @param srcString
	 * @return ���茋��
	 */
	public static boolean checkDateString(String srcString) {
		if (isEmpty(srcString)) {
			return false;
		}
		try {
			LocalDate.parse(srcString, DATEFORMAT_DATE);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	/**
	 * ����������(HHMI)���������`�����ǂ������肷��.
	 * 
	 * @param srcString
	 * @return ���茋��
	 */
	public static boolean checkHourMinuteString(String srcString) {
		if (isEmpty(srcString)) {
			return false;
		}
		try {
			LocalTime.parse(srcString, DATEFORMAT_TIME);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	/**
	 * ����Timestamp�l����w��������Timestamp�l���擾
	 * 
	 * @param srcTimestamp
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return Timestamp�l
	 */
	public static Timestamp timestampPlusDateTime(Timestamp srcTimestamp, int year, int month, int day, int hour,
			int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(srcTimestamp);
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DAY_OF_MONTH, day);
		cal.add(Calendar.HOUR, hour);
		cal.add(Calendar.MINUTE, minute);
		cal.add(Calendar.SECOND, second);
		return new Timestamp(cal.getTime().getTime());
	}

	/**
	 * ����Timestamp�l����w��N�������Timestamp�l���擾
	 * 
	 * @param srcTimestamp
	 * @param year
	 * @param month
	 * @param day
	 * @return Timestamp�l
	 */
	public static Timestamp timestampPlusDate(Timestamp srcTimestamp, int year, int month, int day) {
		return timestampPlusDateTime(srcTimestamp, year, month, day, 0, 0, 0);
	}

	/**
	 * ����Timestamp�l����w�莞�Ԍ��Timestamp�l���擾
	 * 
	 * @param srcTimestamp
	 * @param hour
	 * @param minute
	 * @param second
	 * @return Timestamp�l
	 */
	public static Timestamp timestampPlusTime(Timestamp srcTimestamp, int hour, int minute, int second) {
		return timestampPlusDateTime(srcTimestamp, 0, 0, 0, hour, minute, second);
	}

	/**
	 * String�^�̃t���O���ڂ�ON/OFF�𔻒�
	 * 
	 * @param flg
	 * @return ���茋��
	 */
	public static boolean isFlgOn(String flg) {
		return StringUtils.equals(flg, CommonConst.FLG_ON.toString());
	}

	/**
	 * Integer�t���O��Boolean�l�ɕϊ�
	 * 
	 * @param flg
	 * @return �ϊ�����
	 */
	public static boolean isFlgOn(Integer flg) {
		return CommonConst.FLG_ON.equals(flg);
	}

	/**
	 * Boolean�l��Integer�t���O�ɕϊ�����.
	 * 
	 * @param flg
	 * @return �ϊ�����
	 */
	public static Integer valueFlg(boolean flg) {
		return flg ? CommonConst.FLG_ON : CommonConst.FLG_OFF;
	}

	/**
	 * �g��null����Bnull�A�󕶎��ɉ����ĕ�����"null"��true�Ɣ��肷��.
	 * 
	 * @param val
	 *            ���蕶����
	 * @return ���茋��
	 */
	public static boolean isEmpty(String val) {
		if (StringUtils.isEmpty(val) || StringUtils.equals(val, "null")) {
			return true;
		}
		return false;
	}

	/**
	 * ��r�Ώە����񂪓��ꂩ�ǂ����𔻒肷��B Null�Ƌ󕶎��A�󕶎���Null�ANull��Null�A�󕶎��Ƌ󕶎��͓���Ƃ݂Ȃ�.
	 * 
	 * @param equalsFrom
	 *            ��r������
	 * @param equalsTo
	 *            ��r������
	 * @return ��r����
	 */
	public static boolean nullSafeEquals(String equalsFrom, String equalsTo) {
		if (isEmpty(equalsFrom) && isEmpty(equalsTo)) {
			return true;
		}
		return StringUtils.equals(equalsFrom, equalsTo);
	}

	/**
	 * null�̏ꍇ�ɁA��l��Ԃ��Anull�ȊO�̏ꍇ�͈�����Ԃ�.
	 * 
	 * @param val
	 *            �ϊ��Ώې��l
	 * @return �ϊ�����
	 */
	public static String nullToEmpty(String val) {
		if (isEmpty(val)) {
			return "";
		}
		return val;
	}

	/**
	 * null�̏ꍇ�ɁA0��Ԃ��Anull�ȊO�̏ꍇ�͈�����Ԃ�.
	 * 
	 * @param val
	 *            �ϊ��Ώې��l
	 * @return �ϊ�����
	 */
	public static String nullToZero(String val) {
		if (isEmpty(val)) {
			return "0";
		}
		return val;
	}

	/**
	 * null�܂��͕ϊ��s�\�̏ꍇ��null��Ԃ��A����ȊO�̏ꍇ��Integer��Ԃ�
	 * 
	 * @param val
	 *            �ϊ��Ώې��l
	 * @return �ϊ�����
	 */
	public static Integer nullSafeParseInt(String val) {
		try {
			return isEmpty(val) ? null : Integer.parseInt(val);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * 2�n�_�Ԃ̋������Z�o.
	 * 
	 * @param startLat
	 *            �n�_�̈ܓx
	 * @param startLng
	 *            �n�_�̌o�x
	 * @param endLat
	 *            �I�_�̈ܓx
	 * @param endLng
	 *            �I�_�̌o�x
	 * @return �����im�j
	 */
	public static String computeDistance(BigDecimal startLat, BigDecimal startLng, BigDecimal endLat,
			BigDecimal endLng) {

		Double dStartLat = startLat.doubleValue();
		Double dStartLng = startLng.doubleValue();
		Double dEndLat = endLat.doubleValue();
		Double dEndLng = endLng.doubleValue();

		Double mLat = Math.toRadians((dStartLat + dEndLat) / 2.000000);
		Double dLat = Math.toRadians(dStartLat - dEndLat);
		Double dLng = Math.toRadians(dStartLng - dEndLng);

		Double w = Math.sqrt(1 - WGS84_ECC_POW2 * Math.pow(Math.sin(mLat), 2));

		Double mRad = WGS84_MARC_CON / Math.pow(w, 3);
		Double vRad = CommonConst.WGS84_EQU_RAD / w;

		Double distance = Math.sqrt(Math.pow(dLat * mRad, 2) + Math.pow(dLng * vRad * Math.cos(mLat), 2));

		return String.format("%.0f", distance);
	}

	/**
	 * �ꏊ�Ƃ̏d�Ȃ蔻��.
	 * 
	 * @return �ʒu��񂪏ꏊ�͈͓̔��ɂ���ꍇtrue
	 */
	public static boolean userPlaceEngaged(BigDecimal locationLat, BigDecimal locationLng,
			PlaceMasterEntityWrapper place) {
		// �ʒu���̍��W�Əꏊ�̒��S���W�Ƃ̋��������a�ȓ��Ȃ�΁A�ꏊ�͈͓̔�
		int distance = Integer
				.parseInt(computeDistance(locationLat, locationLng, place.getLatitude(), place.getLongitude()));
		if (distance <= place.getRadius()) {
			return true;
		}
		return false;
	}

	/**
	 * �V���ȃ��[�U�\���F�Z�b�g���擾����.
	 */
	public static UserColorSet getNewUserColorSet(int userSeq) {
		if (USER_COLOR_LIST == null) {
			int userColors = Integer.parseInt(nullToZero(getProperty("userColors")));
			USER_COLOR_LIST = new UserColorSet[userColors];
			for (int i = 1; i <= userColors; i++) {
				String[] userColor = StringUtils.split(getProperty("userColor" + i), ",");
				USER_COLOR_LIST[i - 1] = new UserColorSet(userColor[0], userColor[1], i);
			}
		}
		int mod = Math.floorMod(userSeq - 1, USER_COLOR_LIST.length);
		return USER_COLOR_LIST[mod];
	}

	/**
	 * �}�[�J�[�\���F��int�l���擾����
	 * 
	 * @param rgb
	 * @return hsbvals[0]
	 */
	public static int getMarkerColorFromRGB(String rgb) {
		float[] hsbvals = { 0, 0, 0 };
		int red = (int) Long.parseLong(StringUtils.substring(rgb, 0, 2), 16);
		int green = (int) Long.parseLong(StringUtils.substring(rgb, 2, 4), 16);
		int blue = (int) Long.parseLong(StringUtils.substring(rgb, 4, 6), 16);
		Color.RGBtoHSB(red, green, blue, hsbvals);
		return (int) hsbvals[0];
	}

	/**
	 * ���[�U�̃f�t�H���g�A�C�R���̃��X�g���擾����.
	 * 
	 * @return �A�C�R�����X�g
	 */
	public static List<IconDataSet> getUserDefaultIconList() {
		return getIconList(CommonConst.SYSTEM_MODULE_PATH + CommonConst.USER_ICON_DIR);
	}

	/**
	 * �ꏊ�A�C�R���̃��X�g���擾����.
	 * 
	 * @return �A�C�R�����X�g
	 */
	public static List<IconDataSet> getPlaceIconList() {
		return getIconList(CommonConst.SYSTEM_MODULE_PATH + CommonConst.PLACE_ICON_DIR);
	}

	/**
	 * �}�[�J�[�A�C�R���̃��X�g���擾����.
	 * 
	 * @return �A�C�R�����X�g
	 */
	public static List<IconDataSet> getMarkerIconList() {
		return getIconList(CommonConst.SYSTEM_MODULE_PATH + CommonConst.MARKER_ICON_DIR);
	}

	/**
	 * �w�肳�ꂽ�A�C�R���i�[�f�B���N�g������A�C�R���̃��X�g���擾����.
	 * 
	 * @param iconDir
	 *            �f�B���N�g���p�X
	 * @return �A�C�R�����X�g
	 */
	private static List<IconDataSet> getIconList(String iconDir) {

		List<IconDataSet> dataList = new ArrayList<>();

		File baseDir = new File(iconDir);
		File[] listOfFiles = baseDir.listFiles();

		if (listOfFiles != null) {
			for (File item : listOfFiles) {
				if (item.isFile()) {
					try {
						IconDataSet data = new IconDataSet();
						data.setIconName(StringUtils.substringBeforeLast(item.getName(), "."));
						InputStream istream = new FileInputStream(item.getPath());
						ByteArrayOutputStream outPutStream = new ByteArrayOutputStream();
						IOUtils.copy(istream, outPutStream);
						data.setIconData(KdcCommonUtils.scaleImage(outPutStream.toByteArray(), CommonConst.ICON_WIDTH,
								CommonConst.ICON_HEIGHT, "png"));
						istream.close();
						dataList.add(data);
					} catch (IOException e) {
						e.getStackTrace();
					}
				}
			}
		}

		return dataList;
	}

	/**
	 * �摜�̃o�C�g�X�g���[���f�[�^���A�C�R���\���p�T�C�Y�ɐ��`���������ŁABase64������ɕϊ�����
	 * 
	 * @param in
	 *            �摜�o�C�g�X�g���[��
	 * @return Base64�ϊ���̉摜�o�C�g�f�[�^
	 */
	public static String getIconStringBase64(byte[] in) {
		if (in.length == 0) {
			return "";
		}
		return Base64.getEncoder()
				.encodeToString(scaleImage(in, CommonConst.ICON_WIDTH, CommonConst.ICON_HEIGHT, "png"));
	}

	/**
	 * �摜�̃o�C�g�X�g���[�������T�C�Y����
	 * 
	 * @param in
	 *            �摜�o�C�g�f�[�^
	 * @param width
	 *            �摜�o�C�g�f�[�^
	 * @param height
	 *            �摜�o�C�g�f�[�^
	 * @param formatName
	 *            �摜�o�C�g�f�[�^
	 * @return �摜�o�C�g�X�g���[��
	 */
	public static byte[] scaleImage(byte[] in, int width, int height, String formatName) {
		try {
			BufferedImage org = ImageIO.read(new ByteArrayInputStream(in));
			ImageFilter filter = new AreaAveragingScaleFilter(width, height);
			ImageProducer p = new FilteredImageSource(org.getSource(), filter);
			java.awt.Image dstImage = Toolkit.getDefaultToolkit().createImage(p);
			BufferedImage dst = new BufferedImage(dstImage.getWidth(null), dstImage.getHeight(null),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = dst.createGraphics();

			// �w�i�𓧉ߐF�ɐݒ肵�ăC���[�W���쐬����
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
			Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, dst.getWidth(), dst.getHeight());
			g.fill(rect);
			g.setPaintMode();

			g.drawImage(dstImage, 0, 0, null);
			g.dispose();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(dst, formatName, baos);
			return baos.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ���[�U�̃A�C�R���摜���A�}�b�v��̃}�[�J�[�Ƃ��ĕ\������A�C�R���摜�ɉ��H����.
	 * 
	 * @param userIconFile
	 *            �A�C�R���摜�t�@�C��
	 * @param markerColorId
	 *            �}�[�J�[�\���F
	 * @return base64�ŃG���R�[�h���ꂽ�o�C�g�z��
	 * @throws IOException
	 */
	public static String createUserMarkerIconString(byte[] userIconFile, byte[] markerBaseFile) throws IOException {
		// ���[�U�A�C�R���摜
		BufferedImage userIconImg = ImageIO.read(new ByteArrayInputStream(userIconFile));
		// ���[�U�A�C�R����\������}�[�J�[�A�C�R���摜
		BufferedImage markerImage = ImageIO.read(new ByteArrayInputStream(markerBaseFile));

		// ���[�U�A�C�R���摜���~�^�ɐ؂���
		BufferedImage userChipImg = new BufferedImage(userIconImg.getWidth(), userIconImg.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D gr = userChipImg.createGraphics();
		gr.setColor(new Color(0, 1F, 0, 1F));
		RoundRectangle2D rr = new RoundRectangle2D.Double(7.0, 7.0, userIconImg.getWidth() - 15.5,
				userIconImg.getHeight() - 15.5, 40, 40);
		gr.fill(rr);
		BasicStroke wideStroke = new BasicStroke(4.0f);
		gr.setStroke(wideStroke);
		gr.setComposite(AlphaComposite.SrcIn);
		gr.drawImage(userIconImg, 0, 0, null);
		gr.dispose();

		// �؂��������[�U�A�C�R�����}�[�J�[�A�C�R���ɏd�˂�
		Graphics graphics1 = null;
		graphics1 = markerImage.getGraphics();
		int x = 0;
		int y = -3;
		graphics1.drawImage(userChipImg, x, y, null);

		// ������̉摜��ێ�
		ByteArrayOutputStream byteImg = new ByteArrayOutputStream();
		ImageIO.write(markerImage, "png", byteImg);

		return Base64.getEncoder().encodeToString(byteImg.toByteArray());
	}

	/**
	 * API�J�n���O�o��
	 * 
	 * @param api
	 *            API����ID
	 * @param userInfo
	 *            ���[�U���N���X
	 * @param deviceInfo
	 *            �[�����N���X
	 * @return ���O������
	 */
	public static String getApiStartLog(ApiIdEnum api, UserInfoEntity userInfo, DeviceInfoEntity deviceInfo) {
		StringBuilder logString = new StringBuilder();
		logString.append("API Called:");
		logString.append(api.toString());
		logString.append(" UserId:");
		logString.append(userInfo.getUserId());
		logString.append(" TelephoneNumber:");
		logString.append(deviceInfo.getTelephoneNumber());
		return logString.toString();
	}

	/**
	 * API�I�����O�o��
	 * 
	 * @param api
	 *            API����ID
	 * @param userInfo
	 *            ���[�U���N���X
	 * @param deviceInfo
	 *            �[�����N���X
	 * @param status
	 *            Http���X�|���X�X�e�[�^�X�R�[�h
	 * @param resultCd
	 *            �������ʃR�[�h
	 * @return ���O������
	 */
	public static String getApiEndLog(ApiIdEnum api, UserInfoEntity userInfo, DeviceInfoEntity deviceInfo,
			Integer status, Integer resultCd) {
		StringBuilder logString = new StringBuilder();
		logString.append("API Complete:");
		logString.append(api.toString());
		logString.append(" UserId:");
		logString.append(userInfo.getUserId());
		logString.append(" TelephoneNumber:");
		logString.append(deviceInfo.getTelephoneNumber());
		logString.append(" Status:");
		logString.append(status);
		logString.append(" resultCd:");
		logString.append(resultCd);
		return logString.toString();
	}

	/**
	 * API�G���[���O�o��
	 * 
	 * @param api
	 *            API����ID
	 * @param userInfo
	 *            ���[�U���N���X
	 * @param deviceInfo
	 *            �[�����N���X
	 * @param error
	 *            �G���[�R�[�h
	 * @return ���O������
	 */
	public static String getApiErrorLog(ApiIdEnum api, UserInfoEntity userInfo, DeviceInfoEntity deviceInfo,
			String error) {
		StringBuilder logString = new StringBuilder();
		logString.append("API Failed:");
		logString.append(api.toString());
		logString.append(" UserId:");
		logString.append(userInfo.getUserId());
		logString.append(" TelephoneNumber:");
		logString.append(deviceInfo.getTelephoneNumber());
		logString.append(" Error:");
		logString.append(error);
		return logString.toString();
	}
}
