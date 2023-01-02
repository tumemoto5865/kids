package com.kdc.web.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.kdc.web.common.WebLoginInfoHolder;
import com.kdc.web.common.javascript.JavaScriptConfirmHolder;
import com.kdc.web.common.javascript.JavaScriptHolder;
import com.kdc.web.masterpage.MasterPage;

/**
 * �}�X�^�y�[�W�N���X
 */
public abstract class MasterPageController {

	// ���O�C�����ێ�
	@Autowired
	protected WebLoginInfoHolder loginInfoHolder;

	// JavaScript ���ێ�
	@Autowired
	protected JavaScriptHolder javaScriptHolder;

	// �m�F���b�Z�[�W���ێ�
	@Autowired
	protected JavaScriptConfirmHolder javaScriptConfirmHolder;

	// �}�X�^�y�[�W����
	@Autowired
	private MasterPage masterPage;

	/**
	 * ������ʕ\��.
	 * 
	 * @see org.springframework.web
	 */
	public abstract String showPage(Model model);

	/**
	 * �}�X�^�[�y�[�W����.
	 * 
	 * @param model
	 *            org.springframework.ui.Model
	 */
	protected String setMasterPage(Model model, String page) {
		this.masterPage.showPage(model);
		return page;
	}

}
