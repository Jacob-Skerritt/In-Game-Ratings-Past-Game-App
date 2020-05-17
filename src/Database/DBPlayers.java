/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Player;
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
public class DBPlayers {
    
    public static void addPlayer(Connection db, Player player, int fixtureId){
    try {
            // the mysql insert statement
            String query = "insert into"
                        + " fixtures_players(fixture_id, player_id, team_id, position, type, formation_position, captain, minutes_played, pass_accuracy, total_shots, shots_on_goal,"
                        + " saves, goal_scores, goal_assists, total_crosses, cross_accuracy, yellowcards, redcards, yellowredcards, offsides, pen_saved, pen_missed, pen_scored,"
                        + " tackles, blocks, intercepts, clearances)"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = db.prepareStatement(query);
            
            preparedStmt.setInt(1, fixtureId);

            preparedStmt.setInt(2, player.getId());
            
            preparedStmt.setInt(3, player.getTeamId());

            preparedStmt.setString(4, String.valueOf(player.getPosition()));

            preparedStmt.setString(5, player.getType());

            if(player.getFormationPosition() != -1)
                preparedStmt.setInt(6, player.getFormationPosition());
            else
                preparedStmt.setNull(6, java.sql.Types.VARCHAR);

            preparedStmt.setBoolean(7, player.isCaptain());

            preparedStmt.setInt(8, player.getMinutesPlayed());

            preparedStmt.setInt(9, player.getPassAccuracy());

            preparedStmt.setInt(10, player.getTotalShots());

            preparedStmt.setInt(11, player.getShotsOnGoal());

            preparedStmt.setInt(12, player.getSaves());

            preparedStmt.setInt(13, player.getGoalScores());

            preparedStmt.setInt(14, player.getGoalAssists());
            
            preparedStmt.setInt(15, player.getTotalCrosses());

            preparedStmt.setInt(16, player.getCrossAccuracy());

            preparedStmt.setInt(17, player.getYellowcards());

            preparedStmt.setInt(18, player.getRedcards());

            preparedStmt.setInt(19, player.getYellowRedcards());

            preparedStmt.setInt(20, player.getOffsides());

            preparedStmt.setInt(21, player.getPenSaved());

            preparedStmt.setInt(22, player.getPenMissed());

            preparedStmt.setInt(23, 0);

            preparedStmt.setInt(24, player.getTackles());

            preparedStmt.setInt(25, player.getBlocks());

            preparedStmt.setInt(26, player.getIntercepts());
       
            preparedStmt.setInt(27, player.getClearances());

            preparedStmt.execute();
        }catch (SQLException ex) {

        }
    }
    
    //method to get a players position in a formation based on the paramters fixture id and player id
    public static int getPlayerFormationPosition(int playerId, int fixtureId){
        
        int formationPosition = -1;
        
        try {
            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture_player/get_position.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"fixture_id\": \" "+ fixtureId +  "\", \"player_id\": \" " + playerId + "\"}";
            
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

                  if(response.toString().length() > 0)
                    formationPosition = Integer.parseInt(response.toString());
                  
              }
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return formationPosition;
    }
    
    //Method to update a plyaers formation for a particular fixture
    public static void setPlayerFormationPosition(int playerId, int fixtureId, int position){

      
        
        try {
            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture_player/updatePosition.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"fixture_id\": \" "+ fixtureId +  "\", \"player_id\": \" " + playerId + "\", \"formation_position\": \" " + position +  "\"}";
            
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
        
    }
    
}
