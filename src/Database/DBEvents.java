/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Event;
import Classes.Team;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import pastgameapp.PastGameApp;

/**
 *
 * @author anyone
 */
public class DBEvents {
    
     public static int determineEventId() throws Exception{

        int newId = -1;
        
        try {
            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture_event/count_fixture_events.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "";
            
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

                  newId = Integer.parseInt(response.toString());
              }
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newId;
        
       
    }
    
    public static void addEvent(Event event){
        
        
            
        try {
            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture_event/create.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{"
                    + "\"id\":\""+ event.getId() +  "\","
                    + "\"fixture_id\":\"" + event.getFixtureId() + "\","
                    + "\"team_id\":\"" + event.getTeamId()+ "\","
                    + "\"player_id\":\"" + event.getPlayerId() + "\","
                    + "\"related_player_id\":\"" + event.getRelatedPlayerId() + "\","
                    + "\"event_id\":\"" + event.getEventId() + "\","
                    + "\"minute\":\"" + event.getMinute()+ "\""  
                    + "}";
            
            
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

                  
              }
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //If statement used to update player formation position in the case of a substitution
            if(event.getEventType().equals("substitution")){

                int position = DBPlayers.getPlayerFormationPosition(event.getRelatedPlayerId(), event.getFixtureId());
                DBPlayers.setPlayerFormationPosition(event.getPlayerId(), event.getFixtureId(), position);
                DBPlayers.setPlayerFormationPosition(event.getRelatedPlayerId(), event.getFixtureId(), 0);
                
                
            }
            
            if(event.getEventType().equals("goal")){
                //may require changes
                
                DBTeams.updateScore( event.getTeamId(), event.getFixtureId()); 
                
            }
        
    }
    
}
