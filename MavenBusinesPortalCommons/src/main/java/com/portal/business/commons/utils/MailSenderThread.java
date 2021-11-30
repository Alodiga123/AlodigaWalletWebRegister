package com.portal.business.commons.utils;

import com.portal.business.commons.exceptions.GeneralException;


public class MailSenderThread extends Thread {

    private Mail mail;

    public MailSenderThread(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        try {
            this.sendMail(mail);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendMail(Mail mail) throws GeneralException {
        try {
            SendMail.sendMail(mail);
        } catch (Exception ex) {
            throw new GeneralException(ex.getMessage());
        }
    }

}
