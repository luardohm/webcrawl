import java.net.*;
import java.io.*;
import java.util.*;

public class URLConnDemo{
    public static void main(String[] args) throws Exception{
    String urls[] = new String[1000];
    String url = "http://www.vidamo.de/marken";
    int i=0;
    int end=0;
    int var=0;
    String web = Url.readURLContent(url);
    
   
    for(i=0;i<1000; i++){
        
        
        
        String[] words = web.split(" ");
        //split all the content of the web in works
         for(String word : words){
                
            //finds the words containing link signals such as http and href
            String urltag = "http://";
            String linktag = "href=";
            Boolean found;
            Boolean tag;
            
            
                
            found = word.contains(urltag);
            tag = word.contains(linktag);
             if((found==true) && (tag==true) ){
                //verify that the word contains a link tag
               String[] links = word.split(">");
                 //we delet the rest of the chars after the link tag closes >
                 for(String link : links){
                    //we extract those posible links as words
                    found = link.contains(urltag);
                    //we check again that the http: word is included
                    if(found==true){ 
                        //if it's true we clean that word taking away href
                        String urlStringFinal = link.substring(5);
                        end = urlStringFinal.length()-1;
                        //lets take away the " parentesis by deleting the first and last char
                        urlStringFinal = urlStringFinal.substring(1, end);
                        
                        //Starts counting on the link 20 because the first can be css files or other links not relevant
                        if((var>20) && (var<1021)){
                       
                        url=urlStringFinal;
                        urls[i]=url;
                        System.out.println("link: "+var+":"+urls[i]);
                        }
                        var++;
                        
                       
                        
                    }
                   
                 }
             }
        
     
    }
   
    }
}

}

/*This class contains a static function which will fetch the webpage
  of the given url and return as a string */
class Url{
    public static String readURLContent(String address)throws Exception{
    String webpage = "";
        String inputLine = "";
        URL url = new URL(address);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(url.openStream()));
        while ((inputLine = in.readLine()) != null)
        webpage += inputLine;
        in.close();
    return webpage;
    }
}
 