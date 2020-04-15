/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pastgameapp;

import Database.Config;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author anyone
 */
public class PastGameApp implements Runnable {
    private String id;
    private int startTime;
    private JSONObject pastFixture;
    
    public PastGameApp(String id, String startTime){
        this.id = id;
        this.startTime = Integer.parseInt(startTime);
    }

    @Override
    public void run() {
        
        Config database;
        try {
            
            database = new Config("jdbc:mysql://localhost/in_game_ratings", "root", "");
            try (Connection db = database.getDatabaseConnection()) {

                
                 getPastFixtureData();
            }
        } catch (SQLException | PropertyVetoException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        
    }
    
    
    public void getPastFixtureData(){
        
        try {
            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture/allData.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"id\": \" "+ this.id + " \", \"account_id\": \"11\"}";
            
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);           
            }
            
            try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
                  StringBuilder response = new StringBuilder();
                  String responseLine = null;
                  while ((responseLine = br.readLine()) != null) {
                      response.append(responseLine.trim());
                  }
                  //System.out.println(response.toString());
                  this.pastFixture = new JSONObject(response.toString());
                  System.out.println(this.pastFixture);
              }
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}


