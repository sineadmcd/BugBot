/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author user
 */
public class CrawlDB {
    public Connection conn = null;
    
    public CrawlDB()
    {
        try {
			String url = "jdbc:mysql://danu6.it.nuigalway.ie:3306/mydb2831?zeroDateTimeBehavior=convertToNull";
			conn = DriverManager.getConnection(url, "mydb2831ms","vi3mon");
                        
			//System.out.println("conn built to Records");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	
    }
    
    public Statement create()
    {
        Statement statement = null;
        try {
           statement = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CrawlDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement;
    }
    
    public void executeUp(Statement stat, String url, String time)
    {
        if(url.contains("github.com"))
        {
            try {
                time = WebCrawlgithub.getCreateDate(url);
                System.out.println("********** " + time);
            } catch (IOException ex) {
                Logger.getLogger(CrawlDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            stat.executeUpdate("INSERT INTO `mydb2831`.`Record` (URL,TimeCreated) " + "VALUES ('" + url +"','" + time+"')");
        } catch (SQLException ex) {
            System.out.println("SQL ERROR, Cant execute update");
        }
    }
 
    public  ResultSet runSql(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
 
	public boolean runSql2(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.execute(sql);
	}
        
            public  ResultSet runSql3(String sql) throws SQLException {
		Statement sta = conn.createStatement();
		return sta.executeQuery(sql);
	}
 
	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
                
                int i=0;
                String str1 ="0";
                if(str1.equals(Integer.toString(i)))
                {
                    // BugBot identifide this as a bug
                }
                
                
                
                
	}

}
