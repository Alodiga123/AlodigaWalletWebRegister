package com.alodiga.wallet.web.register.controller;

import com.alodiga.wallet.common.model.CivilStatus;
import com.alodiga.wallet.common.model.DocumentsPersonType;
import com.alodiga.wallet.common.model.NaturalPerson;
import com.alodiga.wallet.common.model.Profession;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author henry
 */
@ManagedBean(name = "naturalPersonController")
@ViewScoped
public class NaturalPersonController {

    private NaturalPerson person;

    private DocumentsPersonType selectedDocumentsPersonType;

    private CivilStatus selectedCivilStatus;

    private Profession selectedProfession;

    @PostConstruct
    public void init() {
        person = new NaturalPerson();
    }

    public NaturalPerson getPerson() {
        return person;
    }

    public void setPerson(NaturalPerson person) {
        this.person = person;
    }

    public DocumentsPersonType getSelectedDocumentsPersonType() {
        return selectedDocumentsPersonType;
    }

    public void setSelectedDocumentsPersonType(DocumentsPersonType selectedDocumentsPersonType) {
        this.selectedDocumentsPersonType = selectedDocumentsPersonType;
        this.person.setDocumentsPersonTypeId(selectedDocumentsPersonType);
    }

    public CivilStatus getSelectedCivilStatus() {
        return selectedCivilStatus;
    }

    public void setSelectedCivilStatus(CivilStatus selectedCivilStatus) {
        this.selectedCivilStatus = selectedCivilStatus;
        this.person.setCivilStatusId(selectedCivilStatus);
    }

    public Profession getSelectedProfession() {
        return selectedProfession;
    }

    public void setSelectedProfession(Profession selectedProfession) {
        this.selectedProfession = selectedProfession;
        this.person.setProfessionId(selectedProfession);
    }

}
