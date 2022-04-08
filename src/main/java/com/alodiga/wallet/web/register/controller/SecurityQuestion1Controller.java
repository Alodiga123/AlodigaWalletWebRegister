package com.alodiga.wallet.web.register.controller;

import com.alodiga.wallet.common.ejb.BusinessPortalEJB;
import com.alodiga.wallet.common.ejb.UtilsEJB;
import com.alodiga.wallet.common.enumeraciones.RequestTypeE;
import com.alodiga.wallet.common.exception.EmptyListException;
import com.alodiga.wallet.common.exception.GeneralException;
import com.alodiga.wallet.common.exception.NullParameterException;
import com.alodiga.wallet.common.exception.RegisterNotFoundException;
import com.alodiga.wallet.common.genericEJB.EJBRequest;
import com.alodiga.wallet.common.model.Address;
import com.alodiga.wallet.common.model.AffiliationRequest;
import com.alodiga.wallet.common.model.BusinessCategory;
import com.alodiga.wallet.common.model.City;
import com.alodiga.wallet.common.model.CivilStatus;
import com.alodiga.wallet.common.model.Country;
import com.alodiga.wallet.common.model.CollectionType;
import com.alodiga.wallet.common.model.CollectionsRequest;
import com.alodiga.wallet.common.model.DocumentsPersonType;
import com.alodiga.wallet.common.model.Person;
import com.alodiga.wallet.common.model.PersonType;
import com.alodiga.wallet.common.model.PhonePerson;
import com.alodiga.wallet.common.model.PhoneType;
import com.alodiga.wallet.common.model.Profession;
import com.alodiga.wallet.common.model.RequestType;
import com.alodiga.wallet.common.model.State;
import com.alodiga.wallet.common.model.StatusApplicant;
import com.alodiga.wallet.common.utils.EjbConstants;
import com.ericsson.alodiga.ws.APIRegistroUnificadoProxy;
import com.ericsson.alodiga.ws.PreguntaIdioma;
import com.ericsson.alodiga.ws.Respuesta;
import com.ericsson.alodiga.ws.RespuestaPreguntasSecretas;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author henry
 */
@ManagedBean(name = "securityQuestion1Controller")
@ViewScoped
public class SecurityQuestion1Controller {

    private RespuestaPreguntasSecretas securityQuestionList;
    private List<PreguntaIdioma> preguntaIdiomaList = new ArrayList();
    private PreguntaIdioma preguntaIdiomaOne;
    private PreguntaIdioma preguntaIdiomaTwo;
    private PreguntaIdioma preguntaIdiomaThree;
    private PreguntaIdioma selectedPreguntaIdiomaOne;
    private PreguntaIdioma selectedPreguntaIdiomaTwo;
    private PreguntaIdioma selectedPreguntaIdiomaThree;
    private String anwserSecurityOne;
    private String anwserSecurityTwo;
    private String anwserSecurityThree;
    private static APIRegistroUnificadoProxy apiRegistroUnificado;

