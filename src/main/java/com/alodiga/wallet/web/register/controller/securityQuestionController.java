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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.primefaces.event.FlowEvent;
import com.ericsson.alodiga.ws.PreguntaIdioma;
import com.ericsson.alodiga.ws.APIRegistroUnificadoProxy;
import com.ericsson.alodiga.ws.PreguntaSecreta;
import com.ericsson.alodiga.ws.RespuestaPreguntasSecretas;
import java.rmi.RemoteException;

/**
 *
 * @author henry
 */
@ManagedBean(name = "securityQuestionController")
@ViewScoped
public class securityQuestionController {

    private RespuestaPreguntasSecretas securityQuestionList;
    private List<PreguntaIdioma> preguntaIdiomaList = new ArrayList();
    private PreguntaIdioma preguntaIdiomaOne;
    private PreguntaIdioma preguntaIdiomaTwo;
    private PreguntaIdioma selectedPreguntaIdiomaOne;
    private PreguntaIdioma selectedPreguntaIdiomaTwo;
    private String anwserSecurityOne;
    private String anwserSecurityTwo;
    private static APIRegistroUnificadoProxy apiRegistroUnificado;

    @PostConstruct
    public void init() {
        try {
            Properties props = new Properties();
            props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "alodiga.wallet.admin");
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext intialContext = new InitialContext(props);
            apiRegistroUnificado = new APIRegistroUnificadoProxy();
            
            try {
                RespuestaPreguntasSecretas securityQuestionList = apiRegistroUnificado.getPreguntasSecretas("usuarioWS", "passwordWS", 4);
                for (PreguntaIdioma preguntas: securityQuestionList.getDatosRespuesta()) {
                    preguntaIdiomaList.add(preguntas);
                }
            
            } catch (RemoteException ex) {
                Logger.getLogger(securityQuestionController.class.getName()).log(Level.SEVERE, null, ex);      
            }
        } catch (NamingException ex) {
            Logger.getLogger(securityQuestionController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void setSelectedPreguntaIdiomaOne(PreguntaIdioma selectedSecurityQuestion) {
        this.selectedPreguntaIdiomaOne = selectedSecurityQuestion;
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

    public List<PreguntaIdioma> getPreguntaIdiomaList() {
        return preguntaIdiomaList;
    }

    public void setPreguntaIdiomaList(List<PreguntaIdioma> preguntaIdiomaList) {
        this.preguntaIdiomaList = preguntaIdiomaList;
    }

    public PreguntaIdioma getPreguntaIdioma(int id) {
        for (PreguntaIdioma pregunta : preguntaIdiomaList) {
            if (pregunta.getPreguntaId() == id) {
                return pregunta;
            }
        }
        return null;
    }
    
    public void submit() {
        try {
            apiRegistroUnificado.setPreguntasSecretasUsuarioAplicacionMovil("usuarioWS", "passwordWS", 410, String.valueOf(getSelectedPreguntaIdiomaOne().getPreguntaId()), getAnwserSecurityOne(), String.valueOf(getSelectedPreguntaIdiomaTwo()), getAnwserSecurityTwo(), null, null);
        } catch (RemoteException ex) {
            Logger.getLogger(securityQuestionController.class.getName()).log(Level.SEVERE, null, ex);      
        }
    }
        

}
