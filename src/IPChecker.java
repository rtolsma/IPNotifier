/**
 * Created by ryan on 6/23/15.
 */
import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;


            public class IPChecker {
                String URL="http://www.whatsmyip.us/showipsimple.php";
                String elementID="ip";


               public String checkIP() {
                   String IP=null;
                   try {
                        WebClient wc = new WebClient();
                        JavaScriptPage page = wc.getPage(URL);
                       System.out.println("Page: "+page.toString());
                       String php=page.getContent();
                       IP= php.substring( php.indexOf("\"")+1, php.lastIndexOf("\"") ) ;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                   //System.out.println("IP is: "+IP);
                        return IP;
                }
            }