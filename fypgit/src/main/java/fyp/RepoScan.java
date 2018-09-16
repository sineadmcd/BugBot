/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.jgit.api.MergeResult;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.merge.MergeStrategy;
import org.eclipse.jgit.transport.FetchResult;

/**
 * Repository Scanning class 
 * Handles Git and scanning functionality
 * Sinead McDonagh
 */
    public class RepoScan {
    private String URL = "https://github.com/sineadmcd/FinalYearProjectTesting.git"; //default my github repo
    
        String args[]=null;
    
    /** variables for cloning the file locally ***/
    private String locallocation = null;
    private String foldername = null;
    private String localPath = null;
    public String rootURL = null;
    
    private int FileCount =0;   // file count is retreived from BugBotConfig through the admin class
    private static Git git;     // private variable git, connection to repository
    
    /** Lists of files **/
    private List<File> allFiles = new ArrayList();
    private List<File> filtered = new ArrayList();
    private List<File> filteredC = new ArrayList();
    
    private List<String> lines = new ArrayList();   // lines of code of each file
    public static List<String> reportLines = new ArrayList();   // the lines of the BugBotReport.txt file
    private String[][] variables = new String[3][10000];    // variable array
    private int varCount;
    private int declaredVars;
    /** temporary variables **/
    private    String varType=null;
    private    String name=null;
    private    String temp=null;
    private    String value=null;
    private int linepos =0;
    
    
    private String commitMsg;
    
    /** flags set if a bug is present **/ 
    private static boolean bugpresentFile=false;
    public static boolean bugpresentRepo=false;
    public static boolean SuccessFlag= false ;
    public static int bugCount;
    
    /** global connections to the databases **/
    public static CrawlDB RecordDB;
    public static QueueDB Qdb;
    BugHive bugHive;
    
    public static String record;    // record string added to the Data.csv file
    public static int totalVars;    // variables added to the Data.csv file
    public static int totalLines;
    

   
    
    private String userAgent ="Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";

   

    RepoScan(String URL) 
    {
        // setting all public variables back to 0
        bugCount=0;
        totalVars =0;
        totalLines=0;
        reportLines.clear();
        record = null;
        bugpresentRepo = false;
            rootURL =URL;
            rootURL= rootURL.replace(".git", "");
            record = rootURL+ ",";
            this.URL = URL;
            System.out.println(rootURL);
            
            
            // this extracts the language labelled in the meta details
            if(URL.contains("bitbucket")){
                     extractMETArepo(URL); }
            
            // local location git repositories are saved to
            this.locallocation = "C:/Users/user/Desktop/GitRepos/";
           
            // set local path to new file e.g File22
            getFilecount();
            FileCount++;
             this.foldername = "File" + FileCount;
            this.localPath = this.locallocation + this.foldername ;        
            setFilecount();
            
            // creates a remote(Fork) and local(Clone remote) copy of the repository
            if(URL.contains("github")){
                Fork();
                Clone();
                setUpfiles();
            }
            else{           // bitbucket autonomous scanning
                try{
                 git = Git.cloneRepository()
                    .setURI(this.URL)
                    .setDirectory(new File(localPath))
                    .call();
                 setUpfiles();
                }catch(GitAPIException e){
                      finish();
                   // System.err.println("no clone");
                } 
               
            }
            
            /* Driver Calls the following functions */
            // Driver: getScannable Files
            // Driver: Scan -> each file ->FixLine -> Fix -> Commit
            // Driver: recordData
            // Driver: Report
            // Driver: Push
            // Driver: Pull request
            
           

    }
    
    /** after clone set up files in local repo **/
    private void setUpfiles()
    {
        // set up the local git repository
            if(!localPath.isEmpty())
            {
                System.out.println("reposcan");
                Driver.admin.addReposAccessed();
                SuccessFlag =true;
            }
            bugHive = new BugHive();
            
            
            File fileFind = new File(git.getRepository().getDirectory().getParent());
            Path path = fileFind.toPath();
            getFiles(allFiles, path);
            
             finish(); // move from queue to record
    }
    
    /** fork from github ***/
    private void Fork()
    {
        String user = getUsername().substring(1).trim();    // these methods extract the userame and reponame
        String repo = getReponame().substring(1).replace(".git", "").trim();
        try{
            GitHubClient client = new GitHubClient();
		client.setCredentials("BugBot16", args[1]); // bugbots github credentials
		RepositoryService service = new RepositoryService(client);
		RepositoryId toBeForked = new RepositoryId(user, repo);
		service.forkRepository(toBeForked);
                
            }catch(IOException e)
            {
               e.printStackTrace();
            }
    }
    /** github clone **/

     private void Clone()
    {
        // github credentials
         CredentialsProvider cd = new UsernamePasswordCredentialsProvider("BugBot16", args[0]);
        String repo= getReponame();
         try {
          
            git = Git.cloneRepository()     //links the local and remote copies hrough a git connection
                    .setURI("https://github.com/BugBot16" + repo)
                    .setCredentialsProvider(cd)
                    .setDirectory(new File(localPath))
                    .call();
        } catch (GitAPIException ex) {
            finish();   // if exception is caught clling the finish method ensures BugBot is not stuck in an infinite while loop
          // ex.printStackTrace();
        }
    }
    
    public void Pull()
    {
        //github credentials
        CredentialsProvider cd = new UsernamePasswordCredentialsProvider("BugBot16", "myfyp16bugbot");
        PullCommand pullCmd = git.pull()
                .setCredentialsProvider(cd)
                .setRemote("origin")
                .setStrategy(MergeStrategy.THEIRS);
              // try and catch the pull request
        try {
            PullResult result = pullCmd.call();
        FetchResult fetchResult = result.getFetchResult();
        MergeResult mergeResult = result.getMergeResult();
        System.out.println(mergeResult.getMergeStatus());  
            } catch (GitAPIException e) {
             // e.printStackTrace();  
            }
        
        
        System.out.println("PULL REQUEST");
    }
    private String getUsername()
    {
        // github repository URLS are always in the form www.github.com/username/reponame
            char[] urlarr = this.URL.toCharArray();
            char[] temp = new char[500];
            int counter=0;
            int j=0;
               // reads from the .com to the second forward slash
                    for(int q = 18; q< urlarr.length; q++)
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
                
            String username = new String(temp);
            return username;
    }
    
    // similar to the get username method
    private String getReponame()
    {
         char[] urlarr = this.URL.toCharArray();
            char[] temp = new char[500];
            int counter=0;
            int j=0;
                
                    for(int q = 18; q< urlarr.length; q++)
                    {
                        char c = urlarr[q];
                        if(c=='/')
                        {
                            counter++;
                        }
                        if(counter==2)
                        {
                            temp[j]=c;
                            j++;
                        }
                    }
                
            String reponame = new String(temp).trim();
            if(reponame.endsWith(".git.git"))   //double check the user has not entered the direct request with .git ending
                reponame = reponame.substring(0, reponame.length()-4);
            
            return reponame;
    }
    
    
    // method to parse through the repository homepage HTML
     private void extractMETArepo(String url)
    {
        Document meta = null;
        try {
            meta = Jsoup.connect(url+"/overview")
                    .userAgent(userAgent)//USER Agent
                    .get();
        } catch (IOException ex) {
            Logger.getLogger(RepoScan.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(url);
       // System.out.println(meta.toString());
        if(meta.toString().contains(" Language\n" +
"             </dt> \n" +
"             <dd title=\"Java\""))
        {
           System.out.println("JAVA FILE");
           record = record+"JAVA,";
        }
        else if(meta.toString().contains(" Language\n" +
"             </dt> \n" +
"             <dd title=\"C\"")){
            System.out.println("C");
            record = record + "C,";
        }
        else if(meta.toString().contains(" Language\n" +
"             </dt> \n" +
"             <dd title=\"C++\"")){
            System.out.println("C++");
            record = record + "C++,";
        }
        else{
            System.out.println("unsupported language");
            record = record+"unsupported,";
        }
    }
    
    // initialise some variables
    private void Init()
    {
        // some default variable values
        variables[0][varCount]="String";
        variables[2][varCount]= "[string]";
        variables[1][varCount]= "null";
        varCount++;
        variables[0][varCount]="special";
        variables[2][varCount]= "[*var]";
        variables[1][varCount]= "[*var]"; //cant be default due to bug j_idN
        varCount++;
    }
    
    
    private void getFilecount()
    {
        List<Character> newFilename = new ArrayList<Character>(); 
         
            try
            {
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/Desktop/BugBotConfig.txt"));
            String line;
             while ((line = reader.readLine()) != null)
             {
                 //System.out.println(line);
                  if(line.indexOf("FilenameCount=")!=-1)
                  {
                      int pos = line.indexOf('=');
                       char[] linearr = line.toCharArray();
                       
                  for(int j =pos+1; j<linearr.length;j++)
                  {
                          newFilename.add(line.charAt(j));
                       }
                  }  
             }
            reader.close();
         }
            catch(IOException e){e.printStackTrace();}
    
                  StringBuilder builder = new StringBuilder(newFilename.size());
                  for(Character c: newFilename)
                  {
                      builder.append(c);
                  }
                  // the current number of files stored locally
                  // this information is needed as the clones are saved in files corresponding to their clone order i.e. File22
                  FileCount = Integer.parseInt(builder.toString());
                  
                 // System.out.print("FILE COUNT =" + FileCount);
                 
    }
    
    // increments the filecount and saves the new vale to the BugBotConfig.txt file for future use
    public void setFilecount()
    {
        List<String> ConfigLines = new ArrayList<String>(); 
        try {
           
            BufferedReader reader = new BufferedReader(new FileReader("C:/Users/user/Desktop/BugBotConfig.txt"));
            String line;
             while ((line = reader.readLine()) != null)
             {
                ConfigLines.add(line);
                  if(line.indexOf("FilenameCount=")!=-1)
                  {
                      
                      ConfigLines.remove(line);
                      String Filename = "FilenameCount=" + FileCount;
                      ConfigLines.add(Filename);
                      // System.out.println(Filename);

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
           
        } catch (IOException ex) {
           // Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // retrieve all the files in the repositories
    private List<File> getFiles(List<File> fileNames, Path dir) {
        Driver.admin.setStatus("Scanning");
    try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
        for (Path path : stream) {
            if(path.toFile().isDirectory()) {
                getFiles(fileNames, path);
            } else {
                fileNames.add(path.toFile());
               //System.out.println(path.toString());
            }
        }
    } catch(IOException e) {
        e.printStackTrace();
    }
    return fileNames;
    }
    
    // get the files ending in file extensions of languages bugbot supports
    public void getScannableFiles()
    {
        for(File filterfile : allFiles)
     {
         if( filterfile.getPath().endsWith(".java"))
         {
             filtered.add(filterfile);
             //System.out.println("FILE ADDED");
         }
         else  if( filterfile.getPath().endsWith(".c") | filterfile.getPath().endsWith(".cpp"))
         {
             // these are sent to the RepoScanC class
             filteredC.add(filterfile);
         }
     }
        
    }
    
public void Scan() 
    {
        // added to the data.csv file
        record = record+ filtered.size()+ "javaF,";
        record = record+ filteredC.size()+ "cF,";
        for( File scanfileC : filteredC)
        {// C files sent to RepoScannC class
            RepoScanC cfile = new RepoScanC(scanfileC.toString());
        }
        for( File scanfile : filtered)  // for each java file
     {
         //clearing variables array for each file
           for(int i = 0; i<varCount; i++)
        {
            variables[0][i]= null;
            variables[1][i]= null;
            variables[2][i]= null;
        }
        varCount =0;
       // clear all lines from prev file
         linepos =0;
         lines.clear();
         try (BufferedReader br = new BufferedReader(new FileReader(scanfile))) {
             //System.out.println("FILE ACCESSED READ");
                String line;
                 while ((line = br.readLine()) != null) {
                     lines.add(line);   // add each line to an arrayList of strings
                    
            }
                 
                totalLines += lines.size();
                 BugScan(lines, scanfile);  // scans the lines of this file
        }   catch (FileNotFoundException ex) {
                Logger.getLogger(RepoScan.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RepoScan.class.getName()).log(Level.SEVERE, null, ex);
            }
         catch(Exception e)
         {
             System.err.println("scan error");
             e.printStackTrace();
             
         }
     }
        record = record+ totalVars + "V,";
        record = record + totalLines + "L,";
    }
    
    private int bugNum;
    private void BugScan(List<String> lines, File fixfile) throws IOException
    {
        bugpresentFile = false;
        Driver.admin.setStatus("Identifying");
        Init();
         Pattern pattern = Pattern.compile("");
         Matcher matcher = pattern.matcher("");
        System.out.println( "FILE: " + fixfile);
         for(String line:lines)     // for each line
         {
            linepos++;
            /////////// RECORDING VARIABLES CODE/////////////////////////
            recordVars(line);
            recordDefaultVars(line);
             /////////// DATABASE SEARCH CODE/////////////////////////
            // System.out.println(bugHive.dblength());
            Driver.admin.setBugsNum(bugHive.dblength());
            for(int i=1; i<= 8; i++)                    // java bugs
            {
                //System.out.println("for each bug");
                String regex = bugHive.getRegEx(i);     // get regex of current bug
                //System.out.print(regex);
                pattern = Pattern.compile(regex);      
                matcher = pattern.matcher(line);
                while (matcher.find()) {                // match bug regex on line
                            relevantVars.clear();
                            
                            // further analysis of bug if matched
                            // analyseBug returns true or false for definitve match
                            if(analyseBug(matcher.group(),i))
                            {
                                System.out.println("I found the text '" +
	                    matcher.group()+ "' starting at index " + matcher.start() +
	                    " and ending at index " + matcher.end() + " around line: " + linepos);
                            System.out.println("BUG_ID: " + bugHive.getBugID(i));
                            
                            // increment the number of this type of bug found in bugbotconfig.txt
                                Driver.admin.updateBugtypeStats(bugHive.getBugID(i));
                                bugNum=i;
                                //System.out.println("Bug present: true");
                                bugpresentFile=true;    // set flags
                                bugpresentRepo = true;
                                bugCount++;
                                Driver.admin.addBugsFound();
                                String fix =bugHive.getFix(i);
                                
                                if(bugNum==2 && matcher.group().contains("!"))
                                    fix="!" + fix;
                                
                                // fix the bug
                                Fixline(lines, linepos,fix,matcher.group());
                                // add to the BugBotReport of this repository
                                reportLines.add("BugBot found: " + bugHive.getBugID(i)+ ": "+ bugHive.getDescription(i)
                                + " around line: " + linepos + " in file " + fixfile.toString());
                            }     
                            else
                            {
                               // System.out.println("Bug present: false");
                            }
                }
            } 
             } 
         totalVars= totalVars +declaredVars;
            // fixing entire file i.e re-write and commit the file.
        if(bugpresentFile)
         Fix(fixfile, lines);
         
         
    } 
    private boolean getVariationofBug(String Bug)
    {
        BugVarNames = new ArrayList<String>();
            for(int i =0; i<varCount; i++)      // parse through variable array to find matching variables
             {  
                 
                if(Bug.contains(variables[1][i])){
                    // System.out.println(variables[1][i]);
                    BugVarNames.add(variables[1][i]);
                }
             }
            if(BugVarNames.size()>=2)
            {
                if(BugVarNames.size()==2)
                {
                    String varY = BugVarNames.get(1);
                    BugVarNames.add(0, varY);
                    relevantVars = BugVarNames;
                    return true;
                }
            }
            else {
                return false;
                }
        return false;
    }
    
    private boolean analyseBug(String Bug, int bugNum)
    {
        boolean bugmatch=false;
        relevantVars.clear();
        // this bug is not a bug if one of the variables is null
        if(bugNum==2 && Bug.contains("null"))
        {
            return false;
        }
        else if(bugHive.getVariations(bugNum)) // if the bug has variations set to 1
        {                                       // ensure correct bug was identified
            return getVariationofBug(Bug);
             
        }
        else if(bugHive.getVarDepend(bugNum))
        {
           String form= bugHive.getGeneralForm(bugNum);
           relevantVars =extractVars(form);
           
           if(compareVars(Bug, relevantVars))
           {
               bugmatch = true;
           }
        }
        else{ 
            bugmatch =true; 
        } //not var dependant and no variation
            
        return bugmatch;
    }
    private void getUndeclaredintlong(String line)
    {
        Pattern pattern = Pattern.compile("\\d+");      
         Matcher matcher = pattern.matcher(line);
         try{
                while (matcher.find()) {
                    
                    // check for int or long vars
                    if(line.charAt(matcher.start()-1) != '"' && line.charAt(matcher.start()-1) != '.'  )
                    {
                    if( Long.parseLong(matcher.group())>Integer.MIN_VALUE && Long.parseLong(matcher.group())<Integer.MAX_VALUE)
                    {
                    variables[0][varCount]="int";
                    variables[2][varCount]= "[int]";
                    variables[1][varCount]=matcher.group();
                    varCount++;
                    }
                    else
                    {
                    variables[0][varCount]="long";
                    variables[2][varCount]= "[long]";
                    variables[1][varCount]=matcher.group();
                    varCount++;
                    }
                    }
                }
         }
         catch(Exception e)
         {
             System.out.println("default variable error 1");
         }
    }
    
    private void getUndeclareddoublefloat(String line)
    {
         // check for double or float vars
         Pattern pattern = Pattern.compile("((\\d+)(\\.)(\\d+))");      
         Matcher matcher = pattern.matcher(line);
         try{
         while (matcher.find()) {
              if(line.charAt(matcher.start()-1) != '"' ){
                    if(Double.parseDouble(matcher.group())>Float.MIN_VALUE && Double.parseDouble(matcher.group())<Float.MAX_VALUE)
                    {
                    variables[0][varCount]="float";
                    variables[2][varCount]= "[float]";
                    variables[1][varCount]=matcher.group();
                    varCount++;
                    }
                    else
                    {
                    variables[0][varCount]="double";
                    variables[2][varCount]= "[double]";
                    variables[1][varCount]=matcher.group();
                    varCount++;
                    }
                }
         }
         }catch(Exception e)
         {
             System.out.println("defaut variable error type 2");
         }
    }
    
    private void getUndeclaredcharString(String line)
    {
         // check for string or char variables
         Pattern pattern = Pattern.compile("\\\".*?\\\"");      
         Matcher matcher = pattern.matcher(line);
          while (matcher.find()) {
                    variables[0][varCount]="String";
                    variables[2][varCount]= "[string]";
                    variables[1][varCount]=matcher.group();
                    varCount++;
          }
         pattern = Pattern.compile("\\'.?\\'");      
         matcher = pattern.matcher(line);
          while (matcher.find()) {
                    variables[0][varCount]="char";
                    variables[2][varCount]= "[char]";
                    variables[1][varCount]=matcher.group();
                    varCount++;
          }

    }
    
    // undeclared variables 
    private void recordDefaultVars(String line)
    {
        //get integers or long variables
         getUndeclaredintlong(line);
         // get double or float variables
         getUndeclareddoublefloat(line);
         //get character or String variables
         getUndeclaredcharString(line);   
    }
    
    private void getBugVars(String Bug)
    {
               
        for(int i =0; i<variables[1].length; i++)
        {  
            try{
                String p ="((\\s|\\W)"+variables[1][i]+ "(\\W|\\s))";
            Pattern pattern = Pattern.compile(p); 
            Matcher matcher = pattern.matcher(Bug);
            while(matcher.find())
            {
              //System.out.println("here " + matcher.group());
               BugVars.add(variables[0][i].toLowerCase());   // detect&save the variables types
               //System.out.println(variables[1][i]);
               BugVarNames.add(variables[1][i]);
            }
            }
            catch(Exception patternsyntaxexcep)
            {
                //System.out.println(variables[0][i]+ "  " + variables[1][i]);
                //System.out.println("variable err");
            }
        }
    }
    List<String> BugVars;
    List<String> BugVarNames;
    private boolean compareVars(String Bug, List<String> relevantVars)
    {  
        BugVars = new ArrayList<String>();
        BugVarNames = new ArrayList<String>();
        String varType=null;
        int numMatches=0;
        int count=0;
        boolean match=false;
        Bug = " " + Bug + " ";
        //System.out.println(Bug);
        
            // go through each recorded variable, get the varType of the variables within the bug
            Pattern pattern1 = Pattern.compile("\\={1}"); 
            Matcher matcher1 = pattern1.matcher(Bug);
            while(matcher1.find())
            {
                Bug= Bug.replace("="," = " );
            }
            
            //get the variable names and tyoe from the bug
            // this populates the BugVar Array and BugVarName Array 
            getBugVars(Bug);
            
            
            
        for(int j=0; j<BugVarNames.size(); j++)
        {
                if(BugVars.get(j).equals(relevantVars.get(numMatches).replace("[", "")))
                {
                    numMatches++;
                    
                }
                else
                {
                    numMatches=0;
                    for(int remove=j-1; remove>=0;remove--)
                    {
                        BugVarNames.remove(remove); //remove all vars upto this var due to non mtch
                    }
                }
                
                if(numMatches==relevantVars.size())
                {
                    match=true;
                    return match;
                }
        }
        /**System.out.println(match);
        *for(int r=0 ; r<relevantVars.size(); r++)
        *{
        *    try{
        *        // this was for a bug that is now removed from bughive
        *    if(relevantVars.get(r).equals("[*var"))
        *    {
        *        match = !(BugVars.get(0).equals("string") && BugVars.get(1).equals("string"));
        * 
        *    }
        *    }
        *    catch(IndexOutOfBoundsException e)
        *    {
        *        System.out.println("Equals method for different var types limit");
        *    }
        }*/
        
       
        return match;
       
    }       
    // extract the variables from the bug definitions general form
    List<String> relevantVars = new ArrayList<String>();
    private List<String> extractVars(String form)
    {
        
        List<String> numVars = new ArrayList<String>();
         Pattern pattern = Pattern.compile("\\[((\\*)*(\\w|\\d)+)");      
         Matcher matcher = pattern.matcher(form);
                while (matcher.find()) {
                    if(matcher.group().contains("int")){
                            numVars.add("[int");
                    }
                    else
                    {
                        //System.out.println(matcher.group());
                        numVars.add(matcher.group());
                    }
                }
                return numVars;
                
                
    }
    private void multDecVariables(String curline)
    {
        Pattern multdeclarepat = Pattern.compile("((int)|(short)|(long)|(float)|(double)|(char)|(boolean)|(String))\\s*((\\w|\\d)+)\\s*(\\,\\s*((\\w|\\d)+)\\s*)+\\;");
         Matcher matchMultdec = multdeclarepat.matcher(curline);
          while (matchMultdec.find()) {
             varType="N/A";
            name = "N/A";
            value = "N/A";
           String add = matchMultdec.group();
          
            Pattern var =Pattern.compile("((int)|(short)|(long)|(float)|(double)|(char)|(boolean)|(String))");
            Matcher matchvar = var.matcher(add);
            while(matchvar.find()){
                varType = matchvar.group();
            }
            Pattern nam =Pattern.compile("((\\w|\\d)+)\\s*(\\,|\\;)");
            Matcher matchname = nam.matcher(add);
            while(matchname.find()){
                temp = matchname.group();
                  //      System.out.println("matchmd");
                Pattern delim= Pattern.compile("((\\w|\\d)+)");
                Matcher matchDelim = delim.matcher(temp);
                while(matchDelim.find())
                {
                    name=matchDelim.group();
                    variables[0][varCount] = varType;
                    variables[1][varCount] = name;
                    variables[2][varCount] = "N/A";
                    varCount++;
                    declaredVars++;
                }
            }    
        }   
    }
    
    private void assignedVar(String curline)
    {
       Pattern assignpat = Pattern.compile("((int)|(short)|(long)|(float)|(double)|(char)|(boolean)|(String))\\s*((\\w|\\d)+)\\s*\\=\\s*((\\\"(.+)\\\")|(\\'.?\\')|(\\d+\\.\\d+)|((\\w|\\d)+)|(.+))\\s*\\;");
        Matcher matchAssign =assignpat.matcher(curline);
        
                while (matchAssign.find()) {
           // System.out.println("found:" + matchAssign.group());
            varType="N/A";
            name = "N/A";
            value = "N/A";
            String add = matchAssign.group();
           
            Pattern var =Pattern.compile("((int)|(short)|(long)|(float)|(double)|(char)|(boolean)|(String))");
            Matcher matchvar = var.matcher(add);
            while(matchvar.find()){
                
                varType = matchvar.group();
               // System.out.println("found:" + varType);
            }
            Pattern nam =Pattern.compile("(\\s*((\\w)+)\\s*\\=){1}");
           // System.out.println(add);
            Matcher matchname = nam.matcher(add);
            while(matchname.find())
            {
                temp = matchname.group();
                Pattern delim = Pattern.compile("((\\w|\\d)+)");
                Matcher matchDelim = delim.matcher(temp);
                while(matchDelim.find())
                {
                  name= matchDelim.group();
                }
            }
            
            Pattern val = Pattern.compile("\\=\\s*((\\\"(.+)\\\")|(\\'.?\\')|(\\d+\\.\\d+)|((\\w|\\d)+))\\s*");
            Matcher matchval = val.matcher(add);
            while(matchval.find())
            {
                temp = matchval.group();
                Pattern delim = Pattern.compile("((\\\"(.+)\\\")|(\\'.?\\')|(\\d+\\.\\d+)|((\\w|\\d)+))");
                Matcher matchDelim = delim.matcher(temp);
                while(matchDelim.find())
                {
                    
                    value= matchDelim.group();
                    if(value.contains("\""))
                    {
                        varType="String";
                    }
                    else if(value.equals(null))
                    {
                        varType="String";
                    }
                   
                }
            }
            variables[0][varCount] = varType;
            variables[1][varCount] = name;
             variables[2][varCount] = value;
           varCount++;
           declaredVars++;
                }
    }
    
    private void noAssignVar(String curline)
    {
        Pattern noassignpat = Pattern.compile("((int)|(short)|(long)|(float)|(double)|(char)|(boolean)|(String))\\s*((\\w|\\d)+)\\s*\\;");
        Matcher matchNoAssign = noassignpat.matcher(curline);
         while (matchNoAssign.find()) {
             varType="N/A";
            name = "N/A";
            value = "N/A";
           
            String add = matchNoAssign.group();
            
            Pattern var =Pattern.compile("((int)|(short)|(long)|(float)|(double)|(char)|(boolean)|(String))");
            Matcher matchvar = var.matcher(add);
            while(matchvar.find()){
                varType = matchvar.group();
            }
            Pattern nam =Pattern.compile("\\s*((\\w|\\d)+)\\s*");
            Matcher matchname = nam.matcher(add);
            while(matchname.find()){
                name = matchname.group();
            }
            
            variables[0][varCount] = varType;
            variables[1][varCount] = name;
            variables[2][varCount] = "N/A";
                    varCount++;
                    declaredVars++;
        }
    }
    
    // declared variables
    private void recordVars(String curline)
    {
        multDecVariables(curline);  // e.g int i,j,k;
        assignedVar(curline);       // e.g int i=9;
        noAssignVar(curline);       // e.g int i;

    }
    
    private void printVars()
    {
        System.out.println("VarType" +"\t"+ "Name" +"\t"+ "Value" + "\n");
        for(int i=0; i<varCount; i++)
        {
        System.out.println(variables[0][i] +"\t"+ variables[1][i] +"\t"+ variables[2][i] + "\n");
        }
         System.out.println("----------------------------------------------------------------------------------");
    }
    
    private List<String> Fixline(List<String> lines, int pos, String bugfix, String Bug)
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
            newLine =setVars(bugfix, OGline, Bug);
        }
        
       
         lines.set(pos-1, newLine);
        
         return lines;
    }
    
    // set the variables form the bug into the fix in the appropriate position
    private String setVars(String bugfix, String origLine, String Bug)
    {
       
         String newLine=null;
         // print outs for debugging
          for(int i=0; i<BugVarNames.size();i++)
          {
            // System.out.println(BugVarNames.get(i));
          }
          List<String> fixvars = extractVars(bugfix);
        // System.out.println(relevantVars.size());
         //System.out.println(fixvars.size());
          
          if(fixvars.size()==relevantVars.size())
          {
             for(int i=0; i<relevantVars.size();i++)
            {
              bugfix=bugfix.replaceFirst("\\[((\\w|\\d)+)\\]", BugVarNames.get(i));
            }
          }
          if(fixvars.size()>relevantVars.size())
          {
              for(int i=0; i<fixvars.size();i++)
              {
                bugfix=bugfix.replaceFirst("\\[((\\w|\\d)+)\\]", BugVarNames.get(0));
              }
          }
          System.out.println(origLine);
         
          
          newLine = origLine.replace(Bug, bugfix);
          System.out.println(newLine);
          return newLine;
    }
    
    // rewrites a file
    public static void Fix(File fixfile, List<String> lines) 
    {
            Driver.admin.setStatus("Debugging");
        FileWriter fw = null;
        try {
            fw = new FileWriter(fixfile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(String line: lines)
            {
                if(line.contains("\\\\n"))
                {
                    //System.out.println("here");
                    String split1 = line.substring(0, line.indexOf("\\"));
                    String split2 = line.substring(line.indexOf("\\\\n"), line.length());
                    String split2a = split2.replaceAll("\\\\n", " ");
                    String split2b = split2a.replaceAll("\\\\", "");
                    bw.write(split1);
                    bw.newLine();
                    bw.write(split2b);
                }
                else{
                     bw.write( line );
                }
                bw.newLine();
            }
            bw.flush();
                 bw.close();
            System.out.println("FIXED");
        } catch (IOException ex) {
            Logger.getLogger(RepoScan.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
           
        try {
            Commit();
        } catch (GitAPIException ex) {
            Logger.getLogger(RepoScan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private static void Commit() throws GitAPIException
    {
        Driver.admin.setStatus("Pushing");
        //printVars();
        if(bugpresentRepo)
        {
        git.commit()
                        .setAll(true)
                        .setMessage("BugBot Fixed this file")
                        .call();


                System.out.println("Committed all changes to repository at " + git.getRepository().getDirectory());
        }
    }
    

    
    // pushes a file back to the remote repository if bugs were fixed and committed
    public void Push() throws GitAPIException
    {
        try{
        if(bugpresentRepo)
        {
            // Github credentials
        CredentialsProvider cd = new UsernamePasswordCredentialsProvider("BugBot16", "myfyp16bugbot");
        PushCommand pushCommand = git.push();
        pushCommand.setCredentialsProvider(cd);
        pushCommand.setRemote( "origin" );
        pushCommand.setRefSpecs( new RefSpec( "master" ) );
        pushCommand.call();
        
        System.out.println("Pushed Changes!!");
        }
        }catch(Exception e)
        {
            System.err.println("Push exception");
        }
    }
    // this method is linked to the data.csv file 
    public static void recordData()
    {
            /*** record string was added to in the following order
             * URL
             * LANGUAGE
             * #JAVA FILES
             * #C FILES
             * #JAVA DECLARED VARIABLES
             * #LOC
             * TIME TO SCAN 
             * #BUGS FOUND
             */
        
            record = record+ bugCount + "B,";   
        BufferedWriter writer = null;
        try {

            writer = new BufferedWriter(new FileWriter("C:/Users/user/Desktop/DATA.txt", true));
            writer.newLine();
            writer.append(record);
        } catch (IOException ex) {
            Logger.getLogger(RepoScan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(RepoScan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // this method is called on the succssful completion of a git clone, 
    // the method is also called if an error has occured or an exception is caught
    // to ensure BugBot does not keep trying to scan the same repo
    public final void finish()
    {
        
        // this method adds the repository to records as well as removes it from the queue
        RecordDB = new CrawlDB();
        Driver.admin.setStatus("Finishing");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
         if(this.URL.contains("github.com"))
        {
            try {
                timeStamp = WebCrawlgithub.getCreateDate(rootURL);  // create date i.e. the commit time from the meta data.
            } catch (IOException ex) {
                Logger.getLogger(CrawlDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String insrt = "INSERT INTO Record(URL, TimeCreated) VALUES (\"" +this.URL + "\",\"" + timeStamp+"\")";
        try {
            RecordDB.runSql2(insrt);        // insert this repository into records
        } catch (SQLException ex) {
            Logger.getLogger(RepoScan.class.getName()).log(Level.SEVERE, null, ex);
        }
        Qdb = new QueueDB();        // remove the repository from the queue.
        Qdb.removeFirst();
            
    }
    
    // this report will be added to the text file BugBotReport.
    public void Report()
	{
            // this line is added every time this method is accessed
                reportLines.add("For more information visit www.BugBot.org");
		if(bugpresentRepo)
		{
                    System.out.println("reporting");
                        try {
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(localPath +"\\BugBotReport.txt"), "utf-8"));
                            // report lines were appended after each identification of a bug
                            for(String line: reportLines)
                        {
                             writer.write( line);
                             writer.newLine();
                        }
                            writer.flush();
                            writer.close();
                             git.add().addFilepattern(localPath).addFilepattern("BugBotReport.txt").call();
                   // this report is commited sepeartely to all bug fixes
                             git.commit()
                         .setAll(true)
                        .setMessage("BugBot Report")
                        .call();


                        } catch (Exception ex) {
                            Logger.getLogger(RepoScan.class.getName()).log(Level.SEVERE, null, ex);
                        }
		}
		else
		{
			System.out.println("NO BUGS FOUND");
		}
	}     
 
}
