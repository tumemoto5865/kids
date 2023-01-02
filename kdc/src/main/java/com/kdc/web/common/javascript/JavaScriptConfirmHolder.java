package com.kdc.web.common.javascript;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.kdc.common.enums.ConfirmMessageEnum;
import com.kdc.common.util.KdcCommonUtils;

/**
 * �m�F���b�Z�[�W�Ɋւ���JavaScript��ێ����A�}�X�^�y�[�W�ɔ��f����
 * 
 * @author umemeoto
 * @since 2017/01/11
 * @version
 *
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JavaScriptConfirmHolder {

	private ArrayList<ConfirmItem> confirmList = new ArrayList<>();

	private class ConfirmItem {
		private String buttonId;
		private ConfirmMessageEnum confirmMessage;
		private String callJavaScript;
	}

	/**
	 * �m�F���b�Z�[�W�o�^.
	 * 
	 * @param buttonId
	 *            �m�F���b�Z�[�W��ݒ肷��{�^����ID
     * @param confirmMessage �\������m�F���b�Z�[�W�̃��b�Z�[�WID
	 * @param callJavaScript
	 *            �n�j�{�^��������̒ǉ�����JavaScript/{@code null}�̏ꍇ�͒ǉ������Ȃ�
	 */
	public void addItem(String buttonId, ConfirmMessageEnum confirmMessage, String callJavaScript) {
		ConfirmItem item = new ConfirmItem();
		item.buttonId = buttonId;
		item.confirmMessage = confirmMessage;
		item.callJavaScript = callJavaScript;
		this.confirmList.add(item);
		return;
	}

	/**
	 * �m�F���b�Z�[�W�p��JavaScript�\�[�X�������Ԃ�.
	 * 
	 * @param prettyPrint
	 *            ���s���`(true)/���`�Ȃ�(false)
	 * @return JavaScript�\�[�X������
	 */
	public String getJavaScriptSource(Boolean prettyPrint) {
		StringBuilder js = new StringBuilder();
		// �Ăяo���֐���`
		for (ConfirmItem item : this.confirmList) {
			js.append("function fnc_").append(item.buttonId).append("_Confirm(e){");
			js.append(prettyPrint ? "\n\t" : "");
			// �m�F���b�Z�[�W�\���F�����Ȃ�
			js.append("if(!confirmNormal(e,");
			js.append(item.confirmMessage.getMessageString());
			js.append(")){return false;}");
			if (!KdcCommonUtils.isEmpty(item.callJavaScript)) {
				js.append(item.callJavaScript);
			}
			js.append("return true;");

			js.append(prettyPrint ? "\n" : "");
			js.append("}");
			js.append(prettyPrint ? "\n" : "");
		}
		// �{�^���փC�x���g�o�^
		if (this.confirmList.size() != 0) {
			js.append("$(function(){");
			js.append(prettyPrint ? "\n\t" : "");
			for (ConfirmItem item : this.confirmList) {
				js.append("$(\"#").append(item.buttonId).append("\")");
				js.append(".off(\"click.confirm\")");
				js.append(".on(\"click.confirm\",function(e){");

				// �x�����b�Z�[�W�ő��s��̓��b�Z�[�W����
				js.append("if($('#ignoreAttentionFlg').val() == '1'){return true;}");
				js.append("return fnc_").append(item.buttonId).append("_Confirm(e);");
				js.append("});");
				js.append(prettyPrint ? "\n" : "");
			}
			js.append("});");
			js.append(prettyPrint ? "\n" : "");
		}
		// JavaScript�ԋp
		return js.toString();
	}

}
