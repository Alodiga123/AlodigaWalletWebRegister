package com.alodiga.wallet.web.register.controller;

import com.alodiga.wallet.common.ejb.BusinessPortalEJB;
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

/**
 *
 * @author henry
 */
@ManagedBean(name = "registerController")
@ViewScoped
public class RegisterController {

    private List<Country> countries = new ArrayList();

    private List<State> states;

    private List<City> cities;

    private List<PersonType> personTypes;

    private List<DocumentsPersonType> documentPersonTypes;

    private List<CollectionType> collectionTypes;

    private List<CollectionsRequest> collectionRequests;

    private List<CivilStatus> listCivilStatus;

    private List<Profession> professions;

    private List<BusinessCategory> businessCategories;

    private List<DocumentsPersonType> naturalPersonDocumentsPersonTypes;

    //Business Info
    private Country selectedCountry;

    private State selectedState;

    private City selectedCity;

    private PersonType selectedPersonType;

    private Person businessPerson;

    private Address address;

    private static BusinessPortalEJB proxy;

    @ManagedProperty(value = "#{legalPersonController}")
    private LegalPersonController legalPersonController;

    @ManagedProperty(value = "#{naturalPersonController}")
    private NaturalPersonController naturalPersonController;

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
                listCivilStatus = proxy.getCivilStatus(new EJBRequest());
                professions = proxy.getProfession(new EJBRequest());
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

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public List<PersonType> getPersonTypes() {
        return personTypes;
    }

    public void setPersonTypes(List<PersonType> personTypes) {
        this.personTypes = personTypes;
    }

    public List<DocumentsPersonType> getDocumentPersonTypes() {
        return documentPersonTypes;
    }

    public void setDocumentPersonTypes(List<DocumentsPersonType> documentPersonTypes) {
        this.documentPersonTypes = documentPersonTypes;
    }

    public List<CollectionType> getCollectionTypes() {
        return collectionTypes;
    }

    public void setCollectionTypes(List<CollectionType> collectionTypes) {
        this.collectionTypes = collectionTypes;
    }

    public List<CollectionsRequest> getCollectionRequests() {
        return collectionRequests;
    }

    public void setCollectionRequests(List<CollectionsRequest> collectionRequests) {
        this.collectionRequests = collectionRequests;
    }

    public Country getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(Country selectedCountry) {
        this.selectedCountry = selectedCountry;
        this.selectedState = null;
        this.selectedCity = null;
        this.selectedPersonType = null;
        this.states = new ArrayList();
        this.cities = new ArrayList();
        this.personTypes = new ArrayList();
        this.documentPersonTypes = new ArrayList();
        this.collectionTypes = new ArrayList();
        this.collectionRequests = new ArrayList();

        if (selectedCountry != null) {
            
            selectedCountry.getCode();

            try {
                states = proxy.getStatesByCountryId(selectedCountry.getId());
            } catch (EmptyListException | GeneralException | NullParameterException ex) {
                states = new ArrayList();
            }

            try {
                List<PersonType> types = proxy.getPersonTypesBycountryId(selectedCountry.getId());
                for (PersonType personType : types) {
                    if (personType.getOriginApplicationId().getCode().equals(PORTAL_ORIGIN_CODE)) {
                        personTypes.add(personType);
                    }
                }
            } catch (EmptyListException | GeneralException | NullParameterException ex) {
                personTypes = new ArrayList();
            }

            try {
                collectionTypes = proxy.getCollectionTypesBycountryId(selectedCountry.getId());
            } catch (EmptyListException | GeneralException | NullParameterException ex) {
                collectionTypes = new ArrayList();
            }

            businessPerson.getPhonePerson().setCountryId(selectedCountry);
            businessPerson.getPhonePerson().setIndMainPhone(true);
            businessPerson.setCountryId(selectedCountry);
            address.setCountryId(selectedCountry);

        } else {
            this.states = new ArrayList();
            this.personTypes = new ArrayList();
        }
    }

    public State getSelectedState() {
        return selectedState;
    }

