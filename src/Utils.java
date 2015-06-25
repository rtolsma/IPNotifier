/**
 * Created by ryan on 6/23/15.
 */

import java.awt.event.InputEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 6/23/15
 */
public class Utils {
    /* Constants*/



    final static String LINUX_FOLDER_NAME = ".IPNotifier", WINDOWS_FOLDER_NAME = "IPNotifier";
    List<String> command;
    ArrayList<String> desktopFile;
    boolean windows;
    File folder, startup, checker;


    public Utils() {
        windows = isWindows();
        createStorageFolder(getFolderPath());
    }

        public void writeData(String data, String fileName) {
           try {
               File f=new File(getFolderPath()+fileName);
               PrintWriter pr= new PrintWriter(f);
                   pr.println(data);
               pr.close();
           } catch (IOException e) {
               e.printStackTrace();
           }

        }
    public String readData(String fileName) {
        File f;
        String data="";
        try{
            f=new File(getFolderPath()+fileName);
            if(!f.exists()) return null;
            else {
                BufferedReader br=new BufferedReader(new FileReader(f));
                String temp;
                while((temp=br.readLine())!=null) {
                    data+=temp;
                }


            }

        } catch(Exception e) {
            return null;
        }

        return data;
    }


    private boolean createStorageFolder(String filepath) {
        boolean success;
        if (windows) {
            folder = new File(filepath);
            success = folder.mkdir();
            //make the directory hidden

        } else {
            folder = new File(filepath);
            success = folder.mkdir();
        }


        return success;
    }


    //!!!!!!!!!!append "\\" or "/" respectively if creating file within that folder***********
    public static String getFolderPath() {
        if (isWindows()) {
            return "C:\\Users\\AppData\\Roaming\\" + WINDOWS_FOLDER_NAME+"\\";
        } else if (findOS().contains("MAC")) {
            return "/Users/" + findUsername() + "/" + LINUX_FOLDER_NAME+"/";
        } else {
            //it's linux then
            return "/home/" + findUsername() + "/" + LINUX_FOLDER_NAME+"/";
        }
    }


    public static String findOS() {
        String os = null;
        if (os == null) {
            os = System.getProperty("os.name");
        }
        return os;
    }

    public static boolean isWindows() {
        if (findOS().contains("Windows")) {
            return true;
        } else return false;
    }

    public static String findUsername() {
        return System.getProperty("user.name");
    }


}