package com.alodiga.wallet.web.register.converter;

import com.alodiga.wallet.common.model.CivilStatus;
import com.alodiga.wallet.web.register.controller.SecurityQuestionController;
import com.ericsson.alodiga.ws.PreguntaIdioma;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author hvarona
 */
@ManagedBean(name = "preguntaSecretaConverter")
@ViewScoped
public class PreguntaSecretaConverter implements Converter {

    @ManagedProperty(value = "#{securityQuestionController}")
    private SecurityQuestionController securityQuestionController;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.equals("")) {
            return "";
        }
        try {
            return securityQuestionController.getPreguntaIdioma(Integer.parseInt(submittedValue));
        } catch (NumberFormatException ex) {
            Logger.getLogger(PreguntaSecretaConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            if (value instanceof PreguntaIdioma) {
                return Long.toString(((PreguntaIdioma) value).getPreguntaIdiomaId());
            } else {
                return value.toString();
            }

        }
    }

    public void setSecurityQuestionController(SecurityQuestionController securityQuestionController) {
        this.securityQuestionController = securityQuestionController;
    }

}
