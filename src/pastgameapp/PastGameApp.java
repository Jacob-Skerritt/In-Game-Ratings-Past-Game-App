/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pastgameapp;

import Database.Config;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anyone
 */
public class PastGameApp implements Runnable {
    private String id;
    
    public PastGameApp(String id){
        this.id = id;
    }

    @Override
    public void run() {
        
        Config database;
        try {
            
            database = new Config("jdbc:mysql://localhost/in_game_ratings", "root", "");
            try (Connection db = database.getDatabaseConnection()) {
                System.out.println(db);
                System.out.println("The id is : " + this.id);
            }
        } catch (SQLException | PropertyVetoException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
