/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Fixture;
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
import org.json.JSONObject;
import pastgameapp.PastGameApp;

/**
 *
 * @author anyone
 */
public class DBFixture {
    
    public static int determineFixtureId() throws Exception{

        int newId = -1;
        
        try {
            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture/count_fixtures.php");
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
        
    public static void addFixture(Connection db, Fixture fixture){


        try {
            // the mysql insert statement
            String query = " insert into fixtures(id, league_id, season_id, stage_id, round_id, venue_id, weather_code, weather_type, weather_report_image, temperature, fixture_status, starting_time, starting_date, timezone, time_minute, time_second, added_time, extra_time, injury_time)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, fixture.getId());
            preparedStmt.setInt(2, fixture.getLeagueId());
            preparedStmt.setInt(3, fixture.getSeasonId());
            preparedStmt.setInt(4, fixture.getStageId());
            preparedStmt.setInt(5, fixture.getRoundId());

            preparedStmt.setInt(6, fixture.getVenueId());



            preparedStmt.setString(7, fixture.getWeatherCode());
            preparedStmt.setString(8, fixture.getWeatherType());

            preparedStmt.setString(9, fixture.getWeatherImage());
            preparedStmt.setInt(10, fixture.getTemp());


            preparedStmt.setString(11, fixture.getStatus());
            preparedStmt.setTime(12, Time.valueOf(fixture.getStartTime()));
            preparedStmt.setDate(13, Date.valueOf(fixture.getStartDate()));
            preparedStmt.setString(14, fixture.getTimezone());

            preparedStmt.setInt(15, fixture.getTimeMinute());
            preparedStmt.setInt(16, fixture.getTimeSecond());
            preparedStmt.setInt(17, fixture.getAddedTime());
            preparedStmt.setInt(18, fixture.getExtraTime());
            preparedStmt.setInt(19, fixture.getInjuryTime());
            // execute the preparedstatement
            preparedStmt.execute();


        } catch (SQLException ex) {

        }finally{

        }


    }
        
    public static void updateStatus(int fixtureId, String status){

        
        try {
            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture/updateStatus.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"id\": \" "+ fixtureId +  "\", \"fixture_status\": \" " + status + "\"}";
            
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
    
    public static void updateTime(int fixtureId, int minute, int second){
      
        try {
            
            URL url = new URL ("http://mysql03.comp.dkit.ie/D00196117/in_game_ratings_api/fixture/updateTime.php");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            String jsonInputString = "{\"id\": \" "+ fixtureId +  "\", \"time_minute\": \" " + minute + "\", \"time_second\": \" " + second + "\"}";
            
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
