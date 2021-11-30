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
import com.alodiga.wallet.common.model.PhoneType;
import com.alodiga.wallet.common.model.Profession;
import com.alodiga.wallet.common.model.RequestType;
import com.alodiga.wallet.common.model.State;
import com.alodiga.wallet.common.model.StatusApplicant;
import com.alodiga.wallet.common.utils.EjbConstants;
import com.alodiga.wallet.common.utils.S3cur1ty3Cryt3r;
import com.alodiga.wallet.ws.APIAlodigaWalletProxy;
import com.alodiga.wallet.ws.DocumentPersonTypeListResponse;
import com.ericsson.alodiga.ws.APIRegistroUnificadoProxy;
import com.ericsson.alodiga.ws.RespuestaCodigoRandom;
import com.ericsson.alodiga.ws.RespuestaGuardarUsuario;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;


/**
 *
 * @author henry
 */
@ManagedBean(name = "registerController")
@ViewScoped
public class RegisterController {

    private List<Country> countries = new ArrayList();

    

    //Business Info
    private Country selectedCountry;

    

    private static BusinessPortalEJB proxy;

    private String countryPhoneCode;
    private String areaCode;
    private String numberPhone;
    private Boolean sendSms;
    private String token;
    private String codeToken;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String passwordOperation;
    private int number;
    private List<com.alodiga.wallet.ws.DocumentsPersonType> documentsPersonTypes;
    private com.alodiga.wallet.ws.DocumentsPersonType selectedDocumentsPersonType;
    private Integer documentNumber;
    private Integer documentsPersonTypeId;

    

