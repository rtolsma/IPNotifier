import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ryan on 6/23/15.
 * <p/>
 * Need user to input their email account, password
 */


public class Input {


    long now =0;
    long timeInterval=1000*60*60*6; //6 hours
    boolean oneDay() {

        if ((System.currentTimeMillis() - now) / (timeInterval) > 1) {
            now = System.currentTimeMillis();
            return true;
        }
        return false;

    }


    public static void main(String[] args) {
        final String FILENAME = "IPAddress";
        IPChecker ch = new IPChecker();
        SMTP smtp;
        Utils u = new Utils();
        String user, pass, rcpt, host;
        Input i = new Input();
        String ip;
        String[] data = new String[1];

        if (args.length > 3) {
            user = args[0];
            pass = args[1];
            rcpt=args[2];
            host=args[3];
            System.out.println("User: " + user + "\tPass: " + pass+ " Recipient: "+rcpt+ " Host: "+host);

            while (true) {

                if (i.oneDay()) {
                    ip = ch.checkIP();
                    System.out.println(ip);
                    if (!ip.equals(u.readData(FILENAME)) && ip != null) {
                        data[0] = "Your ip has been changed to: " + ip;
                        smtp = new SMTP(host, 587, "Your IP has Changed", rcpt, user, data, user, pass);
                        smtp.send();

                        u.writeData(ip, FILENAME);
                    }

                    try {
                        Thread.sleep(i.timeInterval); //one day

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }


        } else {
            System.out.println("Must have arguments in format \"Password\" \"Username\" \"Recipient\" \"Email Server\"");

        }


    }


}