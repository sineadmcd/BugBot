/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp;

import static fyp.RepoScan.totalLines;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class RepoScanC {
    
    private List<String> g_lines;
    public RepoScanC(String Cfile){
        
        g_lines = new ArrayList();
         try (BufferedReader br = new BufferedReader(new FileReader(Cfile))) {
             //System.out.println("FILE ACCESSED READ");
                String line;
                 while ((line = br.readLine()) != null) {
                     g_lines.add(line);
                    
            }
                 totalLines += g_lines.size();
                 BugScanC(g_lines, new File(Cfile));
         }
                 catch(Exception e){
                         
                         }
        
    }
    
    private void BugScanC(List<String> lines, File fixfile) throws IOException
    {
        boolean bugpresentFile = false;
        Driver.admin.setStatus("Identifying");
        
        System.out.println( "FILE: " + fixfile);
        int linepos=0;
        BugHive bugHive = new BugHive();
        for(String line:lines)
         {
            linepos++;
             /////////// DATABASE SEARCH CODE/////////////////////////
            // System.out.println(bugHive.dblength());
            for(int i=9; i<= bugHive.dblength(); i++)
            {
                //System.out.println("for each bug");
                String regex = bugHive.getRegEx(i);
                //System.out.print(regex);
                Pattern pattern = Pattern.compile(regex);      // does this move to next??
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {

                                System.out.println("I found the text '" +
	                    matcher.group()+ "' starting at index " + matcher.start() +
	                    " and ending at index " + matcher.end() + " around line: " + linepos);
                            System.out.println("BUG_ID: " + bugHive.getBugID(i));
                                
                            Driver.admin.updateBugtypeStats(bugHive.getBugID(i));
                            
                            int bugNum=i;
                                bugpresentFile=true;
                                RepoScan.bugpresentRepo = true;
                                RepoScan.bugCount++;
                                Driver.admin.addBugsFound();
                                g_lines= Fixline(g_lines, linepos,bugHive.getFix(i),bugHive.getGeneralForm(i), matcher.group());
                                RepoScan.reportLines.add("BugBot found: " + bugHive.getBugID(i)+ ": "+ bugHive.getDescription(i));
                            }     
                
            } 
             } 
        
        if(bugpresentFile)
         RepoScan.Fix(fixfile, g_lines);
        
    }
    
    
    private List<String> Fixline(List<String> lines, int pos, String bugfix, String Bugform, String bug)
    {

        String newLine=null;
        String OGline = lines.get(pos-1);
        // not fx just a comment
        if(bugfix.contains("//"))
        {
            System.out.println("here");
            newLine = OGline+ "\t"+ bugfix;
            System.out.println(newLine);
        }
        else    //fix and replace
        {   
            /*System.out.println("bugfix:" + bugfix);
            System.out.println("OGline:" + OGline);
            System.out.println("Bug:" + Bug);*/
            newLine =setVars(bugfix, OGline, Bugform);
        }
            String fixedline =OGline.replaceAll(bug, newLine);
         lines.set(pos-1, fixedline);
        
         return lines;
    }
    
    private String setVars(String bugfix, String origLine, String Bug)
    {
         String newLine=null;
         List<String> BugVars= extractVars(Bug);
         List<String> fixvars = extractVars(bugfix);
         List<String> lineVars = extractVarsRegex(origLine);
          String fixed =null;
          if(fixvars.size()==BugVars.size())
          {
             for(int i=0; i<fixvars.size();i++)
            {
              bugfix=bugfix.replaceFirst("\\[((\\w|\\d)+)\\]", lineVars.get(i));

            }
             fixed=bugfix;
          }
          
          if(fixvars.size()>BugVars.size())
          {
              for(int i=0; i<fixvars.size();i++)
              {
                bugfix=bugfix.replaceAll("\\[((\\w|\\d)+)\\]", lineVars.get(0));
              }
              fixed =bugfix;
          }
          
          System.out.println("\t" +origLine);
          newLine = fixed;
          System.out.println("\t" +newLine);
          return newLine;
    }
          
    
        private List<String> extractVars(String form)
    {
        
        List<String> numVars = new ArrayList<String>();
         Pattern pattern = Pattern.compile("\\[((\\w|\\d)+)\\]");      
         Matcher matcher = pattern.matcher(form);
                while (matcher.find()) {
                           /*System.out.println("I found the text '" +
	                    matcher.group()+ "' starting at index " + matcher.start() +
	                    " and ending at index " + matcher.end());*/
                            numVars.add(matcher.group());
                }
                return numVars;         
                
    }
        
        private List<String> extractVarsRegex(String line)
        {
            List<String> vars = new ArrayList();
            Pattern pattern = Pattern.compile("((\\W|\\s)(\\w|\\d)+)");      
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                          if(!matcher.group().contains("double") && !matcher.group().contains("return") && !matcher.group().contains("if"))
                          {
                                   /*  System.out.println("I found the text '" +
	                    matcher.group().substring(1)+ "' starting at index " + matcher.start() +
	                    " and ending at index " + matcher.end());*/
                              vars.add(matcher.group().substring(1));
                          }
                }
            return vars;
        }
    
}