    @PostConstruct
    public void init() {
        try {
            apiRegistroUnificado = new APIRegistroUnificadoProxy();
            
            try {
                RespuestaPreguntasSecretas securityQuestionList = apiRegistroUnificado.getPreguntasSecretas("usuarioWS", "passwordWS", 4);
                for (PreguntaIdioma preguntas: securityQuestionList.getDatosRespuesta()) {
                    preguntaIdiomaList.add(preguntas);
                }
            
            } catch (RemoteException ex) {
                Logger.getLogger(SecurityQuestionController.class.getName()).log(Level.SEVERE, null, ex);      
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(SecurityQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RespuestaPreguntasSecretas getSecurityQuestionList() {
        return securityQuestionList;
    }

    public void setSecurityQuestionList(RespuestaPreguntasSecretas securityQuestionList) {
        this.securityQuestionList = securityQuestionList;
    }

    public PreguntaIdioma getSelectedPreguntaIdiomaOne() {
        return selectedPreguntaIdiomaOne;
    }

    public void setSelectedPreguntaIdiomaOne(PreguntaIdioma selectedPreguntaIdiomaOne) {
        System.out.println("Entro en el set selectedPreguntaIdiomaOne");
        this.selectedPreguntaIdiomaOne = selectedPreguntaIdiomaOne;
    }

    public PreguntaIdioma getSelectedPreguntaIdiomaTwo() {
        return selectedPreguntaIdiomaTwo;
    }

    public void setSelectedPreguntaIdiomaTwo(PreguntaIdioma selectedPreguntaIdiomaTwo) {
        this.selectedPreguntaIdiomaTwo = selectedPreguntaIdiomaTwo;
    }

    public PreguntaIdioma getPreguntaIdiomaOne() {
        return preguntaIdiomaOne;
    }

    public void setPreguntaIdiomaOne(PreguntaIdioma preguntaIdiomaOne) {
        this.preguntaIdiomaOne = preguntaIdiomaOne;
    }

    public String getAnwserSecurityOne() {
        return anwserSecurityOne;
    }

    public void setAnwserSecurityOne(String anwserSecurityOne) {
        this.anwserSecurityOne = anwserSecurityOne;
    }

    public PreguntaIdioma getPreguntaIdiomaTwo() {
        return preguntaIdiomaTwo;
    }

    public void setPreguntaIdiomaTwo(PreguntaIdioma preguntaIdiomaTwo) {
        this.preguntaIdiomaTwo = preguntaIdiomaTwo;
    }

    public String getAnwserSecurityTwo() {
        return anwserSecurityTwo;
    }

    public void setAnwserSecurityTwo(String anwserSecurityTwo) {
        this.anwserSecurityTwo = anwserSecurityTwo;
    }

    public PreguntaIdioma getPreguntaIdiomaThree() {
        return preguntaIdiomaThree;
    }

    public void setPreguntaIdiomaThree(PreguntaIdioma preguntaIdiomaThree) {
        this.preguntaIdiomaThree = preguntaIdiomaThree;
    }

    public PreguntaIdioma getSelectedPreguntaIdiomaThree() {
        return selectedPreguntaIdiomaThree;
    }

    public void setSelectedPreguntaIdiomaThree(PreguntaIdioma selectedPreguntaIdiomaThree) {
        this.selectedPreguntaIdiomaThree = selectedPreguntaIdiomaThree;
    }

    public String getAnwserSecurityThree() {
        return anwserSecurityThree;
    }

    public void setAnwserSecurityThree(String anwserSecurityThree) {
        this.anwserSecurityThree = anwserSecurityThree;
    }

    public List<PreguntaIdioma> getPreguntaIdiomaList() {
        return preguntaIdiomaList;
    }

    public void setPreguntaIdiomaList(List<PreguntaIdioma> preguntaIdiomaList) {
        this.preguntaIdiomaList = preguntaIdiomaList;
    }

    public PreguntaIdioma getPreguntaIdioma(int id) {
        for (PreguntaIdioma preguntaIdioma : preguntaIdiomaList) {
            if (preguntaIdioma.getPreguntaIdiomaId() == id) {
                return preguntaIdioma;
            }
        }
        return null;
    }
    
    public PreguntaIdioma getPreguntaIdiomaTwo(int id) {
        for (PreguntaIdioma preguntaTwo : preguntaIdiomaList) {
            if (preguntaTwo.getPreguntaId() == id) {
                return preguntaTwo;
            }
        }
        return null;
    }

    public PreguntaIdioma getPreguntaIdiomaThree(int id) {
        for (PreguntaIdioma preguntaThree : preguntaIdiomaList) {
            if (preguntaThree.getPreguntaId() == id) {
                return preguntaThree;
            }
        }
        return null;
    }
    
    public void submit() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (selectedPreguntaIdiomaOne != null) {
            try {
            Respuesta respuesta = apiRegistroUnificado.setPreguntasSecretasUsuarioAplicacionMovil("usuarioWS", "passwordWS", 410, String.valueOf(selectedPreguntaIdiomaOne.getPreguntaId()), anwserSecurityOne, String.valueOf(selectedPreguntaIdiomaTwo.getPreguntaId()), anwserSecurityTwo, String.valueOf(selectedPreguntaIdiomaThree.getPreguntaId()), anwserSecurityThree);
            if (respuesta.getCodigoRespuesta().equals("00")) {
               context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Las respuestas del usuario se guardaron correctamente en la BD", null));
           }else{
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se presentó un problema al guardar los datos, por favor intente de nuevo", null));  
           }
            } catch (RemoteException ex) {
                ex.printStackTrace();
                Logger.getLogger(SecurityQuestionController.class.getName()).log(Level.SEVERE, null, ex);      
            }
        }  
    }
    
    public void submit2() {
        addMessage("Welcome to PrimeFaces!!");
    }
 
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
