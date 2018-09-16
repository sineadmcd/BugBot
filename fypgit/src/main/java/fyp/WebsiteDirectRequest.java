/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
/**
 *
 * @author user
 */
public class WebsiteDirectRequest {
    
    public static void getEmails()
    {
      String host = "outlook.office365.com";// change accordingly
      String mailStoreType = "imaps";
      String username = "bugbot16@outlook.com";// change accordingly
      String password = "myfyp16bugbot";// change accordingly

      check(host, mailStoreType, username, password);   
    }
    
    
       public static void check(String host, String storeType, String user,String password) 
   {
       try {

      //create properties field
      Properties properties = new Properties();

      properties.put("mail.imap.host", host);
      properties.put("mail.imap.port", "995");
      properties.put("mail.imap.starttls.enable", "true");
      Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
          
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }
});
  
      //create the POP3 store object and connect with the pop server
      Store store = emailSession.getStore("imaps");

      store.connect(host, user, password);

      //create the folder object and open it
      Folder inbox = store.getFolder("INBOX");
      inbox.open(Folder.READ_WRITE);
      int ucount = inbox.getUnreadMessageCount();
      // retrieve the messages from the folder in an array and print it
      Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
      
      
      for (int i = 0;i < ucount; i++) {
         Message message = messages[i];
         String content = message.getContent().toString();
         content = content.substring(16).trim();
         checkURL(content);
      }

      //close the store and folder objects
      inbox.close(false);
      store.close();

      } catch (Exception e) {
          
      } 
   }
       
       public static void checkURL(String url)
       {
           if(url.contains("github"))
           {
               if(!url.endsWith(".git"))
               {
                   url = url+".git";
               }
           }
           else if(url.contains("bitbucket"))
           {
                {
            char[] urlarr = url.toCharArray();
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
            url = "https://sineadmcd@bitbucket.org/" + end.trim();  
        }
           }
           else{
               return;
           }
           String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
           QueueDB Qdb = new QueueDB();
           Qdb.executeUp(Qdb.create(), url, timeStamp);
           Driver.admin.addReposAccessRequests(url);
           System.out.println(url);  

                 
                 
       }

}
