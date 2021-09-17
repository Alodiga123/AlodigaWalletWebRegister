package com.alodiga.wallet.web.register.converter;

import com.alodiga.wallet.common.model.Profession;
import com.alodiga.wallet.web.register.controller.RegisterController;
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
@ManagedBean(name = "professionConverter")
@ViewScoped
public class ProfessionConverter implements Converter {

    @ManagedProperty(value = "#{registerController}")
    private RegisterController registerController;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.equals("")) {
            return "";
        }
        try {
            return registerController.getProfession(Long.parseLong(submittedValue));
        } catch (NumberFormatException ex) {
            Logger.getLogger(CountryConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            if (value instanceof Profession) {
                return Long.toString(((Profession) value).getId());
            } else {
                return value.toString();
            }

        }
    }

    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }

}