    private static final String PORTAL_ORIGIN_CODE = "PORNEG";

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
            proxy = (BusinessPortalEJB) intialContext.lookup(EjbConstants.BUSINESS_PORTAL_EJB);
            try {                
                List<Country> countryList = proxy.getCountries();

                for (Country country : countryList) {
                    try {
                        countries.add(country);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (EmptyListException ex) {
                countries = new ArrayList();
            } catch (GeneralException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullParameterException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (NamingException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    

    //Moises/////////////////////
    public Country getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Country selectedCountry) {
        APIAlodigaWalletProxy proxy = new APIAlodigaWalletProxy();
        this.selectedCountry = selectedCountry;
        this.documentsPersonTypes = null;
        this.sendSms = false;
        try {
            if (selectedCountry != null) {
                this.countryPhoneCode = selectedCountry.getCode();
                DocumentPersonTypeListResponse doc = proxy.getDocumentPersonTypeByCountry(selectedCountry.getId(), 3);
                documentsPersonTypes = Arrays.asList(doc.getDocumentsPersonTypes());
                System.out.println("documens " + documentsPersonTypes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getCountryPhoneCode() {
        return countryPhoneCode;
    }

    public void setCountryPhoneCode(String countryPhoneCode) {
        this.countryPhoneCode = countryPhoneCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        this.sendSms = false;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
        this.sendSms = false;
    }

    public String goNext() {
        System.out.println("confirm");
        return "confirm";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCodeToken() {
        return codeToken;
    }

    public void setCodeToken(String codeToken) {
        this.codeToken = codeToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPasswordOperation() {
        return passwordOperation;
    }

    public void setPasswordOperation(String passwordOperation) {
        this.passwordOperation = passwordOperation;
    }

    public void increment() {
        number++;
    }

    public int getNumber() {
        return number;
    }

    public List<com.alodiga.wallet.ws.DocumentsPersonType> getDocumentsPersonTypes() {        
        return documentsPersonTypes;
    }

    public void setDocumentsPersonTypes(List<com.alodiga.wallet.ws.DocumentsPersonType> documentsPersonTypes) {
        this.documentsPersonTypes = documentsPersonTypes;
    }

    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    //Moises//////////
    

    public String onFlowProcess(FlowEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext request = RequestContext.getCurrentInstance();
        RespuestaCodigoRandom resp = null;

        switch (event.getOldStep()) {
            case "location":
                if (!sendSms) {
                    APIRegistroUnificadoProxy unificadoProxy = new APIRegistroUnificadoProxy();
                    String number = countryPhoneCode + areaCode + numberPhone;
                    try {
                        resp = unificadoProxy.generarCodigoMovilSMS("usuarioWS", "passwordWS", number);
                        codeToken = resp.getDatosRespuesta();
                        sendSms = true;                        
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SMS enviado con éxito", null));
                        request.update("registerForm:growl");            

                    } catch (Exception e) {
                        e.printStackTrace();
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "NO SE  envió el SMS ", null));
                        request.update("registerForm:growl");
                        return "location";
                    }

                }
                break;
            case "Confirmation":
                if (!sendSms) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo enviar el Mensaje"));
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERORR", null));
                    request.update("registerForm:growl");
                    return "location";
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ENTRO CONFIRM", null));
                    request.update("registerForm:growl");
                    return "token";
                }
            case "token":
                System.out.println("tojken " + token);
                System.out.println("resp " + codeToken);
                if (token.equals(codeToken)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "token valido", null));
                    request.update("registerForm:growl");
                    return "Confirmation1";
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "token invalido", null));
                    request.update("registerForm:growl");
                    return "location";
                }

        }
        return event.getNewStep();

    }

    

    public Country getCountry(long id) {
        for (Country country : countries) {
            if (country.getId() == id) {
                return country;
            }
        }
        return null;
    }

    

    public com.alodiga.wallet.ws.DocumentsPersonType getDocumentsPersonType(long id) {
        System.out.println("ANTES DEL FOR");
        for (com.alodiga.wallet.ws.DocumentsPersonType documentPersonType : documentsPersonTypes) {
            if (documentPersonType.getId() == id) {
                documentsPersonTypeId = documentPersonType.getId();
                return documentPersonType;
            }
        }
        return null;
    }

    

   public void submit() {
       
       APIRegistroUnificadoProxy proxy = new APIRegistroUnificadoProxy();
       FacesContext context = FacesContext.getCurrentInstance();
       try {
           String number = countryPhoneCode + areaCode + numberPhone; 
           String pass = S3cur1ty3Cryt3r.aloDesencript(password, "1nt3r4xt3l3ph0ny", null, "DESede", "0123456789ABCDEF");
           String passOperation = S3cur1ty3Cryt3r.aloDesencript(passwordOperation, "1nt3r4xt3l3ph0ny", null, "DESede", "0123456789ABCDEF");
           RespuestaGuardarUsuario guardar = proxy.guardarUsuarioAplicacionMovil
            ("usuarioWS", "passwordWS", "", 
             firstName, lastName, pass, 
             email, number, "21-07-1988", 
             "APP_WEB", String.valueOf(selectedCountry.getId()), "1", 
             "1", "1", "1010", 
             token, "AloCash WEB", null, 
             "AloCash WEB", passOperation, documentsPersonTypeId, String.valueOf(documentNumber));                                      
           if (guardar.getCodigoRespuesta().equals("00")) {
               context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "EL usuario fue guardado exitosamente", null));
           }else{
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "EL usuario no pudo ser guardado", null));  
           }
           
           //System.out.println("respuesta guardar usuario id" + guardar.getDatosRespuesta().getUsuarioID());
           System.out.println("codigo guardar usuario " + guardar.getCodigoRespuesta());
           System.out.println("descripcion guardar usuario " + guardar.getMensajeRespuesta() );
       } catch (Exception e) {
           e.printStackTrace();
           System.out.println("ENTRO EN EL CATCH");
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo guardar el usuario ", null));
       }
       
       
       
//        StatusApplicant statusApplicant;
//        RequestType requestType;
//        try {
//            statusApplicant = proxy.getStatusApplicant(new EJBRequest()).get(0);
//            requestType = proxy.loadRequestTypeByCode(RequestTypeE.SOAFNE.getRequestTypeCode());
//        } catch (RegisterNotFoundException | EmptyListException | GeneralException | NullParameterException ex) {
//            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
//            return;
//        }
//
//        PhoneType phoneType = null;
//        try {
//            phoneType = proxy.getPhoneType(new EJBRequest()).get(0);
//        } catch (EmptyListException | GeneralException | NullParameterException ex) {
//            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
//            return;
//        }
//        
//
//        this.businessPerson.getPhonePerson().setPhoneTypeId(phoneType);
//        this.businessPerson.getPhonePerson().setPersonId(businessPerson);
//
//        if (selectedPersonType.getIndNaturalPerson()) {
//            naturalPersonController.getPerson().setStatusApplicantId(statusApplicant);
//            this.businessPerson.setNaturalPerson(naturalPersonController.getPerson());
//            this.naturalPersonController.getPerson().setPersonId(businessPerson);
//            try {
//                AffiliationRequest request = proxy.saveNaturalPersonAffiliationRequest(businessPerson, naturalPersonController.getPerson(), requestType, businessPerson.getPhonePerson(), address);
//                //saveBusinessAffiliationRequest(businessPerson, naturalPersonController.getPerson(), null, businessPerson.getPhonePerson(), address);                
//                FacesContext.getCurrentInstance().getExternalContext().redirect("loadCollectionRequest.xhtml?i=" + request.getId());
//            } catch (NullParameterException | GeneralException | IOException ex) {
//                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } else {
//            legalPersonController.getPerson().setStatusApplicantId(statusApplicant);
//            this.businessPerson.setLegalPerson(legalPersonController.getPerson());
//            legalPersonController.getPerson().setPersonId(businessPerson);
//            legalPersonController.getRepresentative().setStatusApplicantId(statusApplicant);
//            try {
//                AffiliationRequest request = proxy.saveLegalPersonAffiliationRequest(businessPerson, legalPersonController.getPerson(), requestType, businessPerson.getPhonePerson(), address, legalPersonController.getRepresentative());
//                //AffiliationRequest request = proxy.saveBusinessAffiliationRequest(businessPerson, null, legalPersonController.getPerson(), businessPerson.getPhonePerson(), address);              
//                FacesContext.getCurrentInstance().getExternalContext().redirect("loadCollectionRequest.xhtml?i=" + request.getId());
//            } catch (NullParameterException | GeneralException | IOException ex) {
//                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    public com.alodiga.wallet.ws.DocumentsPersonType getSelectedDocumentsPersonType() {
        return selectedDocumentsPersonType;
    }

    public void setSelectedDocumentsPersonType(com.alodiga.wallet.ws.DocumentsPersonType selectedDocumentsPersonType) {
        System.out.println("Entro en el set selected documents");
        this.selectedDocumentsPersonType = selectedDocumentsPersonType;
    }

}
