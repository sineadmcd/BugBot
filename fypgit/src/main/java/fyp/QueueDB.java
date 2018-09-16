/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class QueueDB {
     public Connection conn = null;
     String args[] = null;
    public QueueDB()
    {
        try {
			String url = "jdbc:mysql://danu6.it.nuigalway.ie:3306/mydb2831?zeroDateTimeBehavior=convertToNull";
			conn = DriverManager.getConnection(url, "mydb2831ms",args[0]); // database credentials
                        
			//System.out.println("conn built to Queue");
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
        try {
            stat.executeUpdate("INSERT INTO `mydb2831`.`Queue` (URL,TimeCreated) " 
                    + "VALUES ('" + url +"','" + time+"')");
        } catch (SQLException ex) {
            Logger.getLogger(CrawlDB.class.getName()).log(Level.SEVERE, null, ex);
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
        
        //returns the URL of the first repository in the queue
        public String getFirst()
        {
            String url=null;        //URL as String
            ResultSet rs;
             try {
             rs = runSql("SELECT * FROM Queue");    //selects entire Queue
                if(rs.first()){                     // get first result
                   url= rs.getString("URL");        // set url
                }
            } catch (SQLException ex) {
                Logger.getLogger(QueueDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            return url;
        }
        
        public void removeFirst()
        {
            ResultSet rs;
         try {
             rs = runSql("SELECT * FROM Queue");
             
             if(rs.first()){
               String url= rs.getString("URL");
               String removeSta = "DELETE FROM Queue WHERE URL=\"" + url + "\"";
               boolean y = runSql2(removeSta);
            }
             
         } catch (SQLException ex) {
             Logger.getLogger(QueueDB.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
        
        public int getLength()
        {
            int l =0;
            
         try {
             ResultSet rs =runSql("SELECT * FROM Queue");
            if(rs.last()){
            return rs.getRow();
            }
              
         } catch (SQLException ex) {
             Logger.getLogger(QueueDB.class.getName()).log(Level.SEVERE, null, ex);
         }
         return l;
       
        }
 
	@Override
	protected void finalize() throws Throwable {
		if (conn != null || !conn.isClosed()) {
			conn.close();
		}
	}

}

