package com.alodiga.wallet.web.register.controller;

import com.alodiga.wallet.common.model.BusinessCategory;
import com.alodiga.wallet.common.model.CivilStatus;
import com.alodiga.wallet.common.model.DocumentsPersonType;
import com.alodiga.wallet.common.model.LegalPerson;
import com.alodiga.wallet.common.model.LegalRepresentative;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author henry
 */
@ManagedBean(name = "legalPersonController")
@ViewScoped
public class LegalPersonController {

    private LegalPerson person;

    private DocumentsPersonType selectedDocumentsPersonType;

    private BusinessCategory selectedBusinessCategory;

    private LegalRepresentative representative;

    private DocumentsPersonType selectedRepresentativeDocumentsPersonType;

    private CivilStatus selectedRepresentativeCivilStatus;

    private String businessAddress;

    @PostConstruct
    public void init() {
        person = new LegalPerson();
        representative = new LegalRepresentative();
    }

    public LegalPerson getPerson() {
        return person;
    }

    public void setPerson(LegalPerson person) {
        this.person = person;
    }

    public LegalRepresentative getRepresentative() {
        return representative;
    }

    public void setRepresentative(LegalRepresentative representative) {
        this.representative = representative;
    }

    public DocumentsPersonType getSelectedDocumentsPersonType() {
        return selectedDocumentsPersonType;
    }

    public void setSelectedDocumentsPersonType(DocumentsPersonType selectedDocumentsPersonType) {
        this.selectedDocumentsPersonType = selectedDocumentsPersonType;
        this.person.setDocumentsPersonTypeId(selectedDocumentsPersonType);
    }

    public BusinessCategory getSelectedBusinessCategory() {
        return selectedBusinessCategory;
    }

    public void setSelectedBusinessCategory(BusinessCategory selectedBusinessCategory) {
        this.selectedBusinessCategory = selectedBusinessCategory;
        this.person.setBusinessCategoryId(selectedBusinessCategory);
    }

    public DocumentsPersonType getSelectedRepresentativeDocumentsPersonType() {
        return selectedRepresentativeDocumentsPersonType;
    }

    public void setSelectedRepresentativeDocumentsPersonType(DocumentsPersonType selectedRepresentativeDocumentsPersonType) {
        this.selectedRepresentativeDocumentsPersonType = selectedRepresentativeDocumentsPersonType;
        this.representative.setDocumentsPersonTypeId(selectedRepresentativeDocumentsPersonType);
    }

    public CivilStatus getSelectedRepresentativeCivilStatus() {
        return selectedRepresentativeCivilStatus;
    }

    public void setSelectedRepresentativeCivilStatus(CivilStatus selectedRepresentativeCivilStatus) {
        this.selectedRepresentativeCivilStatus = selectedRepresentativeCivilStatus;
        this.representative.setCivilStatusId(selectedRepresentativeCivilStatus);
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

}
