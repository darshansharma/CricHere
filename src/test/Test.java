package test;

import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.Document;

class CricAsk {

    String apikey;
    String unique_id;

    public void setApiKey(String apikey) {
        this.apikey = apikey;
    }

    public void getMatchDetails() throws IOException {
        String doc = Jsoup.connect("http://cricapi.com/api/matchCalendar?apikey=" + apikey).header("Accept", "text/javascript").ignoreContentType(true).execute().body();
        //System.out.println(doc);
        String cname = "Pakistan"; //Write your country name here
        String cname1 = cname + " v"; // 1st possiblity of your country in JSON data
        String cname2 = "v ".concat(cname); // 2nd possiblity of your country name in JSON data        
        String matchdetail = null;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        char cname_array[] = new char[10000];
        int cindex = 0; ////set country position from json in local variable
        int cindex1 = 0; // cindex 1 for cname1 
        int cindex2 = 0; // cindex 2 for cname2
        if (doc.toLowerCase().contains(cname1.toLowerCase())) {
            cindex1 = doc.indexOf(cname1);
            //System.out.println(cindex1);            
        }
        if (doc.toLowerCase().contains(cname2.toLowerCase())) {
            cindex2 = doc.indexOf(cname2);
            //System.out.println(cindex2);            
        }
        if (cindex1 > cindex2) {
            cindex = cindex2;
            int tempIndex = 0;
            tempIndex = doc.indexOf("name", cindex - 15);
            //System.out.println("TEMP INDEX: " + tempIndex);
            tempIndex += 7;
            cindex = tempIndex;
        } else {
            cindex = cindex1;
        }
        for (int i = cindex; i < doc.indexOf("}", cindex); i++) {
            sb.append(doc.charAt(i));
        }

        matchdetail = sb.toString();
        matchdetail = matchdetail.replaceAll("\"", "");
        System.out.println(matchdetail);
        cindex = cindex - 70;
        for (int i = doc.indexOf("unique_id", cindex) + 11; i < doc.indexOf("name", cindex); i++) {
            sb2.append(doc.charAt(i));
        }
        unique_id = sb2.toString();
        unique_id = unique_id.replace("\"", "");
        unique_id = unique_id.replace(",", "");
        System.out.println("Match Unique ID : " + unique_id);
    }

    public void getLiveMatchStatistic() throws IOException {
        if (unique_id.equalsIgnoreCase("will generate 1-2 days before match")) {
            System.out.println("SORRY MATCH HAS NOT STARTED YET");
        } else {
            String res = Jsoup.connect("http://cricapi.com/api/cricketScore?apikey=" + apikey + "&unique_id=" + unique_id).header("Accept", "text/javascript").ignoreContentType(true).execute().body();
            System.out.println(res);
        }
    }

}

public class Test {

    public static void main(String[] args) throws Exception {
        String apikey = "0RKpiKRCFQPUYCGiFXA24UvDiRW2";
        CricAsk ca = new CricAsk();
        ca.setApiKey(apikey);
        ca.getMatchDetails();
        ca.getLiveMatchStatistic();

    }
}
