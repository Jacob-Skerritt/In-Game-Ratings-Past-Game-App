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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public static void addEvent(Connection db, Event event){
        
        try {
                // the mysql insert statement
                String query = "insert into fixtures_events(id, fixture_id, team_id, player_id, related_player_id, event_id, minute)"
                        + " values (?, ?, ?, ?, ?, ?, ?)";


                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = db.prepareStatement(query);
                preparedStmt.setLong(1, event.getId());
                preparedStmt.setInt(2, event.getFixtureId());
                

                preparedStmt.setInt(3, event.getTeamId());

                

                preparedStmt.setInt(4, event.getPlayerId());


                preparedStmt.setInt(5, event.getRelatedPlayerId());

               

                preparedStmt.setInt(6, event.getEventId());


                preparedStmt.setInt(7, event.getMinute());

               

                // execute the preparedstatement
                preparedStmt.execute();
            }catch (SQLException ex) {

            }
            
            //If statement used to update player formation position in the case of a substitution
            if(event.getEventType().equals("substitution")){

                int position = DBPlayers.getPlayerFormationPosition(event.getRelatedPlayerId(), event.getFixtureId());
                DBPlayers.setPlayerFormationPosition(db,event.getPlayerId(), event.getFixtureId(), position);
                DBPlayers.setPlayerFormationPosition(db,event.getRelatedPlayerId(), event.getFixtureId(), 0);
                
                
            }
            
            if(event.getEventType().equals("goal")){
                int score = DBTeams.getScore(db, event.getTeamId(), event.getFixtureId());
                DBTeams.updateScore( event.getTeamId(), event.getFixtureId(), score); 
                
            }
        
    }
    
}
