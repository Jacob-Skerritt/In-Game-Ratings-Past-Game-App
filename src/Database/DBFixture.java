/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author anyone
 */
public class DBFixture {
    
        public static int determineFixtureId(Connection db) throws Exception{
      
        int newId = -1;
        try {
              
            // the mysql insert statement
            String query = " SELECT COUNT(*) from fixtures where id < 1000";

            PreparedStatement preparedStmt = db.prepareStatement(query);

            // execute the query, and get a java resultset
            ResultSet rs = preparedStmt.executeQuery();

            // iterate through the java resultset
              // iterate through the java resultset
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    int count = rs.getInt("COUNT(*)");

                   newId = count;

                }
                preparedStmt.close();
                
            }
            
            if(newId == -1)
                throw new Exception("Invalid, unable to generate fixture id");
             
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         
        return newId;
        
       
    }
    
}
