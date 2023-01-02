package com.kdc.web.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import com.kdc.common.enums.WebPageEnum;
import com.kdc.common.util.CommonConst;
import com.kdc.common.util.UrlUtils;
import com.kdc.web.base.MasterPageController;



/**
 * ���O�C����� Controller �N���X
 * 
 *
 */
@Controller
@RequestMapping(CommonConst.WEB_BASE_URL + "/login")
@Transactional(rollbackFor = Exception.class)
public class WebLoginController extends MasterPageController {

	@Autowired
	private WebLoginService service;

	// �y�[�W����
	private static final String THISPAGE = WebPageEnum.LOGIN.getId();

	/**
	 * ������ʕ\��
	 */
	@RequestMapping(method = RequestMethod.GET)
	@Override
	public String showPage(Model model) {
		model.addAttribute("lblTitle", WebPageEnum.LOGIN.getPageTitleLabel());

		// ���͍��ڂ̏����l��ݒ�
		WebLoginForm form = new WebLoginForm();

		// ��ʏ���������
		this.service.init(form);

		model.addAttribute("form", form);

		// �}�X�^�y�[�W����
		return setMasterPage(model, THISPAGE);
	}

	/**
	 * �o�^�{�^������������.
	 * 
	 * @param model
	 * @param form
	 * 			���O�C����ʃt�H�[��
	 * @param session
	 * @param attributes
	 * @return ���_�C���N�g��̃p�X������
	 */
	@RequestMapping(params = "action=doCommit", method = RequestMethod.POST)
	public String doCommit(Model model, WebLoginForm form, HttpSession session, RedirectAttributes attributes) {

		// �Ǘ��Җ⍇��
		if (!this.service.getAdministrator(form)) {
			form.setLoginErrorFlg(true);

			// login�ĕ\��
			model.addAttribute("lblTitle", WebPageEnum.LOGIN.getPageTitleLabel());
			model.addAttribute("form", form);
			return THISPAGE;
		} else {
			super.loginInfoHolder.setLoginId(form.getLoginId());
			super.loginInfoHolder.setSessionId(session.getId());
			// �J�ڐ�ɒl��ݒ�
			attributes.addAttribute("loginId", form.getLoginId());
			attributes.addAttribute("sessionId", session.getId());

			// main�֑J��
			return UrlUtils.getRedirectString(WebPageEnum.MAIN.getId());
		}
	}

}
