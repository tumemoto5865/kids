package com.kdc.web.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdc.common.entity.db.AdministratorMasterEntity;
import com.kdc.mybatis.mapper.web.WebLoginMapper;

/**
 * ���O�C����� Service �N���X
 * 
 *
 */
@Service
public class WebLoginService {

	@Autowired
	private WebLoginMapper webLoginMapper;

	/**
	 * ��������.
	 * 
	 * @param form
	 */
	public void init(WebLoginForm form) {
		form.setLoginId("");
		form.setLoginPass("");
		form.setLoginErrorFlg(false);
	}

	/**
	 * �Ǘ��҃}�X�^�擾.
	 * 
	 * @param form
	 * 			���O�C����ʃt�H�[��
	 * @return �擾���茋��
	 */
	public Boolean getAdministrator(WebLoginForm form) {

		AdministratorMasterEntity rec = new AdministratorMasterEntity();
		rec.setAdministratorid(form.getLoginId());
		rec.setPassword(form.getLoginPass());

		AdministratorMasterEntity entity = webLoginMapper.selectAdmin(rec);
		if (entity == null) {
			return false;
		}
		return true;
	}

}
