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
import java.util.logging.Level;
import java.util.logging.Logger;
import pastgameapp.PastGameApp;

/**
 *
 * @author anyone
 */
public class DBPlayers {
    
    public static void addPlayer(Player player, int fixtureId){
    
        try {
        
            int position = 20;
        
            if(player.getFormationPosition() != -1)
                position = player.getFormationPosition();

            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture_player/create.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{"
                    + "\"fixture_id\":\""+ fixtureId +  "\","
                    + "\"player_id\":\"" + player.getId() + "\","
                    + "\"team_id\":\"" + player.getTeamId()+ "\","
                    + "\"position\":\"" + String.valueOf(player.getPosition())+ "\","
                    + "\"type\":\"" +  player.getType() + "\","
                    + "\"formation_position\":\"" + position + "\","
                    + "\"captain\":\"" + player.isCaptain() + "\","
                    + "\"minutes_played\":\"" + player.getMinutesPlayed() + "\","
                    + "\"pass_accuracy\":\"" + player.getPassAccuracy()+ "\","
                    + "\"total_shots\":\"" + player.getTotalShots()+ "\","
                    + "\"shots_on_goal\":\"" + player.getShotsOnGoal()+ "\","
                    + "\"saves\":\"" + player.getSaves() + "\","
                    + "\"goal_scores\":\"" + player.getGoalScores() + "\","
                    + "\"goal_assists\":\"" +  player.getGoalAssists() + "\","
                    + "\"total_crosses\":\"" + player.getTotalCrosses() + "\","
                    + "\"cross_accuracy\":\"" + player.getCrossAccuracy()+ "\","
                    + "\"yellowcards\":\"" +  player.getYellowcards() + "\","
                    + "\"redcards\":\"" + player.getRedcards() + "\","
                    + "\"yellowredcards\":\"" + player.getYellowRedcards() + "\","
                    + "\"offsides\":\"" + player.getOffsides() + "\","
                    + "\"pen_saved\":\"" + player.getPenSaved() + "\","
                    + "\"tackles\":\"" + player.getTackles() + "\","
                    + "\"blocks\":\"" + player.getBlocks() + "\","
                    + "\"intercepts\":\"" + player.getIntercepts() + "\","
                    + "\"clearances\":\"" + player.getClearances() + "\","
                    + "\"pen_missed\":\"" + 0 + "\","
                    + "\"pen_scored\":\"" + 0 + "\""
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
