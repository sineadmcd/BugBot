/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp;


import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.time.StopWatch;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 * Driver of BugBot
 * @author Sinead McDonagh
 */
public class Driver{
 
    public static RepoScan myreposcan;      // repoScan object for each repository
    private static String url=null;         // the URL passed as a parameter to repoScan
    public static QueueDB Qdb;              // connection to the queue database
    
      private final static int numScans=100;      // number of scans to complete in first while loop
      private final static int minQlen =10;       // ensure Queue length does not drop below this threshold
      
     public static final Admin admin = new Admin(); // admin object to record different statistics
            
    public static void main(String[] args) throws SQLException, InterruptedException 
    { 
            int run =0;
            while(run <numScans)
            {
                /******** KEEP QUEUE LENGTH > minQlen *********/
                Qdb = new QueueDB();                   //initialise new connection to queue to avoid time-out exception
                int Qlen= Qdb.getLength();             // get length
                admin.setQlength(Qlen);                // set the length in the BugBotConfig file
            
            
            /********* IF QUEUE LENGTH IS less than THRESH ***********/
                while(Qlen<minQlen){
                        try {
                        System.out.println("length of Queue: " +Qlen);
                        admin.setStatus("Finding");

                        /****** GET DIRECT, INDIRECT, BUGBOT.ORG REQUESTS ****/
                        WebsiteDirectRequest.getEmails();                       //priority 1
                        WebCrawlgithub directRequests = new WebCrawlgithub();   // priority 2
                        WebCrawler indirectrequests = new WebCrawler();         // priority 3

                        Qlen= Qdb.getLength();

                        } catch (IOException ex) {
                        Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            
            /******** GET URL AT TOP OF QUEUE *********/
            url = Qdb.getFirst();
            admin.setCurrentRepo(url);
            
            /******** REPOSITORY SCAN URL **********/
            StopWatch sw = new StopWatch();         // timer for each repository scan
            sw.start();
            try {
                admin.setStatus("Cloning");
                myreposcan = new RepoScan(url);

                    try{
                    myreposcan.getScannableFiles();
                    }
                    catch(Exception e)
                    {
                    //System.out.println("get scannable error");
                    }
            
                myreposcan.Scan();
                sw.stop();
                
                
                /****** RECORD DATA FOR REVEIW in DATA.txt *******/
                RepoScan.record = RepoScan.record + sw + ",";
                RepoScan.recordData();
                sw.reset();
            
                /******** FINISH UP WITH REPO ***********/
                myreposcan.Report();
                if(url.contains("github")){
                myreposcan.Push();
                myreposcan.Pull();
                }

                } catch (GitAPIException ex) {

               // System.out.println("Exception");
                Qdb = new QueueDB();
                Qdb.removeFirst();      // if exception occurs with repo, remove from queue & continue
 
                }
                run++;
            }
            
                    

           
           
     } 
           
    
}