    public void setSelectedState(State selectedState) {
        this.selectedState = selectedState;
        this.selectedCity = null;
        this.cities = new ArrayList();

        if (selectedState != null) {
            try {
                cities = proxy.getCitiesByStateId(selectedState.getId());

            } catch (EmptyListException | GeneralException | NullParameterException ex) {
                cities = new ArrayList();
            }
        }

    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
        this.address.setCityId(selectedCity);
    }

    public PersonType getSelectedPersonType() {
        return selectedPersonType;
    }

    public void setSelectedPersonType(PersonType selectedPersonType) {
        this.selectedPersonType = selectedPersonType;
        this.documentPersonTypes = new ArrayList();
        if (selectedPersonType != null) {
            try {
                documentPersonTypes = proxy.getDocumentPersonTypesBypersonTypeId((long) selectedPersonType.getId());
                this.businessPerson.setPersonTypeId(selectedPersonType);
                if (!selectedPersonType.getIndNaturalPerson()) {
                    naturalPersonDocumentsPersonTypes = new ArrayList();
                    for (PersonType personType : personTypes) {
                        if (personType.getIndNaturalPerson()) {
                            try {
                                naturalPersonDocumentsPersonTypes.addAll(proxy.getDocumentPersonTypesBypersonTypeId((long) personType.getId()));
                            } catch (Exception ignored) {
                            }
                        }
                    }
                }
            } catch (EmptyListException | GeneralException | NullParameterException ex) {
                documentPersonTypes = new ArrayList();
            }
        }
    }

    public List<CivilStatus> getListCivilStatus() {
        return listCivilStatus;
    }

    public void setListCivilStatus(List<CivilStatus> listCivilStatus) {
        this.listCivilStatus = listCivilStatus;
    }

    public Person getBusinessPerson() {
        return businessPerson;
    }

    public void setBusinessPerson(Person businessPerson) {
        this.businessPerson = businessPerson;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public void setProfessions(List<Profession> professions) {
        this.professions = professions;
    }

    public List<BusinessCategory> getBusinessCategories() {
        return businessCategories;
    }

    public void setBusinessCategories(List<BusinessCategory> businessCategories) {
        this.businessCategories = businessCategories;
    }

    public List<DocumentsPersonType> getNaturalPersonDocumentsPersonTypes() {
        return naturalPersonDocumentsPersonTypes;
    }

    public void setNaturalPersonDocumentsPersonTypes(List<DocumentsPersonType> naturalPersonDocumentsPersonTypes) {
        this.naturalPersonDocumentsPersonTypes = naturalPersonDocumentsPersonTypes;
    }

    public String onFlowProcess(FlowEvent event) {
        switch (event.getOldStep()) {
            case "location":
                return selectedPersonType.getIndNaturalPerson() ? "naturalPerson" : "legalPerson";
            case "naturalPerson":
                if (!event.getNewStep().equals("location")) {
                    return "address";
                }
                break;
            case "legalPerson":
                if (event.getNewStep().equals("naturalPerson")) {
                    return "location";
                }
                break;
            case "address":
                if (event.getNewStep().equals("legalPerson2") && selectedPersonType.getIndNaturalPerson()) {
                    return "naturalPerson";
                }
        }
        return event.getNewStep();
    }

    public Date getMaxBirthdate() {
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.YEAR, -18);
        return cal.getTime();
    }

