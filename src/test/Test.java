package test;

import java.io.IOException;
import org.jsoup.*;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.util.concurrent.TimeUnit;
import com.nitido.utils.toaster.Toaster;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Timer;
import javax.imageio.ImageIO;


class CricAsk {

    String apikey;
    String unique_id;

    public void setApiKey(String apikey) {
        this.apikey = apikey;
    }
    public int getMin(int x, int y){
        if(x > y){
            return y;
        }
        else{
            return x;
        }
    }
    public String getMatchDetails() throws IOException {
        String doc = Jsoup.connect("http://cricapi.com/api/matchCalendar?apikey=" + apikey).header("Accept", "text/javascript").ignoreContentType(true).execute().body();
        //System.out.println(doc);
        String cname = "South Africa"; //Write your country name here
        //cname = cname.toLowerCase();
        String cname1 = cname + " v"; //System.out.println(cname1);  // 1st possiblity of your country in JSON data
        String cname2 = "v "+ cname; //System.out.println(cname2);  // 2nd possiblity of your country name in JSON data        
        String matchdetail;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        char cname_array[] = new char[10000];
        int cindex=0; ////set country position from json in local variable
        int cindex1 = 0; // cindex 1 for cname1 
        int cindex2 = 0; // cindex 2 for cname2
        int tempIndex;
        if (doc.toLowerCase().contains(cname1.toLowerCase())) {
            cindex1 = doc.indexOf(cname1);
                        
        }else{cindex1=-99;}//System.out.println("CINDEX1 "+cindex1);
        if (doc.toLowerCase().contains(cname2.toLowerCase())) {
            cindex2 = doc.indexOf(cname2);
                        
        }else{cindex2=-99;}//System.out.println("CINDEX2 "+cindex2);
        if (cindex1==-99 && cindex2!=-99) {
            cindex = cindex2;            
            tempIndex = doc.indexOf("name", cindex - 25);
            //System.out.println("TEMP INDEX: " + tempIndex);
            tempIndex += 7;
            cindex = tempIndex;
        } else if(cindex2==-99 && cindex1!=-99) {
            cindex = cindex1;
            tempIndex = doc.indexOf("name", cindex - 25);
            //System.out.println("TEMP INDEX: " + tempIndex);
            tempIndex += 7;
            cindex = tempIndex;
            
        }else{
            cindex = getMin(cindex1, cindex2);
            tempIndex = doc.indexOf("name", cindex - 25);
            //System.out.println("TEMP INDEX: " + tempIndex);
            tempIndex += 7;
            cindex = tempIndex;
        }
        for (int i = cindex; i < doc.indexOf("}", cindex); i++) {
            sb.append(doc.charAt(i));
        }

        matchdetail = sb.toString();
        matchdetail = matchdetail.replaceAll("\"", "");
        //System.out.println(matchdetail);
        cindex = cindex - 70;
        for (int i = doc.indexOf("unique_id", cindex) + 11; i < doc.indexOf("name", cindex); i++) {
            sb2.append(doc.charAt(i));
        }
        unique_id = sb2.toString();
        unique_id = unique_id.replace("\"", "");
        unique_id = unique_id.replace(",", "");
        //System.out.println("Match Unique ID : " + unique_id);
        return matchdetail;
    }

    public String getLiveMatchStatistic() throws IOException {
        String stat;
        String score;
        if (unique_id.equalsIgnoreCase("will generate 1-2 days before match")) {
            System.out.println("SORRY MATCH HAS NOT STARTED YET");
            stat = "SORRY MATCH HAS NOT STARTED YET";
        } else {
            String res = Jsoup.connect("http://cricapi.com/api/cricketScore?apikey=" + apikey + "&unique_id=" + unique_id).header("Accept", "text/javascript").ignoreContentType(true).execute().body();
            System.out.println(res);
            stat = res;
        }
        return stat;
    }

    public String getUniqueId(){
        return unique_id;
    }
}

public class Test{
    
    
 
    static void callSystemTray(final String matchTitle, final String score) throws AWTException, InterruptedException, IOException{
        if(System.getProperty("os.name").toLowerCase().contains("win")){
            if(SystemTray.isSupported()){
                SystemTray tray = SystemTray.getSystemTray();
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
                TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
                trayIcon.setImageAutoSize(true);
                trayIcon.setToolTip("System tray icon demo");
                tray.add(trayIcon);
                trayIcon.displayMessage(matchTitle, score, MessageType.INFO);                                
            }
            else{
                System.out.println("Sorry, Your System does not support toolbar notification. :(");
            }
        }
        else if((System.getProperty("os.name").toLowerCase().contains("lin"))){
           if(SystemTray.isSupported()){
               System.out.println(score);
                final PopupMenu popup = new PopupMenu();
                
                Image img = ImageIO.read(new File(System.getProperty("user.dir")+"/images/crichere.png"));
                final TrayIcon trayIcon = new TrayIcon(img, "tray icon");
                final SystemTray tray = SystemTray.getSystemTray();
                
                 // Create a pop-up menu components
                MenuItem aboutItem = new MenuItem(score);                                
                
                //Add components to pop-up menu
                popup.add(aboutItem);                                
                trayIcon.setPopupMenu(popup);
                
                try {
                    tray.add(trayIcon);
                    trayIcon.displayMessage(matchTitle,score,TrayIcon.MessageType.INFO);
                    
                } catch (AWTException e) {
                    System.out.println("TrayIcon could not be added.");
                }
               
           }
           else{
               System.out.println("Sorry, Your System does not support toolbar notification. :(");
           }
    
         }
    }

    public static void main(String[] args) throws Exception {
        String apikey = "0RKpiKRCFQPUYCGiFXA24UvDiRW2";
        CricAsk ca = new CricAsk();        
        ca.setApiKey(apikey);
        ca.getMatchDetails();
        ca.getLiveMatchStatistic();
        String matchTitle = ca.getMatchDetails();
        StringBuilder sb = new StringBuilder();        
        for(int i=0; i<matchTitle.indexOf("at"); i++){
            sb.append(matchTitle.charAt(i));
        }
        matchTitle = sb.toString();
        if(!ca.getUniqueId().equals("will generate 1-2 days before match")){       
            while(!ca.getUniqueId().equals("will generate 1-2 days before match")){       
                StringBuilder sb2 = new StringBuilder();
                String score = ca.getLiveMatchStatistic();
                for(int i=score.indexOf("score")+8; i<score.indexOf("\"",score.indexOf("score")+9); i++){
                    sb2.append(score.charAt(i));
                }score = sb2.toString();score = score.replace("*", "");
                System.out.println(score);
                callSystemTray(matchTitle,score);
                TimeUnit.SECONDS.sleep(120); //set Time here. Give time in seconds
                }
        }else{
            callSystemTray("NEXT CRICKET MATCH", ca.getMatchDetails());
           //System.exit(1);
        }

    }
    
}
