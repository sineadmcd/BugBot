/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

/**
 * WebCrawls google for bitbucket reporitories
 * using the search string:
 * Home site:bitbucket.org
 * @author Sinead McDonagh
 */
public class WebCrawler {

    
   public static CrawlDB RECORDdb;
    public static QueueDB Qdb;
    private final String userAgent ="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
    private final String searchStr= "Home site:bitbucket.org";
    private static int bookMark =1;
    
    public WebCrawler() throws SQLException, IOException, InterruptedException
    {
         System.out.println("Google search bit bucket");
         getBookmark();     // get the revious page scanned
         
        String startindex = "&start=" +Integer.toString(10*bookMark);  // multiply this by 10 to get the current search result
        Document searchDoc = Jsoup.connect("https://www.google.com/search?q=" + searchStr +startindex)
                                     .userAgent(userAgent)//USER Agent
                                     .get();  
        
        Elements links = searchDoc.select("a[href]");             //collects the links in the webpage
                        
			for(Element link: links)                    // for each link
                        {
                           String url = link.attr("abs:href");     // gets the absolute url
                            // if it contains bitbucket
                            //System.out.println(url);
                            if(url.contains("https://bitbucket.org/")& !url.contains("related") & !url.contains("webcache"))
                                    {
                                        Thread.sleep(300); // delay for 300 ms as google blocks web crawlers
                                        //System.out.println("here");
                                        if(checkLink(url))  // this checks that this link is an actual repository and not some bitbucket info page
                                        {
                                            // if this repository already exists in records database it has already been scanned
                                             if(!checkExistence("BitBucketGoogleSearch", editURL(url)))  
                                             {
                                                // System.out.println(url);
                                                 // add to queue
                                                 Qdb.executeUp(Qdb.create(), editURL(url), "BitBucketGoogleSearch");
                                             }
                                        }
                                    }
                            
                        } 
                        // increment the the search page.
           bookMark++;
           setBookmark();
          
    }
    
    
    public String editURL(String URL)
    {
         if(URL.contains("bitbucket"))
        {
            char[] urlarr = URL.toCharArray();
            char[] temp = new char[500];
            int counter=0;
            int j=0;
                
                    for(int q = 22; q< urlarr.length; q++)
                    {
                        char c = urlarr[q];
                        if(c=='/')
                        {
                            counter++;
                        }
                        if(counter<2)
                        {
                            temp[j]=c;
                            j++;
                        }
                    }
                
            String end = new String(temp);
            URL = "https://BugBot16@bitbucket.org/" + end.trim();  
        }
           return URL;
    }
    
    // sets the line in the BugBotconfig file relatint to the google search page
    public void setBookmark()
    {    
        List<String> ConfigLines = new ArrayList<String>(); 
        try {
           
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/Desktop/BugBotConfig.txt"));
            String line;
             while ((line = reader.readLine()) != null)
             {
                ConfigLines.add(line);
                  if(line.indexOf("GooglebitbucketSearchPage=")!=-1)
                  {
                      
                      ConfigLines.remove(line);
                      //System.out.println("Set bookmark " + bookMark);
                      String newLine = "GooglebitbucketSearchPage=" + bookMark;
                      ConfigLines.add(newLine);
                      // System.out.println(newLine);

                  } 
             }
             reader.close();
             // re-writes the line
                 BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/user/Desktop/BugBotConfig.txt"));   
                 
                 for(int i=0; i<ConfigLines.size(); i++)
                  {
                      writer.write(ConfigLines.get(i));
                      writer.newLine();
                  }
                  
                  writer.close();
           
        } catch (IOException ex) {
            Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // retrieves the number on the line of BugBoyConfig 
    //relating to the google search page
    public void getBookmark()
    {
          List<Character> newBookmarkStr = new ArrayList<Character>(); 
            try
            {
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/Desktop/BugBotConfig.txt"));
            String line;
             while ((line = reader.readLine()) != null)
             {
                // System.out.println(line);
                  if(line.indexOf("GooglebitbucketSearchPage=")!=-1)
                  {
                      int pos = line.indexOf('=');
                       char[] linearr = line.toCharArray();
                       
                  for(int j =pos+1; j<linearr.length;j++)
                  {
                          newBookmarkStr.add(line.charAt(j));
                       }
                  }  
             }
            reader.close();
         }
            catch(IOException e){e.printStackTrace();}
    
                  StringBuilder builder = new StringBuilder(newBookmarkStr.size());
                  for(Character c: newBookmarkStr)
                  {
                      builder.append(c);
                  }
                  //System.out.println("Got bookmark: " + bookMark);
                  bookMark =Integer.parseInt(builder.toString());
    }
    
    public Boolean checkLink(String URL) throws SQLException, IOException
    {
        Document link = Jsoup.connect(URL)
                                     .userAgent(userAgent)//USER Agent
                                     .get(); 
        Boolean isLink = false;
        
       // System.out.print(link.html());
       // this is the check to make sure it is a reposiotry 
       // the HTML declaratiojn of the clone button is aui-button
       // if the page does not contain this button; the page is not a remote repository
        if(link.toString().contains("aui-button aui-button-light"))
        {
            isLink=true;
        }
        return isLink;
        
}
    // cheack the records database
     public Boolean checkExistence(String timeStamp,String URL) throws SQLException
    {
        System.out.println("URL: " + URL);
        RECORDdb = new CrawlDB();
        Qdb = new QueueDB();
                String sqlOPERATIONR = "select * from Record where URL = '"+URL+"'";// and TimeCreated = '"+timeStamp+"'";
                String sqlOPERATIONQ = "select * from Queue where URL = '"+URL+"'";// and TimeCreated = '"+timeStamp+"'";
		ResultSet rsR = RECORDdb.runSql(sqlOPERATIONR);
                ResultSet rsQ = Qdb.runSql(sqlOPERATIONQ);
                String temp = timeStamp;
		if(rsR.next() | rsQ.next() ){  
                   // System.out.println("true");
                     return true;
                }
                else{
                   // System.out.println("false");
                    return false;
                }        
    }  
}
        