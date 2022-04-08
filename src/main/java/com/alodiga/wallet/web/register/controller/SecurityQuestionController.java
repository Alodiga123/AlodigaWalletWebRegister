package com.alodiga.wallet.web.register.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.ericsson.alodiga.ws.PreguntaIdioma;
import com.ericsson.alodiga.ws.APIRegistroUnificadoProxy;
import com.ericsson.alodiga.ws.RespuestaPreguntasSecretas;
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
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.primefaces.event.FlowEvent;
import java.rmi.RemoteException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jesús Gómez
 */
@ManagedBean(name = "securityQuestionController")
@ViewScoped
public class SecurityQuestionController {

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
                Logger.getLogger(SecurityQuestionController.class.getName()).log(Level.SEVERE, null, ex);      
            }
        } catch (NamingException ex) {
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
        for (PreguntaIdioma pregunta : preguntaIdiomaList) {
            if (pregunta.getPreguntaId() == id) {
                return pregunta;
            }
        }
        return null;
    }
    
    
    
    public void saveSecurityQuestion() {        
        if (getSelectedPreguntaIdiomaOne() != null) {
            try {
            apiRegistroUnificado.setPreguntasSecretasUsuarioAplicacionMovil("usuarioWS", "passwordWS", 410, String.valueOf(getSelectedPreguntaIdiomaOne().getPreguntaId()), getAnwserSecurityOne(), String.valueOf(getSelectedPreguntaIdiomaTwo()), getAnwserSecurityTwo(), String.valueOf(getSelectedPreguntaIdiomaThree()), getAnwserSecurityThree());
            } catch (RemoteException ex) {
                ex.printStackTrace();
                Logger.getLogger(SecurityQuestionController.class.getName()).log(Level.SEVERE, null, ex);      
            }
        }            
    }
    
    public void buttonAction() {
        addMessage("Welcome to PrimeFaces!!");
    }
 
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
        

}
