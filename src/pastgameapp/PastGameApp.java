/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pastgameapp;

import Database.Config;
import Classes.Fixture;
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
import java.time.LocalDate;
import java.time.LocalTime;
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
                 Fixture fixture = parseFixtureData(db);
                 System.out.println(fixture);
            } catch (Exception ex) {
                Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
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
              }
            
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Fixture parseFixtureData(Connection db) throws Exception{
        
        Fixture tempFixture = new Fixture();
        
        tempFixture.setId(tempFixture.determineFixtureId(db));
        tempFixture.setLeagueId(pastFixture.getInt("league_id"));
        tempFixture.setSeasonId(pastFixture.getInt("season_id"));
        tempFixture.setStageId(pastFixture.getInt("stage_id"));
        //May need to make Round a static value!
        tempFixture.setRoundId(pastFixture.getInt("round_id"));
        tempFixture.setVenueId(pastFixture.getInt("venue_id"));
        tempFixture.setWeatherCode(pastFixture.getString("weather_code"));
        tempFixture.setWeatherType(pastFixture.getString("weather_type"));
        tempFixture.setWeatherImage(pastFixture.getString("weather_report_image"));
        tempFixture.setTemp(pastFixture.getInt("temperature"));
        tempFixture.setStatus(pastFixture.getString("fixture_status"));
        tempFixture.setStartTime(LocalTime.parse(pastFixture.getString("starting_time")));
        tempFixture.setStartDate(LocalDate.parse(pastFixture.getString("starting_date")));
        tempFixture.setTimezone(pastFixture.getString("timezone"));
        tempFixture.setTimeMinute(pastFixture.getInt("time_minute"));
        tempFixture.setTimeSecond(pastFixture.getInt("time_second"));
        tempFixture.setAddedTime(pastFixture.getInt("added_time"));
        tempFixture.setExtraTime(pastFixture.getInt("extra_time"));
        tempFixture.setInjuryTime(pastFixture.getInt("injury_time"));
        tempFixture.setEvents(pastFixture.getJSONArray("events"));
        tempFixture.setCorners(pastFixture.getJSONArray("corners"));
        
        //Required - Functionality for teams
        
        
        
        
        
        return tempFixture;
    }
    
}