    public Date getMaxRegistationDate() {
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.DAY_OF_YEAR, false);
        return cal.getTime();
    }

    public Country getCountry(long id) {
        for (Country country : countries) {
            if (country.getId() == id) {
                return country;
            }
        }
        return null;
    }

    public State getState(long id) {
        for (State state : states) {
            if (state.getId() == id) {
                return state;
            }
        }
        return null;
    }

    public City getCity(long id) {
        for (City city : cities) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }

    public DocumentsPersonType getDocumentsPersonType(long id) {
        for (DocumentsPersonType documentPersonType : documentPersonTypes) {
            if (documentPersonType.getId() == id) {
                return documentPersonType;
            }
        }
        return null;
    }

    public DocumentsPersonType getNaturalPersonDocumentsPersonType(long id) {
        for (DocumentsPersonType documentPersonType : naturalPersonDocumentsPersonTypes) {
            if (documentPersonType.getId() == id) {
                return documentPersonType;
            }
        }
        return null;
    }

    public CivilStatus getCivilStatus(long id) {
        for (CivilStatus civilStatus : listCivilStatus) {
            if (civilStatus.getId() == id) {
                return civilStatus;
            }
        }
        return null;
    }

    public PersonType getPersonType(long id) {
        for (PersonType personType : personTypes) {
            if (personType.getId() == id) {
                return personType;
            }
        }
        return null;
    }

    public Profession getProfession(long id) {
        for (Profession profession : professions) {
            if (profession.getId() == id) {
                return profession;
            }
        }
        return null;
    }

    public BusinessCategory getBusinessCategory(long id) {
        for (BusinessCategory category : businessCategories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public void setLegalPersonController(LegalPersonController legalPersonController) {
        this.legalPersonController = legalPersonController;
    }

    public void setNaturalPersonController(NaturalPersonController naturalPersonController) {
        this.naturalPersonController = naturalPersonController;
    }

    public void submit() {
        StatusApplicant statusApplicant;
        RequestType requestType;
        try {
            statusApplicant = proxy.getStatusApplicant(new EJBRequest()).get(0);
            requestType = proxy.loadRequestTypeByCode(RequestTypeE.SOAFNE.getRequestTypeCode());
        } catch (RegisterNotFoundException | EmptyListException | GeneralException | NullParameterException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        PhoneType phoneType = null;
        try {
            phoneType = proxy.getPhoneType(new EJBRequest()).get(0);
        } catch (EmptyListException | GeneralException | NullParameterException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        try {
            address.setAddressTypeId(proxy.getAddressType(new EJBRequest()).get(0));
            address.setEdificationTypeId(proxy.getEdificationType(new EJBRequest()).get(0));
            address.setStreetTypeId(proxy.getStreetType(new EJBRequest()).get(0));
            address.setIndMainAddress(true);
        } catch (EmptyListException | GeneralException | NullParameterException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        this.businessPerson.getPhonePerson().setPhoneTypeId(phoneType);
        this.businessPerson.getPhonePerson().setPersonId(businessPerson);

        if (selectedPersonType.getIndNaturalPerson()) {
            naturalPersonController.getPerson().setStatusApplicantId(statusApplicant);
            this.businessPerson.setNaturalPerson(naturalPersonController.getPerson());
            this.naturalPersonController.getPerson().setPersonId(businessPerson);
            try {
                AffiliationRequest request = proxy.saveNaturalPersonAffiliationRequest(businessPerson, naturalPersonController.getPerson(), requestType, businessPerson.getPhonePerson(), address);
                //saveBusinessAffiliationRequest(businessPerson, naturalPersonController.getPerson(), null, businessPerson.getPhonePerson(), address);                
                FacesContext.getCurrentInstance().getExternalContext().redirect("loadCollectionRequest.xhtml?i=" + request.getId());
            } catch (NullParameterException | GeneralException | IOException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            legalPersonController.getPerson().setStatusApplicantId(statusApplicant);
            this.businessPerson.setLegalPerson(legalPersonController.getPerson());
            legalPersonController.getPerson().setPersonId(businessPerson);
            legalPersonController.getRepresentative().setStatusApplicantId(statusApplicant);
            try {
                AffiliationRequest request = proxy.saveLegalPersonAffiliationRequest(businessPerson, legalPersonController.getPerson(), requestType, businessPerson.getPhonePerson(), address, legalPersonController.getRepresentative());
                //AffiliationRequest request = proxy.saveBusinessAffiliationRequest(businessPerson, null, legalPersonController.getPerson(), businessPerson.getPhonePerson(), address);              
                FacesContext.getCurrentInstance().getExternalContext().redirect("loadCollectionRequest.xhtml?i=" + request.getId());
            } catch (NullParameterException | GeneralException | IOException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
