/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class BugHive {
    
    private Connection bugdb;
    private ResultSet rs;
    private Statement stmt;
    private String SQL ;
    
    public BugHive()
    {
        
        //System.out.println("conn built to BugHive");
        String uname = "mydb2831ms";    // database credentials
        String args[] = null;
       String pword = args[0];
       String host ="jdbc:mysql://danu6.it.nuigalway.ie:3306/mydb2831?zeroDateTimeBehavior=convertToNull";
        try {
            bugdb = DriverManager.getConnection(host, uname,pword);
            stmt = bugdb.createStatement();
            SQL = "SELECT * FROM BugHive";
            
        } catch (SQLException ex) {
            Logger.getLogger(BugHive.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public int dblength()
    { 
        int count=0;
        try {
            rs = stmt.executeQuery( SQL );
            while(rs.next())
            {
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BugHive.class.getName()).log(Level.SEVERE, null, ex);
        }
         return count;
    }
    
    public String getRegEx(int i) 
    {
        String reg=null;    // the regular expression pattern returned as Sttring
        try {
            rs = stmt.executeQuery( "SELECT * FROM BugHive WHERE Num=" +i+ ";" );
                // returns a result set of 1 as numbers are unique identifiers
                if(rs.next()){      
                // returns the Regular Expression value of the element in RS
                reg = rs.getString("RegularExpression");
                //System.out.println(reg);
                }
        } catch (SQLException ex) {
            Logger.getLogger(BugHive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reg;
    }
    
     public String getDescription(int i) 
    {
        String desc=null;
        try {
            rs = stmt.executeQuery( "SELECT * FROM BugHive WHERE Num=" +i+ ";" );
                if(rs.next()){
                desc = rs.getString("Description");
                //System.out.println(reg);
                }

        } catch (SQLException ex) {
            Logger.getLogger(BugHive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return desc;
    }
    
    public String getBugID(int i)
    {
         String ID=null;
        try {
            rs = stmt.executeQuery( "SELECT * FROM BugHive WHERE Num=" +i+ ";" );
                if(rs.next()){
                ID = rs.getString("BUG_ID");
                //System.out.println(reg);
                }

        } catch (SQLException ex) {
            Logger.getLogger(BugHive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ID;
    }

    
    public boolean getVarDepend(int i) 
    {
        int var=2;
        boolean vardep = false;
        try {
            rs = stmt.executeQuery( "SELECT * FROM BugHive WHERE Num=" +i+ ";" );
            while(rs.next())
            {
               var = rs.getInt("VariableDependant");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BugHive.class.getName()).log(Level.SEVERE, null, ex);
        }
        // return this as boolean values
        if(var==0)
        {
            vardep=false;
        }
        else if(var==1)
        {
            vardep=true;
        }
        
        return vardep;
    }
    
    public boolean getVariations(int i)
    {
       int var=2;
        boolean variations = false;
        try {
            rs = stmt.executeQuery( "SELECT * FROM BugHive WHERE Num=" +i+ ";" );
            while(rs.next())
            {
               var = rs.getInt("Variations");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BugHive.class.getName()).log(Level.SEVERE, null, ex);
        }
        // return as boolean values
        if(var==0)
        {
            variations=false;
        }
        else if(var==1)
        {
            variations=true;
        }
        return variations;
    }
    // all getter methods are the same except for change if column selected
    public String getGeneralForm(int i)
    {
        String form=null;
        try {
            rs = stmt.executeQuery( "SELECT * FROM BugHive WHERE Num=" +i+ ";" );
                if(rs.next()){
                form = rs.getString("GeneralString");
                //System.out.println(reg);
                }

        } catch (SQLException ex) {
            Logger.getLogger(BugHive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return form;
    
    }
    public String getCommitMsg(int i)
    {
        String form=null;
        try {
            rs = stmt.executeQuery( "SELECT * FROM BugHive WHERE Num=" +i+ ";" );
                if(rs.next()){
                form = rs.getString("CommitMessage");
                //System.out.println(reg);
                }

        } catch (SQLException ex) {
            Logger.getLogger(BugHive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return form;
    
    }
    
     public String getFix(int i) 
    {
        String fix=null;
        try {
            rs = stmt.executeQuery( "SELECT * FROM BugHive WHERE Num=" +i+ ";" );
                if(rs.next()){
                fix = rs.getString("Fix");
                //System.out.println(reg);
                }

        } catch (SQLException ex) {
            Logger.getLogger(BugHive.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fix;
    }   
     
     public void getCBugs()
             {
                 
             }
}
