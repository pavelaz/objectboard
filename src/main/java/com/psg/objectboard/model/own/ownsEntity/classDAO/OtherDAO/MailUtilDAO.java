package com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO;

import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.MailSendVO;
import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailUtilDAO {
    // for example, smtp.mailgun.org
    private static String emailFrom;
    private static String emailToCc;
    private static String emailToBcc;
    private static String emailSubject;
    private static String emailText;
    private static String emailRutarch;

    public boolean sendMail(MailSendVO recip) throws Exception {

        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host",recip.getSMTP_SERVER()); //optional, defined in SMTPTransport
        properties.put("mail.smtp.port", "587"); // default port 25
        try {
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication( recip.getUSERNAME(), recip.getPASSWORD());
                }
            });
            this.emailFrom = recip.getEMAIL_FROM();
            this.emailToCc = recip.getEMAIL_TO_CC();
            this.emailToBcc = recip.getEMAIL_TO_BCC();
            this.emailSubject = recip.getEMAIL_SUBJECT();
            this.emailText = recip.getEMAIL_TEXT();
            this.emailRutarch = recip.getEMAIL_RUTARCH();
            Message message = prepareMessage(session, recip.getUSERNAME(), recip.getEMAIL_TO());
            Transport.send(message);
            System.out.println("Message sent successfully");
            return true;
        }catch (Exception e) {
            System.out.println("Error"+e);
            return false;
        }
    }


    private static Message prepareMessage(Session session, String USERNAME, String recepient){

        try {
            Message message = new MimeMessage(session);

            // from
            message.setFrom(new InternetAddress(emailFrom));

            // to
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));

            // cc
            message.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(emailToCc, false));
            // bcc
            message.setRecipients(Message.RecipientType.BCC,
                    InternetAddress.parse(emailToBcc, false));

            // subject
            message.setSubject(emailSubject);

            // content
            message.setContent(emailText, "text/html"); // formato del texto a incluir es texto o html
            // message.setText(emailText); // formato del texto a incluir es solo texto

            message.setSentDate(new Date());
            ///
            if (!emailRutarch.equals("")) {
                // text
                MimeBodyPart p1 = new MimeBodyPart();
                //p1.setText(emailText);  // formato del texto a incluir es solo texto
                p1.setContent(emailText, "text/html"); // formato del texto a incluir es texto o html

                // file
                MimeBodyPart p2 = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(emailRutarch);
                p2.setDataHandler(new DataHandler(fds));
                p2.setFileName(fds.getName());

                Multipart mp = new MimeMultipart();
                mp.addBodyPart(p1);
                mp.addBodyPart(p2);

                message.setContent(mp);


                SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            }
            ///

            /*

            // Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            // connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);

            // send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();*/
            return message;

        } catch (Exception e) {
            Logger.getLogger(MailUtilDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;

    }

}