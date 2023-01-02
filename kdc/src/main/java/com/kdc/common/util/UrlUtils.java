package com.kdc.common.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * URL �ɌW��郆�[�e�B���e�B�N���X.
 */
@Component
public class UrlUtils {

	/**
	 * Spring MVC �� View �ɑ΂��郊�_�C���N�g�w���̕������Ԃ�.
	 * 
	 * @param path
	 *            ���_�C���N�g��̃p�X
	 * @return ���_�C���N�g�w���̕����� ({@code not null})
	 */
	public static String getRedirectString(String path) {
		StringBuilder str = new StringBuilder();
		str.append("redirect:").append(CommonConst.WEB_BASE_URL);
		if (!path.startsWith("/")) {
			str.append("/");
		}
		str.append(path);

		return str.toString();
	}

	/**
	 * Spring MVC �� View �ɑ΂���t�H���[�h�w���̕������Ԃ�.
	 * 
	 * @param path
	 *            �t�H���[�h��̃p�X
	 * @return �t�H���[�h�w���̕����� ({@code not null})
	 */
	public static String getForwardString(String path) {
		StringBuilder str = new StringBuilder();
		str.append("forward:/").append(CommonConst.WEB_BASE_URL);
		if (!path.startsWith("/")) {
			str.append("/");
		}
		str.append(path);

		return str.toString();
	}

	/**
	 * Web ��ʂ� URL �������Ԃ�.
	 * <p>
	 * JavaScript �� HTML �Ȃǂ̃����N�Ɏg�p����
	 * </p>
	 * 
	 * @param path
	 *            �����N��̃p�X
	 * @return URL������ ({@code not null})
	 */
	public static String getWebLinkUrl(String path) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		StringBuilder str = new StringBuilder();
		str.append(request.getContextPath());
		if (!path.startsWith("/")) {
			str.append("/");
		}
		str.append(path);

		return str.toString();
	}

}
