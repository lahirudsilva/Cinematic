/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Lahiru De silva
 */
public class MailClass {
     private final String companyEmail = "thecinematicofficial@gmail.com"; //company email
    private final String companyPassword = "cinematic123"; //email password
    private final String gmailHost = "smtp.gmail.com"; //google smtp mail server
    private Properties propertyFile;
    private Session theSession;
    private Authenticator theAuthenticator;

    private static final MailClass theMail = new MailClass();

    private MailClass() {
        try {
             propertyFile = System.getProperties(); //set the properties required
            propertyFile.put("mail.smtp.host", gmailHost); //set mail host as gmail
            propertyFile.put("mail.smtp.port", "465"); //set port used to communicate
            propertyFile.put("mail.smtp.ssl.enable", "true"); //enable ssl communication
            propertyFile.put("mail.smtp.auth", "true"); //enable authentication of company account


            theAuthenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(companyEmail, companyPassword); //retrieve an authentication token to the company gmail address
                }
            };

            theSession = Session.getInstance(propertyFile, theAuthenticator); //retrieve a session instance for the company gmail account
            theSession.setDebug(true);
        } catch (Exception ex) {
            System.out.println("Mail Error: " + ex);
        }
    }

    public void sendMail(String header, String body, String recepientEmail) {
        try {
            MimeMessage theMessage = new MimeMessage(theSession); //create a new message using MimeMessage (Derived from message class)
            theMessage.setFrom(companyEmail); //set the message sender as the company gmail account
            theMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recepientEmail)); //set reciepent (user email address)
            theMessage.setSubject(header); //set the header of the message
            theMessage.setText(body); //set the content of the message

            Transport.send(theMessage); //send the message to the recipient
        } catch (Exception ex) {
            System.out.println("Mail Sending Error: " + ex);
        }
    }

    public static MailClass getMailInstance() {
        return theMail; //static method implemented to ensure only one instance of mail class is used
    }
}
