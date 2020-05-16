/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

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
public class DBTeams {
    
    
    public static void addTeam(Connection db, Team team, int fixtureId){
        
        try {
            // the mysql insert statement
            String query = " insert into fixtures_teams(fixture_id, team_id, winning_team, home_team, score, pen_score, colour, formation, total_shots,"
                    + " shots_on_goal, shots_blocked, total_passes, accurate_passes, total_attacks, dangerous_attacks, fouls, corners,"
                    + " offsides, possessiontime, yellowcards, redcards, yellowredcards, saves, substitutions, penalties)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, fixtureId);
            preparedStmt.setInt(2, team.getTeamId());

            preparedStmt.setBoolean(3, team.getWinningTeam());

            preparedStmt.setBoolean(4, team.getHomeTeam());

            preparedStmt.setInt(5, team.getScore());


            preparedStmt.setInt(6, team.getPenScore());


            preparedStmt.setString(7, team.getColour());


            preparedStmt.setString(8, team.getFormation());

            preparedStmt.setInt(9, 0);

            preparedStmt.setInt(10, 0);

            preparedStmt.setInt(11, 0);

            preparedStmt.setInt(12, 0);

            preparedStmt.setInt(13, 0);

            preparedStmt.setInt(14, 0);

            preparedStmt.setInt(15, 0);

            preparedStmt.setInt(16, 0);

            preparedStmt.setInt(17, 0);

            preparedStmt.setInt(18, 0);

            preparedStmt.setInt(19, 0);

            preparedStmt.setInt(20, team.getYellowcards());

            preparedStmt.setInt(21, team.getRedcards());


            preparedStmt.setInt(22, 0);

            preparedStmt.setInt(23, 0);


            preparedStmt.setInt(24, 0);

            preparedStmt.setInt(25, 0); 



            // execute the preparedstatement
            preparedStmt.execute();
        }catch (SQLException ex) {

        }
    }
    
    public static void updateScore(int teamId, int fixtureId, int score){

        try {
            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture_team/add_goal.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"id\": \" "+ fixtureId +  "\", \"score\": \" " + score + "\", \"team_id\": \" " + teamId + "\"}";
            
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
    
      public static int getScore(Connection db,int teamId, int fixtureId){
        try {
            // the mysql insert statement
            String query = "SELECT score from fixtures_teams where team_id = ? AND fixture_id = ?";

            try (PreparedStatement preparedStmt = db.prepareStatement(query)) {
                preparedStmt.setInt(1, teamId);
                preparedStmt.setInt(2, fixtureId);
                
                // execute the query, and get a java resultset
                ResultSet rs = preparedStmt.executeQuery();
                
                // iterate through the java resultset
                
                
                while (rs.next()) {               
                    return rs.getInt("score");
                    
                }
            }
                
            
            
        } catch (SQLException ex) {
        }
        return 0;
    }
    
}
