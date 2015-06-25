
import com.sun.mail.smtp.SMTPMessage;
import com.sun.mail.smtp.SMTPSSLTransport;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;

/**
 * Created by ryan on 6/21/15
 * Decided to use external party mail api
 */
public class SMTP {

    int port;
    String host, subject, rcpt, sender, temp, pass, username;
    String[] data, commandArray, smtpArray;
    Runtime runtime;
    ProcessBuilder builder;
    Process smtp;
    PrintWriter wr;
    BufferedReader rd, error;
    ArrayList<String>  outputLog=new ArrayList<String>();
    Properties props;
    SMTPMessage msg;
    Session session;
    // boolean finished=false;

        public SMTP(String subject, String rcpt, String sender, String[] data, String username, String pass) {
          this("smtp.gmail.com", 587, subject, rcpt, sender, data, username, pass);
        }


    /**
     *
     * @param host The email server
     * @param port The port to connect to
     * @param subject Message Subject
     * @param rcpt The username of recipient
     * @param sender Name of Sender
     * @param data An array of Strings for the message
     * @param username The email address of the User
     * @param pass The password of the User
     */

    public SMTP(String host, int port, String subject, String rcpt, String sender, String[] data, String username, String pass) {
        this.host=host;
        this.port=port;
        this.subject=subject;
        this.rcpt=rcpt;
        this.sender=sender;
        this.username=username;
        this.pass=pass;
        this.data=new String[data.length];
        props=new Properties();


        for(int i=0; i<data.length; i++) this.data[i]=data[i];
      

    }

    public void send() {
        try {
              // props.put("mail.smpt.auth.mechanisms" , "PLAIN");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "" + port);
  //          props.put("mail.smtp.user", username);
//            props.put("mail.smtp.password", pass);

            session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, pass);
                }

            });
            // session=Session.getDefaultInstance(props);

            msg = new SMTPMessage(session);
            msg.setFrom(new InternetAddress(sender));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(rcpt));
            msg.setSubject(subject);
            temp="";
            for(int i=0; i<data.length; i++) temp+=data[i]+"\n";
            msg.setText(temp);
            System.out.println("Sending: " +msg.toString());
            SMTPSSLTransport.send(msg);
            System.out.println("SUCCESS");

        }catch (MessagingException e) {
                System.out.println("Error: Exception in sending IP changed Notification");
            e.printStackTrace();
        }





    }
}