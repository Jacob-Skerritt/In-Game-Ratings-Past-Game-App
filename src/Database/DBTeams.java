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
    
    
    public static void addTeam( Team team, int fixtureId){
        
        
        
        try {
            int home = 0;
            if(team.getHomeTeam())
                home = 1;

            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture_team/create.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{"
                    + "\"fixture_id\":\""+ fixtureId +  "\","
                    + "\"team_id\":\"" + team.getTeamId() + "\","
                    + "\"winning_team\":\"" + team.getWinningTeam()+ "\","
                    + "\"home_team\":\"" + home + "\","
                    + "\"score\":\"" +  team.getScore() + "\","
                    + "\"pen_score\":\"" + team.getPenScore() + "\","
                    + "\"colour\":\"" + team.getColour() + "\","
                    + "\"formation\":\"" + team.getFormation() + "\","
                    + "\"total_shots\":\"" + 0+ "\","
                    + "\"shots_on_goal\":\"" + 0+ "\","
                    + "\"shots_blocked\":\"" + 0+ "\","
                    + "\"total_passes\":\"" + 0 + "\","
                    + "\"accurate_passes\":\"" + 0 + "\","
                    + "\"total_attacks\":\"" +  0 + "\","
                    + "\"dangerous_attacks\":\"" + 0 + "\","
                    + "\"fouls\":\"" +0+ "\","
                    + "\"corners\":\"" +  0 + "\","
                    + "\"offsides\":\"" + 0 + "\","
                    + "\"possessiontime\":\"" + 0 + "\","
                    + "\"yellowcards\":\"" + team.getYellowcards() + "\","
                    + "\"redcards\":\"" + team.getRedcards() + "\","
                    + "\"yellowredcards\":\"" + 0 + "\","
                    + "\"saves\":\"" + 0 + "\","
                    + "\"substitutions\":\"" + 0 + "\","
                    + "\"penalties\":\"" + 0 + "\""
                    + "}";
            
            
            //System.out.println(jsonInputString);
            
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
    
    public static void updateScore(int teamId, int fixtureId){

        try {
            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture_team/add_goal.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"fixture_id\": \" "+ fixtureId +  "\", \"team_id\": \" " + teamId + "\"}";
            
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
