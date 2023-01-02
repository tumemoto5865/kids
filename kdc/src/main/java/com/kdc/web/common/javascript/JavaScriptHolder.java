package com.kdc.web.common.javascript;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;
import com.kdc.common.util.CommonConst;
import com.kdc.common.util.KdcCommonUtils;
import com.kdc.web.common.error.ComErrorInfoData;
import com.kdc.web.common.error.ComErrorInfoData.ErrorInfo;

/**
 * JavaScript��CSS��ێ����A�}�X�^�y�[�W�ɔ��f����
 * 
 * @author shimizuh@kksse.co.jp
 * @since 2016/07/06
 * @version
 *
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JavaScriptHolder {

	// Ajax�Ŏg�p����g���K�[���ڂ�Key������.
	private static final String TRIGGER_ID = "triggerid";
	private static final String TRIGGER_VALUE = "triggerval";

	// JavaScript �C�x���g���ʎq
	public enum EventHandlerEnum {
		// ���ʎq
		onBlur("blur"),
		onChange("change"),
		onClick("click"),
		onDblclick("dblclick"),
		onFocus("focus"),
		onKeydown("keydown"),
		onKeypress("keypress"),
		onKeyup("keyup"),
		onLoad("load"),
		onMousedown("mousedown"),
		onMousemove("mousemove"),
		onMouseout("mouseout"),
		onMouseover("mouseover"),
		onMouseup("mouseup"),
		onResize("resize"),
		onScroll("scroll"),
		onSelect("select"),
		onSubmit("submit"),
		onUnload("unload"),
		;

		// �t�B�[���h
		private String source;

		// �R���X�g���N�^
		private EventHandlerEnum(String source) {
			this.source = source;
		}

		public String getSource() {
			return this.source;
		}
	}

	/**
	 * Ajax �ݒ荀��.
	 */
	public class AjaxItem {
		// Ajax�g���K�[���.
		private class Trigger {
			public String id;
			public EventHandlerEnum eventHandler;
		}

		// Ajax�p�����[�^���.
		private class Parameter {
			public String id;
			public String keyId;
			public String keyValue;
		}

		// Ajax�p�����[�^�i�Œ�l�j���.
		private class Constant {
			public String value;
			public String keyValue;
		}

		private String index;
		private String ajaxUrl;
		private Boolean isAsync;
		private String dataType;
		private String callBack;
		private List<Trigger> triggerList = new ArrayList<>();
		private List<Parameter> parameterList = new ArrayList<>();
		private List<Constant> constantList = new ArrayList<>();

		// Ajax�p��JavaScript�֐�����Ԃ�.
		protected String getFuncName() {
			return new StringBuilder("AjaxF").append(this.index).toString();
		}

		/**
		 * Ajax�g���K�[��ǉ�����.
		 * 
		 * @param targetId
		 *            �g���K�[��ݒ肷��HTML���ID������
		 * @param eventHandler
		 *            �ݒ肷��g���K�[���
		 */
		public void addTrigger(String targetId, EventHandlerEnum eventHandler) {
			Trigger trigger = new Trigger();
			trigger.id = StringUtils.trim(targetId);
			trigger.eventHandler = eventHandler;
			this.triggerList.add(trigger);
		}

		/**
		 * Ajax�p�����[�^��ǉ�����.
		 * 
		 * @param paramId
		 *            �l���擾����HTML���ID������
		 * @param keyId
		 *            �Ăяo�����̎擾Key������iHTML���ID������)
		 * @param keyValue
		 *            �Ăяo�����̎擾Key������(�l)
		 */
		public void addParameter(String paramId, String keyId, String keyValue) {
			Parameter parameter = new Parameter();
			parameter.id = StringUtils.trim(paramId);
			parameter.keyId = StringUtils.trim(keyId);
			parameter.keyValue = StringUtils.trim(keyValue);
			this.parameterList.add(parameter);
		}

		/**
		 * Ajax�p�����[�^(�Œ�l)��ǉ�����.
		 * 
		 * @param value
		 *            �Œ�l������
		 * @param keyValue
		 *            �Ăяo�����̎擾Key������(�Œ�l)
		 */
		public void addConstant(String value, String keyValue) {
			Constant constant = new Constant();
			constant.value = StringUtils.trim(value);
			constant.keyValue = StringUtils.trim(keyValue);
			this.constantList.add(constant);
		}
	}

	// �ϐ���`��֐���`�Ȃǂ� JavaScript���X�g
	private List<String> defineList = new ArrayList<>();
	// Document Ready���Ɏ��s����JavaScript�̃��X�g
	private List<String> javaScriptList = new ArrayList<>();
	// ���ʃ|�b�v�A�b�v��ʂ���e��ʂɒl��߂�JavaScript�̃��X�g(��������)
	private List<String> popupResultList = new ArrayList<>();
	// Ajax�ݒ荀�ڃ��X�g
	private List<AjaxItem> ajaxList = new ArrayList<>();
	// CSS���X�g
	private List<String> cssList = new ArrayList<>();

	/**
	 * �V���� Ajax�ݒ荀�ڂ�Ԃ�.
	 * 
	 * @return Ajax�ݒ荀�� ({@code not null})
	 */
	public AjaxItem getNewItem() {
		return new AjaxItem();
	}

	/**
	 * Ajax������ǉ�����(Response��JavaScript).
	 * 
	 * @param ajaxUrl
	 *            Ajax�ŌĂяo��URL
	 * @param isAsync
	 *            ����(false)/�񓯊�(true)
	 * @param item
	 *            Ajax�ݒ荀��
	 * @return �V����Ajax�ݒ荀�� ({@code not null})
	 */
	public AjaxItem addAjaxScript(String ajaxUrl, Boolean isAsync, AjaxItem item) {
		if (item.triggerList.size() == 0) {
			throw new IllegalArgumentException();
		}
		item.index = String.format("%03d", this.ajaxList.size() + 1);
		item.ajaxUrl = ajaxUrl;
		item.isAsync = isAsync;
		item.dataType = "script";
		item.callBack = "";
		this.ajaxList.add(item);
		return new AjaxItem();
	}

	/**
	 * Ajax������ǉ�����(Response��Json).
	 * 
	 * @param ajaxUrl
	 *            Ajax�ŌĂяo��URL
	 * @param isAsync
	 *            ����(false)/�񓯊�(true)
	 * @param callBack
	 *            Json���󂯎��JavaScript�֐���
	 * @param item
	 *            Ajax�ݒ荀��
	 * @return �V����Ajax�ݒ荀�� ({@code not null})
	 */
	public AjaxItem addAjaxJson(String ajaxUrl, Boolean isAsync, String callBack, AjaxItem item) {
		if (item.triggerList.size() == 0) {
			throw new IllegalArgumentException();
		}
		item.index = String.format("%04d", this.ajaxList.size() + 1);
		item.ajaxUrl = ajaxUrl;
		item.isAsync = isAsync;
		item.dataType = "json";
		item.callBack = callBack;
		this.ajaxList.add(item);
		return new AjaxItem();
	}

	/**
	 * Document Ready���Ɏ��s����JavaScript��o�^����.
	 * 
	 * @param javaScript
	 *            JavaScript������
	 */
	public void addJavaScript(String javaScript) {
		this.javaScriptList.add(javaScript);
	}

	/**
	 * �ϐ���`��֐���`�Ȃǂ�JavaScript��o�^����.
	 * 
	 * @param javaScript
	 *            JavaScript������
	 */
	public void addDefineScript(String javaScript) {
		this.defineList.add(javaScript);
	}

	/**
	 * ���ʃ|�b�v�A�b�v��ʂ���e��ʂɒl��߂�JavaScript(��������)�̃��X�g��o�^����.
	 * 
	 * @param javaScript
	 *            JavaScript������
	 */
	public void addPopupResultList(String javaScript) {
		this.popupResultList.add(javaScript);
	}

	/**
	 * CSS��o�^����.
	 * 
	 * @param css
	 *            CSS������
	 */
	public void addCssStyle(String css) {
		this.cssList.add(css);
	}

	/**
	 * �ŏI�I��JavaScript�\�[�X�������Ԃ�.
	 * 
	 * @param prettyPrint
	 *            ���s���`(true)/���`�Ȃ�(false)
	 * @return JavaScript�\�[�X������
	 */
	public String getJavaScriptSource(Boolean prettyPrint) {
		StringBuilder str = new StringBuilder();

		// �ϐ���`��֐���`�Ȃǂ�JavaScript���o�͂���
		for (String js : this.defineList) {
			str.append(js);
			str.append(prettyPrint ? "\n" : "");
		}

		// ���ʃ|�b�v�A�b�v��ʂ���e��ʂɒl��߂�JavaScript
		if (this.popupResultList.size() != 0) {
			str.append("$(function(){");
			str.append(prettyPrint ? "\n" : "");
			for (String js : this.popupResultList) {
				str.append(js);
				str.append(prettyPrint ? "\n" : "");
			}
			str.append("window.close();");
			str.append(prettyPrint ? "\n" : "");
			str.append("});");
			str.append(prettyPrint ? "\n" : "");
		}

		// Ajax�p��JavaScript���o�͂���
		str.append(this.getAjaxSource(prettyPrint));
		// Document Ready���Ɏ��s����JavaScript���o�͂���
		if (this.javaScriptList.size() != 0) {
			str.append("$(function(){");
			str.append(prettyPrint ? "\n" : "");
			for (String js : this.javaScriptList) {
				str.append(js);
				str.append(prettyPrint ? "\n" : "");
			}
			str.append("});");
			str.append(prettyPrint ? "\n" : "");
		}

		return str.toString();
	}

	/**
	 * CSS�������Ԃ�.
	 * 
	 * @return CSS������
	 */
	public String getCssStyleSource(Boolean prettyPrint) {
		StringBuilder str = new StringBuilder();

		// CSS���o�͂���
		for (String css : this.cssList) {
			str.append(css);
			str.append(prettyPrint ? "\n" : "");
		}

		return str.toString();
	}

	// Ajax��JavaScript�\�[�X��������擾����
	private String getAjaxSource(Boolean prettyPrint) {
		StringBuilder str = new StringBuilder();

		// �Ώۂ����݂��Ȃ��ꍇ
		if (this.ajaxList.isEmpty()) {
			return "";
		}

		// Trigger �� JavaScript ����
		str.append("$(function(){");
		str.append(prettyPrint ? '\n' : "");
		for (AjaxItem item : this.ajaxList) {
			for (AjaxItem.Trigger trigger : item.triggerList) {
				String eventName = new StringBuilder(trigger.eventHandler.getSource()).append('.')
						.append(item.getFuncName()).toString();
				str.append(prettyPrint ? '\t' : "");
				str.append("$(\"#").append(trigger.id).append("\")");
				str.append(".off(\"").append(eventName).append("\")");
				str.append(".on(\"").append(eventName).append("\",function(){");
				str.append(prettyPrint ? "\n\t\t" : "");
				str.append("isNumberCheck(this);");
				str.append("if (!$(this).prop(\"readonly\")){");
				str.append(prettyPrint ? "\n\t\t\t" : "");
				str.append(item.getFuncName()).append("(\"").append(trigger.id).append("\");");
				str.append(prettyPrint ? "\n\t\t" : "");
				str.append('}');
				str.append(prettyPrint ? "\n\t\t" : "");
				str.append("return false;");
				str.append(prettyPrint ? "\n\t" : "");
				str.append("});");
				str.append(prettyPrint ? "\n" : "");
			}
		}
		str.append("});");
		str.append(prettyPrint ? "\n\n" : "");

		// Function��JavaScript ����
		for (AjaxItem item : this.ajaxList) {
			// Ajax��Data��������
			LinkedHashMap<String, String> dataList = new LinkedHashMap<>();
			dataList.put(String.format("\"%s\"", TRIGGER_ID), "triggerid");
			dataList.put(String.format("\"%s\"", TRIGGER_VALUE), "getItemVal(triggerid)");
			for (AjaxItem.Parameter param : item.parameterList) {
				dataList.put(String.format("\"%s\"", param.keyId), String.format("\"%s\"", param.id));
				dataList.put(String.format("\"%s\"", param.keyValue), String.format("getItemVal(\"%s\")", param.id));
			}
			for (AjaxItem.Constant constant : item.constantList) {
				dataList.put(String.format("\"%s\"", constant.keyValue), String.format("\"%s\"", constant.value));
			}
			String lastKey = "";
			for (String key : dataList.keySet()) {
				lastKey = key;
			}
			// Ajax�֐�
			str.append("function ").append(item.getFuncName()).append("(triggerid){");
			str.append(prettyPrint ? "\n\t" : "");
			str.append("var inputdata = $('form').serializeArray();");
			str.append(prettyPrint ? "\n\t" : "");
			str.append("$.ajax({");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("url:\"").append(item.ajaxUrl).append("\",");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("type:\"POST\",");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("async:").append(item.isAsync).append(',');
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("dataType:\"").append(item.dataType).append("\",");
			str.append(prettyPrint ? "\n\t\t" : "");
			// Data�� �ݒ�
			str.append("data:{");
			for (Map.Entry<String, String> entry : dataList.entrySet()) {
				str.append(prettyPrint ? "\n\t\t\t" : "");
				str.append(entry.getKey()).append(':').append(entry.getValue());
				if (!lastKey.equals(entry.getKey())) {
					str.append(',');
				}
			}
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append(",'inputdata':inputdata");
			str.append(prettyPrint ? "\n\t" : "");
			str.append("}");
			str.append(prettyPrint ? "\n\t" : "");
			str.append("})");
			// �������̏���
			if (!item.callBack.isEmpty()) {
				str.append(".done(function(data,textStatus,jqXHR){");
				str.append(prettyPrint ? "\n\t\t" : "");
				str.append(item.callBack).append("(data);");
				str.append(prettyPrint ? "\n\t" : "");
				str.append("})");
			}
			// ���s���̏���
			str.append(".fail(function(data,textStatus,errorThrown){");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("if (data.status == 408) {");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("alert(\"�Z�b�V�������؂�܂����B\\n�ēx���O�C�����ĉ������B\");");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("return;");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("}");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("if (data.status == 0) {");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("alert(\"�ꎞ�I�ɃT�[�o�Ƃ̒ʐM���؂�܂����B\\n�ēx���삵�Ă��������B\");");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("return;");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("}");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("alert(\"�����G���[(\"+data.status+\":\"+textStatus+\":\"+errorThrown+\")\");");
			str.append(prettyPrint ? "\n\t\t" : "");
			str.append("});");
			str.append(prettyPrint ? "\n" : "");
			str.append('}');
			str.append(prettyPrint ? "\n\n" : "");
		}

		return str.toString();
	}

	/**
	 * �g���K�[���ݒ肳�ꂽ���ڂ�ID��������擾����.
	 * 
	 * @param request
	 *            WebRequest
	 * @return ID������
	 */
	public String getAjaxTriigerId(WebRequest request) {
		return request.getParameter(TRIGGER_ID);
	}

	/**
	 * �g���K�[���ݒ肳�ꂽ���ڂ̒l���擾����.
	 * 
	 * @param request
	 *            WebRequest
	 * @return �l������
	 */
	public String getAjaxTriggetValue(WebRequest request) {
		return request.getParameter(TRIGGER_VALUE);
	}

	/**
	 * Ajax�p�����[�^���擾����.
	 * 
	 * @param request
	 *            WebRequest
	 * @param key
	 *            �L�[������
	 * @return �p�����[�^��񕶎���
	 */
	public String getAjaxParameter(WebRequest request, String key) {
		return request.getParameter(key);
	}

	/**
	 * �\��/��\�����s��JavaScript���擾����.
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param isDisplay
	 *            �\��(true)/��\��(false)
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsDisplay(String id, Boolean isDisplay) {
		return new StringBuilder("$(\"#").append(id).append("\").toggle(").append(isDisplay).append(");").toString();
	}

	/**
	 * �L��/�������s��JavaScript���擾����.
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param isEnabled
	 *            �L��(true)/����(false)
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsEnabled(String id, Boolean isEnabled) {
		return new StringBuilder("$(\"#").append(id).append("\").prop(\"disabled\",").append(!isEnabled).append(");")
				.toString();
	}

	/**
	 * Text��ݒ肷��JavaScript���擾����.
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param text
	 *            �ݒ肷�镶����
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsSetText(String id, String text) {
		return new StringBuilder("$(\"#").append(id).append("\").text(\"").append(text).append("\");").toString();
	}

	/**
	 * Value��ݒ肷��JavaScript���擾����.
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param value
	 *            �ݒ肷�镶����
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsSetValue(String id, String value) {
		return new StringBuilder("$(\"#").append(id).append("\").val(\"").append(value).append("\");").toString();
	}

	/**
	 * �`�F�b�N���s��JavaScript���擾����.
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param isEnabled
	 *            �L��(true)/����(false)
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsChecked(String id, Boolean isEnabled) {
		return new StringBuilder("$(\"#").append(id).append("\").prop(\"checked\",").append(isEnabled).append(");")
				.toString();
	}

	/**
	 * ���W�I�{�^���ɑ΂���Value��ݒ肵�đI����Ԃɂ���JavaScript���擾����.
	 * 
	 * @param name
	 *            �Ώۂ̃��W�I�{�^���O���[�v��name������
	 * @param value
	 *            �ݒ肷��Value�l
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsSetRadioValue(String name, String value) {
		return new StringBuilder("$(\"input[name=\'").append(name).append("\']\").val([\"").append(value)
				.append("\"]);").toString();
	}

	/**
	 * SELECT���̑I�����ڂ��X�V����JavaScript���擾����.
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param key
	 *            �I������Ă���Key������
	 * @param map
	 *            �I�����ڂ�Map
	 * @param isNeedNull
	 *            ���I����Ԃ��K�v(true)/���I����Ԃ͕s�v(false)
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsSelect(String id, String key, LinkedHashMap<String, String> map, Boolean isNeedNull) {
		StringBuilder str = new StringBuilder();
		if (isNeedNull) {
			str.append("<option value=''></option>");
		}
		for (Entry<String, String> entry : map.entrySet()) {
			str.append("<option value='").append(entry.getKey()).append("'");
			if (entry.getKey().equals(key)) {
				str.append(" selected='selected'");
			}
			str.append(">").append(entry.getValue()).append("</option>");
		}

		return new StringBuilder("$(\"#").append(id).append("\").html(\"").append(str).append("\");").toString();
	}

	/**
	 * �摜(Img�^�O)�ɉ摜�̐ݒ���s��JavaScript���擾����.
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param imgString
	 *            �摜��Base64������
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsSettingImg(String id, String imgString) {
		return new StringBuilder("$(\"#").append(id).append("\").attr({'src':\"").append(CommonConst.ICON_IMG_HEADER)
				.append(imgString).append("\"});").toString();
	}

	/**
	 * JavaScript��URL�������g�ݗ��Ă�R�[�h��Ԃ�.
	 * 
	 * @param url
	 *            url������
	 * @param paramMap
	 *            �����}�b�v
	 * @return URL������g�ݗ���JavaScript�R�[�h������ ({@code not null})
	 */
	public static String getJsUrlBuilder(String url, LinkedHashMap<String, String> paramMap) {
		// �p�����[�^������𐶐�
		StringBuilder param = new StringBuilder();
		for (Map.Entry<String, String> item : paramMap.entrySet()) {
			param.append(param.length() == 0 ? ",\"?\"," : ",\"&\",");
			param.append(String.format("\"%s\"", item.getKey())).append(",\"=\",");
			param.append(item.getValue());
		}
		// URL �𐶐�
		StringBuilder js = new StringBuilder();
		js.append("[");
		js.append(String.format("\"%s\"", url));
		js.append(param);
		js.append("].join(\"\")");
		return js.toString();
	}

	/**
	 * JavaScript��Ajax�Ăяo���������g�ݗ��Ă�R�[�h��Ԃ�.
	 * 
	 * @param url
	 *            url������
	 * @param paramMap
	 *            �����}�b�v
	 * @return Ajax�Ăяo��JavaScript�R�[�h������ ({@code not null})
	 */
	public static String getJsAjaxBuilder(String url, LinkedHashMap<String, String> paramMap) {
		// Ajax�Ăяo��JavaScript�𐶐�
		String lastKey = "";
		for (String key : paramMap.keySet()) {
			lastKey = key;
		}
		StringBuilder js = new StringBuilder();
		js.append("if (!$(this).prop(\"readonly\")){");
		js.append("if (!$(this).prop(\"disabled\")){");
		js.append("$.ajax({");
		js.append("url:\"").append(url).append("\",");
		js.append("type:\"POST\",");
		js.append("async:true,");
		js.append("dataType:\"script\",");
		js.append("data:{");
		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			String value = entry.getValue();
			js.append(entry.getKey()).append(':').append(StringUtils.isEmpty(value) ? "\"\"" : value);
			if (!lastKey.equals(entry.getKey())) {
				js.append(',');
			}
		}
		js.append("}");
		js.append("})");
		// ���s���̏���
		js.append(".fail(function(data,textStatus,errorThrown){");
		js.append("if (data.status == 408) {");
		js.append("alert(\"�Z�b�V�������؂�܂����B\\n�ēx���O�C�����ĉ������B\");");
		js.append("}else{");
		js.append("alert(\"�����G���[(\"+textStatus+\":\"+errorThrown+\")\");");
		js.append("}");
		js.append("});");
		js.append("}");
		js.append("}");
		return js.toString();
	}

	/**
	 * Value��ݒ肷��JavaScript���擾����.
	 * 
	 * @param fromid
	 *            �擾���ڂ�ID������
	 * @param toid
	 *            �ݒ荀�ڂ�ID������
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsSetIdMainWindow(String fromid, String toid) {
		return this.getJsSetIdMainWindow(fromid, toid, null);
	}

	/**
	 * Value��ݒ肷��JavaScript���擾����(EventFire�Ή�).
	 * 
	 * @param fromid
	 *            �ݒ荀�ڂ�ID������
	 * @param toid
	 *            �ݒ荀�ڂ�ID������
	 * @param event
	 *            fire����C�x���g�i{@code null}�̏ꍇ�́Afire���Ȃ�)
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsSetIdMainWindow(String fromid, String toid, EventHandlerEnum event) {
		StringBuilder js = new StringBuilder();
		js.append("window.opener.$('#").append(toid).append("').val(").append("$('#" + fromid + "').val()").append(")");
		if (event != null) {
			js.append(".").append(event.getSource()).append("()");
		}
		return js.append(";").toString();
	}

	/**
	 * Value��ݒ肷��JavaScript���擾����.
	 * 
	 * @param value
	 *            �ݒ蕶����
	 * @param toid
	 *            �ݒ荀�ڂ�ID������
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsSetValueMainWindow(String value, String toid) {
		return this.getJsSetValueMainWindow(value, toid, null);
	}

	/**
	 * Value��ݒ肷��JavaScript���擾����(EventFire�Ή�).
	 * 
	 * @param value
	 *            �ݒ蕶����
	 * @param toid
	 *            �ݒ荀�ڂ�ID������
	 * @param event
	 *            fire����C�x���g�i{@code null}�̏ꍇ�́Afire���Ȃ�)
	 * @return JavaScript������({@code not null})
	 */
	public String getJsSetValueMainWindow(String value, String toid, EventHandlerEnum event) {
		StringBuilder js = new StringBuilder();
		js.append("window.opener.$('#").append(toid).append("').val('").append(value).append("')");
		if (event != null) {
			js.append(".").append(event.getSource()).append("()");
		}
		if (event != EventHandlerEnum.onChange) {
			// �ύX�t���O��ݒ肷��C�x���g��Fire
			js.append(".not('._noChangeFlg, :disabled')");
			js.append(".trigger('").append(EventHandlerEnum.onChange.getSource()).append(".changeflg')");
		}
		return js.append(";").toString();
	}

	/**
	 * �m�F�_�C�A���O��\������JavaScript���擾����.
	 * 
	 * @param buttonId
	 *            �{�^��ID
	 * @param message
	 *            ���b�Z�[�W
	 * @return JavaScript������({@code not null})
	 */
	public String getJsSetConfirm(String buttonId, String message) {
		StringBuilder js = new StringBuilder();

		js.append("$('#").append(buttonId).append("').click(function(){");
		js.append("if(!confirm('").append(message).append("')){");
		js.append("return false;");
		js.append("}");
		js.append("});");

		return js.append(";").toString();
	}

	/**
	 * �_�C�A���O��\������JavaScript���擾����.
	 * 
	 * @param message
	 *            ���b�Z�[�W
	 * @return JavaScript������({@code not null})
	 */
	public String getJsSetAlert(String message) {
		StringBuilder js = new StringBuilder();

		js.append("window.alert(\"" + message + "\");");

		return js.toString();
	}

	/**
	 * �e�[�u����̕\���E��\����\������CSS���擾����.
	 * 
	 * @param table
	 *            �Ώۃe�[�u����
	 * @param col
	 *            ��ԍ�
	 * @param isDisplay
	 *            �\���E��\��
	 * @return Css������({@code not null})
	 */
	public String getCssSetTableDisplay(String table, String col, Boolean isDisplay) {
		StringBuilder js = new StringBuilder();

		js.append("#").append(table).append(">thead>tr>th:nth-child(").append(col).append(") { display: ")
				.append(isDisplay ? "block" : "none").append(";}");
		js.append("#").append(table).append(">tbody>tr>td:nth-child(").append(col).append(") { display: ")
				.append(isDisplay ? "block" : "none").append(";}");

		return js.toString();
	}

	/**
	 * �e�[�u����̕\���E��\����\������JavaScript���擾����.
	 * 
	 * @param table
	 *            �Ώۃe�[�u����
	 * @param col
	 *            ��ԍ�
	 * @param isDisplay
	 *            �\���E��\��
	 * @return JavaScript������({@code not null})
	 */
	public String getJsSetTableDisplay(String table, String col, Boolean isDisplay) {
		StringBuilder js = new StringBuilder();

		js.append("$('#").append(table).append(">thead>tr>th:nth-child(").append(col).append(")').css('display','")
				.append(isDisplay ? "table-cell" : "none").append("');");
		js.append("$('#").append(table).append(">tbody>tr>td:nth-child(").append(col).append(")').css('display','")
				.append(isDisplay ? "table-cell" : "none").append("');");

		return js.toString();
	}

	/**
	 * �{�^�������\��CSS���擾����.
	 * 
	 * @param buttonId
	 *            �Ώۃ{�^��ID
	 * @return Css������({@code not null})
	 */
	public String getCssButtonEmphasis(String buttonId) {
		StringBuilder js = new StringBuilder();

		js.append("#").append(buttonId)
				.append(" { outline-style: none; box-shadow: 2px 2px 3px 1px rgba(255,0,0,0.8);}");

		return js.toString();
	}

	/**
	 * Form��inputdata�����擾����.
	 * 
	 * @param request
	 *            ���N�G�X�g���
	 * @param name
	 *            name
	 */
	public String getFormSerializeArrayName(WebRequest request, String name) {

		int cnt = 0;
		String result = "";
		// �ꗗ�\�������I���`�F�b�N��������
		while (!KdcCommonUtils.nullSafeEquals(request.getParameter("inputdata[" + cnt + "][name]"), "")) {
			if (KdcCommonUtils.nullSafeEquals(
					KdcCommonUtils.nullToEmpty(request.getParameter("inputdata[" + cnt + "][name]")), name)) {
				result = request.getParameter("inputdata[" + cnt + "][value]");
				break;
			}
			cnt++;
		}

		return result;
	}

	/**
	 * �|�b�v�A�b�v��ʂ̊m�F�{�^���������ɐe��ʂ̃{�^�����N���b�N����javaScript��߂�.
	 * �e��ʂ̃{�^�������݂��Ȃ��ꍇ�͂Ȃɂ������ɉ�ʂ����
	 * 
	 * @param buttonId
	 *            �e��ʃN���b�N�{�^��ID
	 * @return JavaScript������({@code not null})
	 */
	public String getJsPopupOkButton(String buttonId) {
		// JavaScript ����
		StringBuilder js = new StringBuilder();
		if (StringUtils.isNotEmpty(buttonId)) {
			js.append("window.opener.$('#" + buttonId + "').click();");
		}
		js.append("window.close();");
		return js.toString();
	}

	/**
	 * �Ώۂh�c�ɑ΂��ē���̃C�x���g�����������ꍇ�Ƀ{�^���N���b�N���s��JavaScript���擾.
	 * 
	 * @param eventId
	 *            �C�x���g�Ώۂ�ID
	 * @param event
	 *            �ݒ肷��g���K�[���
	 * @param buttonId
	 *            �N���b�N�{�^��ID
	 * @return JavaScript������({@code not null})
	 */
	public String getJsEventOnClick(String eventId, EventHandlerEnum event, String buttonId) {
		StringBuilder js = new StringBuilder();
		js.append("$('#");
		js.append(eventId);
		js.append("').");
		js.append(event.getSource());
		js.append("(function(){");
		js.append("$('#");
		js.append(buttonId);
		js.append("').trigger('click');})");

		return js.append(";").toString();
	}

	/**
	 * �J�����_�[�\��/��\�����s��JavaScript���擾����.
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param isDisplay
	 *            �\��(true)/��\��(false)
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsCalendarDisplay(String id, Boolean isDisplay) {
		StringBuilder sb = new StringBuilder();

		sb.append(this.getJsReadable(id, isDisplay));
		if (isDisplay) {
			sb.append("$(\"#").append(id).append("\").datepicker( \"option\", \"showOn\", \"button\");");
		} else {
			sb.append("$(\"#").append(id).append("\").datepicker( \"option\", \"showOn\", \"\");");
		}

		return sb.toString();
	}

	/**
	 * ���l���ڂ̃X�s�i�[�\��/��\�����s��JavaScript���擾����.
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param isDisplay
	 *            �\��(true)/��\��(false)
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsSpinnerDisplay(String id, Boolean isDisplay) {
		if (isDisplay) {
			return new StringBuilder("$(\"#").append(id).append("\").spinner().spinner(\"enable\");").toString();
		} else {
			return new StringBuilder("$(\"#").append(id).append("\").spinner().spinner(\"disable\");").toString();
		}
	}

	/**
	 * readonly�̗L��/�������s��JavaScript���擾����.
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param isReadonly
	 *            �ҏW��(true)/�ҏW�s��(false)
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsReadable(String id, Boolean isReadonly) {
		StringBuilder js = new StringBuilder();

		js.append("$(\"#").append(id).append("\").prop(\"readonly\",").append(!isReadonly).append(");");
		if (isReadonly) {
			js.append("$(\"#").append(id).append("\").removeClass(\"_readonly\");");
		} else {
			js.append("$(\"#").append(id).append("\").addClass(\"_readonly\");");
		}

		return js.toString();
	}

	/**
	 * readonly�̗L��/�������s��JavaScript���擾����.
	 * 
	 * <p>
	 * ���͕s�̏ꍇ�̓��x�����̕\�����s���B
	 * </p>
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param isReadonly
	 *            �ҏW��(true)/�ҏW�s��(false)
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsReadableToLabel(String id, Boolean isReadonly) {
		StringBuilder js = new StringBuilder();

		js.append("$(\"#").append(id).append("\").prop(\"readonly\",").append(!isReadonly).append(");");
		if (isReadonly) {
			js.append("$(\"#").append(id).append("\").removeClass(\"_attributeCodeDisp\");");
		} else {
			js.append("$(\"#").append(id).append("\").addClass(\"_attributeCodeDisp\");");
		}

		return js.toString();
	}

	/**
	 * �\��/��\����\������CSS���擾����(�̈���m��).
	 * 
	 * @param id
	 *            ���ږ�
	 * @param isVisibility
	 *            �\���E��\��
	 * @return CSS������({@code not null})
	 */
	public String getCssSetVisibility(String id, Boolean isVisibility) {
		StringBuilder js = new StringBuilder();
		js.append("#").append(id).append(" { visibility: ").append(isVisibility ? "visible" : "hidden").append(";}");

		return js.toString();
	}

	/**
	 * �L��/�������s��JavaScript���擾����(�̈���m��).
	 * 
	 * @param id
	 *            �Ώۍ��ڂ�ID������
	 * @param isVisibility
	 *            �L��(true)/����(false)
	 * @return JavaScript������ ({@code not null})
	 */
	public String getJsVisibility(String id, Boolean isVisibility) {
		StringBuilder str = new StringBuilder();
		str.append("$(function(){");
		str.append("var element = document.getElementById(");
		str.append("'");
		str.append(id);
		str.append("');");
		str.append("element.style.visibility = '");
		str.append(isVisibility ? "visible" : "hidden");
		str.append("';});");
		return str.toString();
	}

	/**
	 * ��ʕ\�����Ɏw��̃{�^�����N���b�N����javaScript��߂�.
	 * 
	 * @param buttonId
	 *            ��ʃN���b�N�{�^��ID
	 * @return JavaScript������({@code not null})
	 */
	public String getJsButtonClick(String buttonId) {
		// JavaScript ����
		StringBuilder js = new StringBuilder();
		if (StringUtils.isNotEmpty(buttonId)) {
			js.append("$('#" + buttonId + "').click();");
		}
		return js.toString();
	}

	/**
	 * �G���[�`�F�b�NJavaScript����.
	 * 
	 * @return
	 */
	public String getErrorAjax(List<ErrorInfo> errorInfoList) {
		// JavaScript ����
		StringBuilder js = new StringBuilder();

		for (ComErrorInfoData.ErrorInfo errorInfo : errorInfoList) {
			// ��ʂ̐F��ύX
			js.append("$('#").append(errorInfo.getChangeColorId())
					.append("').closest('td').css('background-color', 'orange');");
		}

		// // �m�F������JavaScript��ݒ�
		// js.append(this.javaScriptHolder.getJsPopupOkButton(buttonId));

		// �G���[�����폜
		errorInfoList = new ArrayList<ErrorInfo>();

		return js.toString();
	}

}
