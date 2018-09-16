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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Admin {
    
    private int reposAccessed=0;
    private int reposAccessRequests=0;
    private int bugsFound=0;
    private int changesPushed=0;
    private int bugsNum=0;
    private String status ="Finding";
    private int qlength =0;
    private String url="Nothing";
    private int origBugsNum=8;
    
    private int tweets = 1;
    
    public Admin()
    {
       checkAdminFile();
    }
    public void getReposAccessed(String line)
    {
                         List<Character> lines = new ArrayList<Character>();
                // System.out.println(line);
                  if(line.indexOf("ReposAccessed=")!=-1)
                  {
                      int pos = line.indexOf('=');
                       char[] linearr = line.toCharArray();
                       
                  for(int j =pos+1; j<linearr.length;j++)
                  {
                          lines.add(line.charAt(j));
                       }
                    
                   StringBuilder builder = new StringBuilder(lines.size());
                  for(Character c: lines)
                  {
                      builder.append(c);
                  }int numacc = Integer.parseInt(builder.toString());
                 
                  this.reposAccessed =numacc;
                  }
    }
    public void getReposAccessRequests(String line)
    {
          List<Character> lines = new ArrayList<Character>();
        if(line.indexOf("RepoAccessRequests=")!=-1)
                  {
                      int pos = line.indexOf('=');
                       char[] linearr = line.toCharArray();
                       
                  for(int j =pos+1; j<linearr.length;j++)
                  {
                          lines.add(line.charAt(j));
                       }
                    
                   StringBuilder builder = new StringBuilder(lines.size());
                  for(Character c: lines)
                  {
                      builder.append(c);
                  }
                  this.reposAccessRequests =Integer.parseInt(builder.toString());
                  }
    }
    public void getBugsFound(String line)
    {
         List<Character> lines = new ArrayList<Character>();
     if(line.indexOf("bugsFound=")!=-1)
                  {
                      int pos = line.indexOf('=');
                      char[] linearr = line.toCharArray();
                       
                  for(int j =pos+1; j<linearr.length;j++)
                  {
                          lines.add(line.charAt(j));
                       }
                    
                   StringBuilder builder = new StringBuilder(lines.size());
                  for(Character c: lines)
                  {
                      builder.append(c);
                  }
                  this.bugsFound =Integer.parseInt(builder.toString());
                  }
    }
    
    public void getChangesPushed(String line)
    {
           List<Character> lines = new ArrayList<Character>();
                  if(line.indexOf("changesPushed=")!=-1)
                  {
                      int pos = line.indexOf('=');
                       char[] linearr = line.toCharArray();
                       
                  for(int j =pos+1; j<linearr.length;j++)
                  {
                          lines.add(line.charAt(j));
                       }
                    
                   StringBuilder builder = new StringBuilder(lines.size());
                  for(Character c: lines)
                  {
                      builder.append(c);
                  }
                  this.changesPushed =Integer.parseInt(builder.toString());
                  }
    }
    
    public void getBugsNum(String line)
    {
          List<Character> lines = new ArrayList<Character>();
          
                  if(line.indexOf("bugsNum=")!=-1)
                  {
                      int pos = line.indexOf('=');
                       char[] linearr = line.toCharArray();
                       
                  for(int j =pos+1; j<linearr.length;j++)
                  {
                          lines.add(line.charAt(j));
                       }
                    
                   StringBuilder builder = new StringBuilder(lines.size());
                  for(Character c: lines)
                  {
                      builder.append(c);
                  }
                  this.bugsNum =Integer.parseInt(builder.toString());
                  }
    }
    
    public void getOrigBugsNum(String line)
    {
             List<Character> lines = new ArrayList<Character>();
          if(line.indexOf("origBugsNum=")!=-1)
                  {
                      int pos = line.indexOf('=');
                       char[] linearr = line.toCharArray();
                       
                  for(int j =pos+1; j<linearr.length;j++)
                  {
                          lines.add(line.charAt(j));
                       }
                    
                   StringBuilder builder = new StringBuilder(lines.size());
                  for(Character c: lines)
                  {
                      builder.append(c);
                  }
                  this.origBugsNum =Integer.parseInt(builder.toString());
                  }
    }
    public void checkAdminFile(){
        
           
            try
            {
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/Desktop/BugBotConfig.txt"));
            String line;
             while ((line = reader.readLine()) != null)
             {
               getReposAccessed(line);
               getReposAccessRequests(line);
               getBugsFound(line);
               getChangesPushed(line);
               getBugsNum(line);
               getOrigBugsNum(line);
                 
             }
            reader.close();
         }
            catch(IOException e){e.printStackTrace();}
            
    }
    public int getBugtypeStat(String bugid)
    {
        int currentNum=0;
        try
            {
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/Desktop/BugBotConfig.txt"));
            String line;
             while ((line = reader.readLine()) != null)
             {
                 List<Character> chars = new ArrayList<Character>();
                  if(line.indexOf(bugid +"=")!=-1)
                  {
                      int pos = line.indexOf('=');
                       char[] linearr = line.toCharArray();
                       
                  for(int j =pos+1; j<linearr.length;j++)
                  {
                          chars.add(line.charAt(j));
                       }
                    
                   StringBuilder builder = new StringBuilder(chars.size());
                  for(Character c: chars)
                  {
                      builder.append(c);
                  }
                  currentNum = Integer.parseInt(builder.toString());
                  }
             }
            }catch(Exception e){}
        return currentNum;
    }
    
    public void updateBugtypeStats(String bugid)
    {
        int current = getBugtypeStat(bugid);
        int newnum = current+1;
        List<String> ConfigLines = new ArrayList<String>(); 
        try {
           
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/Desktop/BugBotConfig.txt"));
            String line;
             while ((line = reader.readLine()) != null)
             {
                ConfigLines.add(line);
                  if(line.indexOf(bugid +"=")!=-1)
                  {
                      ConfigLines.remove(line);
                      String newLine = bugid +"=" + newnum;
                      ConfigLines.add(newLine);
                  } 
             }
    
         reader.close();
                 BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/user/Desktop/BugBotConfig.txt"));   
                 
                 for(int i=0; i<ConfigLines.size(); i++)
                  {
                      writer.write(ConfigLines.get(i));
                      writer.newLine();
                  }
                  
                  writer.close();
                    }
        catch(Exception e)
        {
            
        }
    }
    
    
    public void updateAdminFile()
    {
         List<String> ConfigLines = new ArrayList<String>(); 
        try {
           
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/Desktop/BugBotConfig.txt"));
            String line;
             while ((line = reader.readLine()) != null)
             {
                ConfigLines.add(line);
                  if(line.contains("ReposAccessed="))
                  {
                      ConfigLines.remove(line);
                      String newLine = "ReposAccessed=" + this.reposAccessed;
                      ConfigLines.add(newLine);
                  } 
                  if(line.contains("RepoAccessRequests="))
                  {
                      ConfigLines.remove(line);
                      String newLine = "RepoAccessRequests=" + this.reposAccessRequests;
                      ConfigLines.add(newLine);
                  } 
                  if(line.contains("bugsFound="))
                  {
                      ConfigLines.remove(line);
                      String newLine = "bugsFound=" + this.bugsFound;
                      ConfigLines.add(newLine);
                  } 
                  if(line.contains("changesPushed="))
                  {
                      ConfigLines.remove(line);
                      String newLine = "changesPushed=" + this.changesPushed;
                      ConfigLines.add(newLine);
                  } 
                  if(line.contains("bugsNum="))
                  {
                      ConfigLines.remove(line);
                      String newLine = "bugsNum=" + this.bugsNum;
                      ConfigLines.add(newLine);
                  } 
                  if(line.contains("Status="))
                  {
                      ConfigLines.remove(line);
                      String newLine = "Status=" + this.status;
                      ConfigLines.add(newLine);
                  } 
                   if(line.contains("QueueLength="))
                  {
                      ConfigLines.remove(line);
                      String newLine = "QueueLength=" + this.qlength;
                      ConfigLines.add(newLine);
                  } 
                     if(line.contains("CurrentRepo="))
                  {
                      ConfigLines.remove(line);
                      String newLine = "CurrentRepo=" + this.url;
                      ConfigLines.add(newLine);
                  } 
                      if(line.contains("origBugsNum="))
                  {
                      ConfigLines.remove(line);
                      String newLine = "origBugsNum=" + this.origBugsNum;
                      ConfigLines.add(newLine);
                  } 
             }
              reader.close();
        } catch (IOException ex) {
            Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
              writeConfigFile(ConfigLines);
              updateTwitter();
    }
    
    private void writeConfigFile(List<String> ConfigLines)
    {
           BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("C:/Users/user/Desktop/BugBotConfig.txt"));
            for(int i=0; i<ConfigLines.size(); i++)
            {
                writer.write(ConfigLines.get(i));
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateTwitter()
    {
        if(this.reposAccessed== 100*tweets)
        {
            Tweet t = new Tweet("BugBot has accessed " + reposAccessed + " repositories, and found " 
                            + bugsFound + "bugs. Find out more at www.BugBot.org #Bot #Java #C");
            tweets++;
        }
    }

    public void addReposAccessed() {
        this.reposAccessed++;
        updateAdminFile();
    }

    public void addReposAccessRequests(String url) {
        Tweet t = new Tweet("BugBot has been requested to scan: " + url + 
             ", for BugBot to scan your' repo go to www.BugBot.org/BugBot");
        this.reposAccessRequests++;
        updateAdminFile();
    }

    public void addBugsFound() {
        this.bugsFound++;
        updateAdminFile();
    }

    public void addChangedPushed() {
        this.changesPushed++;
        updateAdminFile();
    }

    public void setBugsNum(int bugsNum) {
        if(bugsNum> origBugsNum)
        {
            Tweet t = new Tweet("A new bug has been added to BugHive. New bug total: " + bugsNum + 
                 ". To view these or add more visit wwww.BugBot.org/BugHive");
            origBugsNum = bugsNum;
        }
        this.bugsNum = bugsNum;
        updateAdminFile();
    }
    public void setStatus(String stat) {
        this.status = stat;
        updateAdminFile();
    }
    
    public void setQlength(int l)
    {
        this.qlength=l;
        updateAdminFile();
    }
    
     public void setCurrentRepo(String url)
    {
        this.url=url;
        updateAdminFile();
    }
    
}
