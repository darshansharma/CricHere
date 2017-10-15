package test;

import org.jsoup.*;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws Exception {
       
     while(true){
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

        }
    }
}

