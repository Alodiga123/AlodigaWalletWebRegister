package com.portal.business.commons.utils;

import com.alodiga.wallet.ws.APIAlodigaWalletProxy;
import java.rmi.RemoteException;

/**
 *
 * @author henry
 */
public class SendSmsThread extends Thread {

    private final String phone;
    private final String message;

    public SendSmsThread(String phone, String message) {
        this.phone = phone;
        this.message = message;
    }

    public void run() {
        APIAlodigaWalletProxy proxy = new APIAlodigaWalletProxy();
        try {
            proxy.sendSMS(phone, message);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
