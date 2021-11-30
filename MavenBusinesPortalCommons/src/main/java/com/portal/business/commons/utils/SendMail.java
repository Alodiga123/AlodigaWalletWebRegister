package com.portal.business.commons.utils;

import com.alodiga.wallet.common.utils.AmazonSESSendMail;
import com.portal.business.commons.exceptions.NullParameterException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public abstract class SendMail {

    private static final String FOLDER = "/home/email/";

    public static void sendMails(List<Mail> mails) throws MessagingException {
        if (mails == null || mails.isEmpty()) {
            throw new MessagingException();
        }
        for (Mail mail : mails) {
            sendMail(mail);
        }
    }

    public static void sendMail(Mail mail) throws MessagingException {
        try {
            if (mail == null) {
                throw new NullParameterException("Parameter mail cannot be null");
            }
            if (mail.getFrom() == null || "".equals(mail.getFrom())) {
                throw new NullParameterException("Parameter mail.getFrom cannot be null");
            }

            if (mail.getTo() == null || mail.getTo().isEmpty()) {
                throw new NullParameterException("Parameter mail.getTo cannot be null");
            }

            for (String to : mail.getTo()) {
                AmazonSESSendMail.SendMail(mail.getSubject(), mail.getBody(), to);
                //sendMailGmail(mail, to);
                //createMessage(to, mail.getFrom(), mail.getSubject(), mail.getBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessagingException(e.getMessage(), e);
        }
    }

    public static void createMessage(String to, String from, String subject, String body) {
        try {
            Message message = new MimeMessage(Session.getInstance(System.getProperties()));
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            // create the message part 
            MimeBodyPart content = new MimeBodyPart();
            // fill message
            content.setContent(body, "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(content);
            // integration
            message.setContent(multipart);
            // store file
            Path folder = Paths.get(FOLDER);
            Path newFile = Files.createTempFile(folder, "mail", "eml");
            message.writeTo(new FileOutputStream(newFile.toFile()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
