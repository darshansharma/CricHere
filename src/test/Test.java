package test;

import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.Document;

class CricAsk{
    String apikey;
    public void setApiKey(String apikey){
        this.apikey = apikey;
    }
    public void getMatchDetails() throws IOException{
        String doc = Jsoup.connect("http://cricapi.com/api/matchCalendar?apikey="+apikey).header("Accept", "text/javascript").ignoreContentType(true).execute().body();
        System.out.println(doc);
        String cname = "India"; //Write your country name here
        String cname1 = cname+" v"; // 1st possiblity of your country in JSON data
        String cname2 = "v ".concat(cname); // 2nd possiblity of your country name in JSON data        
        String matchdetail = null;
        StringBuilder sb = new StringBuilder();
        char cname_array[] =  new char[10000];
        int cindex=0; ////set country position from json in local variable
        int cindex1 = 0; // cindex 1 for cname1 
        int cindex2 = 0; // cindex 2 for cname2
        if(doc.toLowerCase().contains(cname1.toLowerCase())){
            cindex1 = doc.indexOf(cname1); 
            //System.out.println(cindex1);            
        }
        if(doc.toLowerCase().contains(cname2.toLowerCase())){
            cindex2 = doc.indexOf(cname2); 
            //System.out.println(cindex2);            
        }
        if(cindex1 > cindex2){ 
            cindex=cindex2; 
            int tempIndex=0;            
            tempIndex = doc.indexOf("name", cindex-15);
            //System.out.println("TEMP INDEX: " + tempIndex);
            tempIndex += 7;
            cindex = tempIndex;            
        }else{ cindex=cindex1; }        
        for(int i=cindex; i<doc.indexOf("}", cindex); i++){
            sb.append(doc.charAt(i));            
        }
        
        matchdetail = sb.toString();
        matchdetail = matchdetail.replaceAll("\"", "");        
        System.out.println(matchdetail);
    }

}



public class Test {
    public static void main(String[] args) throws Exception {
        String apikey = "0RKpiKRCFQPUYCGiFXA24UvDiRW2";       
        CricAsk ca = new CricAsk();
        ca.setApiKey(apikey);
        ca.getMatchDetails();
       
     /*while(true){
        org.jsoup.nodes.Document doc = Jsoup.connect("http://www.cricbuzz.com/").get();
         //System.out.println(doc.toString());
         //Element e = getElementsByClass('gamelist D(f) Fld(r) Pos(a) Trsdu(.3s)');
         //org.jsoup.select.Elements e1 = doc.getElementsByTag("span");
         org.jsoup.select.Elements e = doc.getElementsByClass("cb-col-100 cb-col cb-hm-scg-blk ");
         //System.out.println(e);
         String para = e.toString();
         //String opponent;
         //System.out.println(para);
         String x = "India v";
         String x2 = "v India";
         System.out.println("Hello! My name is doru and I am gonna show you live score of INDIA in today's cricket match.");
         System.out.println("***************");
         System.out.println("Let me check if today INDIA is having any match or not");
         if(para.toLowerCase().contains(x.toLowerCase()) || para.toLowerCase().contains(x2.toLowerCase())){
             
             System.out.println("-- -- Hoila!! India is having a match today.-- --");
         }
                 
         //System.out.println(para);
         String check = " <div class=\"cb-ovr-flo\" style=\"display:inline-block; width:140px\">";
         System.out.println("** AND THE FUCKING SCORE IS **");
         if(para.contains(check)){
             int i = para.indexOf(check);
             for(int p=i+70; p<i+90; p++){
                 System.out.print(para.charAt(p));
             }//System.out.println(i);
         }
         
         TimeUnit.SECONDS.sleep(10);

        }*/
    }
}

