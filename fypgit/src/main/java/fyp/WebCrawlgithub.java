/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author user
 */
public class WebCrawlgithub {
    
    public static CrawlDB RECORDdb;
    public static QueueDB Qdb;
    
    String username = "sineadmcd";
    String password = "7623sonyer";
    
    private HashMap<String, String> cookies;
    private HashMap<String, String> formData;
    private HashMap<String, String> SearchformData;
    
    private Connection.Response homePage;
    private Connection.Response searchPage;
    private String userAgent ="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
    
    private ArrayList Queue2Scan = new ArrayList();
    private Boolean exists = false;

    public WebCrawlgithub() throws SQLException, IOException
    {
      /***
       * Need to log in
       * obtain cookies and authToken
       * establish a session
       * the search for the BugBotFINDME file
       */
        Connection.Response loginForm = Jsoup.connect("https://github.com/login")
                                     .method(Connection.Method.GET) 
                                     .userAgent(userAgent)//USER Agent
                                     .execute(); 
        
   
        Document loginDoc = loginForm.parse(); 
        cookies = new HashMap<>(loginForm.cookies());  
 
        String authToken = loginDoc.select("#login > form > div:nth-child(1) > input[type=\"hidden\"]:nth-child(2)")  
                           .first()  
                           .attr("value");  // this obtains the every changing authorisation token

        formData = new HashMap<>();
        formData.put("commit", "Sign in");  
        formData.put("utf8", "e2 9c 93"); 
        formData.put("login", username);  // github credentials
        formData.put("password", password);  
        formData.put("authenticity_token", authToken);  

        String sessionURL ="https://github.com/session";
        // establish a session

         homePage = Jsoup.connect(sessionURL)
        .cookies(cookies)  
        .data(formData)  
        .userAgent(userAgent)
        .method(Connection.Method.POST)  
        .execute();
       
        String searchURL ="https://github.com/search";
            // search for repositories
        SearchformData = new HashMap<>();
        SearchformData.put("utf8", "e2 9c 93"); 
        SearchformData.put("q","filename:BugBotFINDME") ;
        SearchformData.put("authenticity_token", authToken);  

        searchPage = Jsoup.connect(searchURL)
        .cookies(homePage.cookies())  
        .data(SearchformData)  
        .userAgent(userAgent)
        .method(Connection.Method.GET)  
        .execute();
           
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            try {
                crawler(timeStamp, "scheduledGITHUB");  // add to the queue db to mark search of github
            } catch (InterruptedException ex) {
                Logger.getLogger(WebCrawlgithub.class.getName()).log(Level.SEVERE, null, ex);
            }
          
    }
    public void crawler(String timeStamp, String URL) throws SQLException, IOException, InterruptedException
    {
        RECORDdb = new CrawlDB();
        Qdb = new QueueDB();
                    Statement statement = RECORDdb.create();
                    RECORDdb.executeUp(statement, URL, timeStamp);
                   
                    Document doc = searchPage.parse();                  
                    Elements links = doc.select("a[href]");             // obtain all links on the page
                        
			for(Element link: links)                        // for each link obtained
                        {
                            //System.out.println(link.attr("abs:href"));
                           
				if(link.attr("abs:href").contains("BugBotFINDME") && !link.attr("abs:href").contains("authenticity_token") )
                                {  
                                    String[] gitname = link.attr("abs:href").split("/blob");
                                    String url = gitname[0];
                                    String time = getCreateDate(url);   // gets the last commit
                                    exists =checkExistence(time, url);
                                    /**************KEY CODE************/
                                    // check it doesnt add github findme url to queue??
                                    if(!url.contains("filename%"))
                                    if(!exists)
                                    {       Qdb.executeUp(Qdb.create(), url+".git", time);
                                            Driver.admin.addReposAccessRequests(url);
                                            System.out.println(url);  
                                    }
                                }
			}                
    }
    
    public static String getCreateDate(String repoURL) throws IOException
    {
        try{
         Connection.Response repoPage = Jsoup.connect(repoURL+ "/commits/master/BugBotFINDME")
        .execute();
         Document repodoc = repoPage.parse();
             int c =repodoc.toString().indexOf("relative-time datetime");
             char[] Time= new char[50];
             for(int i=c+24; i<c+43; i++)
             {
                char temp = repodoc.toString().charAt(i);
                if( !(temp==':' | temp == '-'))
                { 
                  if(temp== 'T')
                  {
                    temp = '_';
                  }
                Time[i-c] = temp; 
                }
             }
             String Timestamp =  new String(Time);
             String thisone = Timestamp.trim().toString();
             //System.out.println(thisone);
            return thisone;
        }catch(Exception e)
        {
            System.err.println("Website request repo");
        }
            return "Webiste Direct Request";
    }
    
    // same as bitbucket repos check existecne but time is compared
    public Boolean checkExistence(String timeStamp,String URL) throws SQLException
    {
        URL = URL + ".git";
                String sqlOPERATIONR = "select * from Record where URL = '"+URL+"' and TimeCreated = '"+timeStamp+"'";
                String sqlOPERATIONQ = "select * from Queue where URL = '"+URL+"' and TimeCreated = '"+timeStamp+"'";
		ResultSet rsR = RECORDdb.runSql(sqlOPERATIONR);
                ResultSet rsQ = Qdb.runSql(sqlOPERATIONQ);
                String temp = timeStamp;
		if(rsR.next()){  
                    //System.out.println("true");
                     return true;
                }
                else if(rsQ.next()){
                        return true;
                }
                else{
                    //System.out.println("false");
                    return false;
                }        
    }  
}
