package com.kdc.web.masterpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.kdc.web.common.javascript.JavaScriptConfirmHolder;
import com.kdc.web.common.javascript.JavaScriptHolder;

/**
 * �}�X�^�y�[�W�����i�F�؂Ȃ��j
 * 
 * @author umemeoto
 * @since 2016/07/06
 * @version
 *
 */
@Component
public class MasterPage {

    // �m�F���b�Z�[�W���ێ�
    @Autowired
    private JavaScriptConfirmHolder javaScriptConfirmHolder;
    
    // Ajax ���ێ�
    @Autowired
    private JavaScriptHolder javaScriptHolder;

//    // �������
//    @Autowired
//    private CodeInfoGengoHolder codeInfoGengoHolder; 
    
    /**
     * �}�X�^�y�[�W�\������. 
     */
    public void showPage(Model model) {
        // JavaScript �̐ݒ�
        StringBuilder js = new StringBuilder();
        // �i�m�F���b�Z�[�W�p��JavaScript�j
        js.append(this.javaScriptConfirmHolder.getJavaScriptSource(false));
//        // �i�����ϊ��p�̏��j
//        js.append("var msSrvGengoDays=").append(this.codeInfoGengoHolder.getJsGengoDaysArray()).append(";");
//        js.append("var msSrvGengoWarekis=").append(this.codeInfoGengoHolder.getJsGengoWarekisArray()).append(";");
//        // �i�x�����f�p�̏��j
//        js.append("var msSrvHolidays=").append("[];");
        // �i���[�U��`��JavaScript�j
        js.append(this.javaScriptHolder.getJavaScriptSource(false));
        model.addAttribute("pagejs", js.toString());

        // CSS �̐ݒ�
        StringBuilder css = new StringBuilder();
        css.append(this.javaScriptHolder.getCssStyleSource(false));
        model.addAttribute("pagecss", css.toString());
        
        return;
    }

}
