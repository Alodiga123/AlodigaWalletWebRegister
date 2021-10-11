package com.portal.business.commons.utils;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class SendMailService {

    private static final String REGISTER_FROM_MAIL = "info@alodiga.com";
    private static final String REGISTER_LINK_URL = "http://44.235.115.116:8080/BusinessPortalWeb/";

    private static final String CARD_PRE_REQUEST_LINK_URL = "http://tajetas.alodiga.com?idRequest=";
    private static final String CARD_PRE_REQUEST_FROM_EMAIL = "businessportal@alodiga.com";

    public static void sendCardPreRequestMail(String to, String fullName, Locale locale) {
        ResourceBundle msg = ResourceBundle.getBundle("com.portal.business.commons.messages.cardPreRequestMailMessages", locale);
        StringBuilder body = new StringBuilder();
        body.append(msg.getString("message")).append("<br><p align=\"left\"><a href='");
        body.append(CARD_PRE_REQUEST_LINK_URL).append("&isEmail").append("'>").append(msg.getString("linkText"));
        body.append("</a></p>");
        sendFormattedMail(CARD_PRE_REQUEST_FROM_EMAIL, to, msg.getString("subject"), fullName, body.toString(), msg);
    }

    public static void sendRegisterSubmitMail(String to, String userFullName, Locale locale) {
        ResourceBundle msg = ResourceBundle.getBundle("com.portal.business.commons.messages.registerMailMessages", locale);
        sendFormattedMail(REGISTER_FROM_MAIL, to, msg.getString("submitSubject"), userFullName, msg.getString("submitMessage"), msg);
    }

    public static void sendRegisterRejectedMail(String to, String userFullName, Locale locale) {
        ResourceBundle msg = ResourceBundle.getBundle("com.portal.business.commons.messages.registerMailMessages", locale);
        sendFormattedMail(REGISTER_FROM_MAIL, to, msg.getString("rejectSubject"), userFullName, msg.getString("rejectMessage"), msg);
    }

    public static void sendRegisterAcceptMail(String to, String userFullName, String user, String password, Locale locale) {
        ResourceBundle msg = ResourceBundle.getBundle("com.portal.business.commons.messages.registerMailMessages", locale);
        StringBuilder body = new StringBuilder();
        body.append(msg.getString("acceptMessage")).append("<br><p align='left'><a href='");
        body.append(REGISTER_LINK_URL).append("'> ").append(msg.getString("acceptMessageLinkText"));
        body.append("</a></p>");
        body.append("<br>").append(msg.getString("acceptMessageUser")).append(user);
        if (password != null && !password.isEmpty()) {
            body.append("<br>").append(msg.getString("acceptMessagePassword")).append(password);
        }
        body.append("<br><br>").append(msg.getString("acceptMessageFooter"));
        sendFormattedMail(REGISTER_FROM_MAIL, to, msg.getString("acceptSubject"), userFullName, body.toString(), msg);
    }

    public static void sendFormattedMail(String fromMail, String toMail, String subject, String userFullName, String message, ResourceBundle msg) {
        StringBuilder body = new StringBuilder();
        body.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/><style type='text/css'>.Estilo11 {font:13px/0.6em Arial,Helvetica,sans-serif,lighter;color: #333333;font-size:13px;font-weight:bold;}.Estilo12{font:13px/0.6em Arial,Helvetica,sans-serif,lighter;color: #666;font-size:13px;}.EstiloColumn {background-color: #555555;color:#FFBF00;font:12px/1.8em Arial,Helvetica,sans-serif,lighter;font-weight:bold;padding-left:10px}.style1{font:13px/0.6em Arial,Helvetica,sans-serif,lighter;color: #666;font-size:13px;}.style2{background-color: #07b49c;color:#ffff;font:16px/1.8em Arial,Helvetica,sans-serif,lighter;font-weight:bold;padding-left:10px'}</style></head><body><div align='center'><table width='756' border='0'><tr><th width='750'<p><img src='http://sales.alodiga.com/images/img-alodiga-logo.png' align='left' width='114' height='90' longdesc='Logo alodiga' alt='img' /></p></th></tr></table><table  width='730' border='0' ><tr><th width='728' height='20' align='right' bgcolor='#283593' style='color:#FFFF;font:16px/1.8em Arial,Helvetica,sans-serif,lighter;'> </th></tr><tr><th width='728' height='5' bgcolor='#232323'></th></tr></table><table width='728' border='0'><tr><th width='728'><p align='left' class='Estilo11'><br>&iexcl;");
        body.append(msg.getString("greetings")).append(" ").append(userFullName);
        body.append("&nbsp;! <br></p></th></tr><tr><th><p align='left' style='font: 16px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; font-weight:bold; display: table;  margin: 0; padding:0;' >");
        body.append(message);
        body.append("</p><br></th></tr><tr height='3px'><th width='728' bgcolor='#232323'></th></tr><tr><th><tr height='3px'><th width='728' bgcolor='#232323'></th></tr><tr height='40px'><th height='40px'><div class='Estilo11' align='left'>");
        body.append(msg.getString("moreInfo"));
        body.append("<span style='font-size: 13px'><a href='https://www.alodiga.com/'> www.alodiga.com</a></span></div></th></tr><tr><th height='31' bordercolor='#999999'><div align='center'><p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;'>");
        body.append(msg.getString("thanks"));
        body.append("</p><p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;'>");
        body.append(msg.getString("messageFooter"));
        body.append("</p></div></th></tr></th></tr></table><div align='center'><p align='center' style='font: 10px/1.8em Arial,Helvetica,sans-serif,lighter ; color: #666; display: table;  margin: 0; padding:0;'>&copy;");
        body.append(msg.getString("copyright"));
        body.append("<br></div></div></body></html>");

        System.out.println("mail to send: " + body.toString());

        Mail mail = new Mail();
        mail.setSubject(subject);
        mail.setFrom(fromMail);
        mail.setBody(body.toString());

        ArrayList<String> recipients = new ArrayList();
        recipients.add(toMail);
        mail.setTo(recipients);

        MailSenderThread sender = new MailSenderThread(mail);
        sender.start();

    }
}
