/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pastgameapp;

import Classes.Corner;
import Classes.Event;
import Database.Config;
import Database.DBFixture;
import Classes.Fixture;
import Classes.Player;
import Classes.Team;
import Database.DBPlayers;
import Database.DBTeams;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
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

                reverseScores(fixture);

                DBFixture.addFixture(db, fixture);
                  
                DBTeams.addTeam(db, fixture.getHomeTeam(), fixture.getId());
                DBTeams.addTeam(db, fixture.getAwayTeam(), fixture.getId());
                  
                for (Player player : fixture.getHomeTeam().getPlayers()) {
                 DBPlayers.addPlayer(db,  player,fixture.getId());
                }
                for (Player player : fixture.getAwayTeam().getPlayers()) {
                 DBPlayers.addPlayer(db,  player,fixture.getId());
                }
            } catch (Exception ex) {
                Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException | PropertyVetoException ex) {
            Logger.getLogger(PastGameApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

        
    }
    
    public void outputPlayers(ArrayList<Player> players){
        
        for(int i = 0;i < players.size();i++){
            
            System.out.println("Player Name: " + players.get(i).getPlayerName() + " Position: " + players.get(i).getFormationPosition());
                
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
        
        tempFixture.setId(DBFixture.determineFixtureId(db));
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
        tempFixture.setStatus("LIVE");
        tempFixture.setStartTime(LocalTime.parse(pastFixture.getString("starting_time")));
        tempFixture.setStartDate(LocalDate.parse(pastFixture.getString("starting_date")));
        tempFixture.setTimezone(pastFixture.getString("timezone"));
        tempFixture.setTimeMinute(this.startTime);
        tempFixture.setTimeSecond(0);
        tempFixture.setAddedTime(pastFixture.getInt("added_time"));
        tempFixture.setExtraTime(pastFixture.getInt("extra_time"));
        tempFixture.setInjuryTime(pastFixture.getInt("injury_time"));
        tempFixture.setEvents(parseEventData(pastFixture.getJSONArray("events"), tempFixture.getId()));
        tempFixture.setCorners(parseCornerData(pastFixture.getJSONArray("corners"), tempFixture.getId()));
        tempFixture.setHomeTeam(parseTeamData(pastFixture.getJSONObject("localteam")));
        tempFixture.setAwayTeam(parseTeamData(pastFixture.getJSONObject("visitorteam"))); 
        
       
        
        return tempFixture;
    }
    
    public Team parseTeamData(JSONObject teamData){
        Team tempTeam = new Team();
        
        tempTeam.setTeamId(teamData.getInt("team_id"));
        tempTeam.setTeamName(teamData.getString("team_name"));
        tempTeam.setManagerName(teamData.getString("manager"));
        tempTeam.setHomeTeam(teamData.getInt("home_team") == 1);
        tempTeam.setLogo(teamData.getString("logo"));
        tempTeam.setWinningTeam(teamData.getInt("winning_team") == 1);
        tempTeam.setScore(teamData.getInt("score"));
        tempTeam.setPenScore(teamData.getInt("pen_score"));
        tempTeam.setYellowcards(teamData.getInt("yellowcards"));
        tempTeam.setRedcards(teamData.getInt("redcards"));
        tempTeam.setColour(teamData.getString("colour"));
        tempTeam.setFormation(teamData.getString("formation"));
        tempTeam.setVenueId(teamData.getInt("venue_id"));
        tempTeam.setPlayers(parsePlayerData(teamData.getJSONArray("players")));
        //Required functionality for players
        
        
        return tempTeam;
        
    }
    
    public ArrayList<Player> parsePlayerData(JSONArray playerData){
        ArrayList<Player> players = new ArrayList<>();
        Player tempPlayer;
        
        
        for(int i = 0; i < playerData.length();i++){
            JSONObject temp = (JSONObject) playerData.get(i);
            tempPlayer  = new Player();
            
            tempPlayer.setId(temp.getInt("player_id"));
            tempPlayer.setFirstname(temp.getString("firstname"));
            tempPlayer.setLastname(temp.getString("lastname"));
            tempPlayer.setPlayerName(temp.getString("player_name"));
            tempPlayer.setDisplayName(temp.getString("display_name"));
            tempPlayer.setTeamId(temp.getInt("team_id"));
            tempPlayer.setPosition(temp.getString("position").charAt(0));
            tempPlayer.setType(temp.getString("type"));

            if(temp.get("formation_position").toString().equals("null"))
                tempPlayer.setFormationPosition(-1);
            else
                tempPlayer.setFormationPosition(temp.getInt("formation_position"));
            
            tempPlayer.setCaptain(temp.getInt("captain") == 1);
            tempPlayer.setCountryId(temp.getInt("country_id"));
            tempPlayer.setNationality(temp.getString("nationality"));
            tempPlayer.setDateOfBirth(LocalDate.parse(temp.getString("date_of_birth")));
            tempPlayer.setImage(temp.getString("image"));
            tempPlayer.setHeight(temp.get("height").toString());
            tempPlayer.setWeight(temp.get("weight").toString());
            tempPlayer.setMinutesPlayed(temp.getInt("minutes_played"));
            tempPlayer.setPassAccuracy(temp.getInt("pass_accuracy"));
            tempPlayer.setTotalShots(temp.getInt("total_shots"));
            tempPlayer.setShotsOnGoal(temp.getInt("shots_on_goal"));
            tempPlayer.setSaves(temp.getInt("saves"));
            tempPlayer.setGoalScores(temp.getInt("goal_scores"));
            tempPlayer.setGoalAssists(temp.getInt("goal_assists"));
            tempPlayer.setTotalCrosses(temp.getInt("total_crosses"));
            tempPlayer.setCrossAccuracy(temp.getInt("cross_accuracy"));
            tempPlayer.setYellowcards(temp.getInt("yellowcards"));
            tempPlayer.setRedcards(temp.getInt("redcards"));
            tempPlayer.setYellowRedcards(temp.getInt("yellowredcards"));
            tempPlayer.setOffsides(temp.getInt("offsides"));
            tempPlayer.setPenSaved(temp.getInt("pen_saved"));
            tempPlayer.setPenMissed(temp.getInt("pen_missed"));
            tempPlayer.setTackles(temp.getInt("tackles"));
            tempPlayer.setBlocks(temp.getInt("blocks"));
            tempPlayer.setIntercepts(temp.getInt("intercepts"));
            tempPlayer.setClearances(temp.getInt("clearances"));
            
            players.add(tempPlayer);
            
        }
        
        return players;
    }
    
    public ArrayList<Event> parseEventData(JSONArray eventData, int fixtureId){
        ArrayList<Event> events = new ArrayList<>();
        Event tempEvent;
        
        
        for(int i = 0; i < eventData.length();i++){
            JSONObject temp = (JSONObject) eventData.get(i);
            tempEvent  = new Event();
            
            tempEvent.setId(temp.getInt("id"));
            tempEvent.setFixtureId(fixtureId);
            tempEvent.setTeamId(temp.getInt("team_id"));
            tempEvent.setPlayerId(temp.getInt("player_id"));
            tempEvent.setPlayerName(temp.getString("player_name"));
            
            if(temp.get("related_player_id").toString().equals("null"))
                tempEvent.setRelatedPlayerId(-1);
            else
                tempEvent.setRelatedPlayerId(temp.getInt("related_player_id"));
            
            
            tempEvent.setEventId(temp.getInt("event_id"));
            tempEvent.setEventType(temp.getString("event_type"));
            tempEvent.setMinute(temp.getInt("minute"));

            
            events.add(tempEvent);
            
        }
     
        return events;
    }
    
        public ArrayList<Corner> parseCornerData(JSONArray cornerData, int fixtureId){
        ArrayList<Corner> corners = new ArrayList<>();
        Corner tempCorner;
        
        
        for(int i = 0; i < cornerData.length();i++){
            JSONObject temp = (JSONObject) cornerData.get(i);
            tempCorner  = new Corner();
            
            tempCorner.setId(temp.getInt("id"));
            tempCorner.setMinute(temp.getInt("minute"));
            tempCorner.setTeamName(temp.getString("team_name"));
            tempCorner.setTeamId(temp.getInt("team_id"));
            tempCorner.setFixtureId(fixtureId);


            
            corners.add(tempCorner);
            
        }
     
        return corners;
    }
        
    public void reverseSubstitutions(Fixture fixture){

        Event tempEvent;
        Player tempPlayer1;
        Player tempPlayer2;
        int position;

        for(int i = fixture.getEvents().size();i > 0;i--){
            tempEvent = fixture.getEvents().get(i-1);
            if(tempEvent.getEventId() == 2 && tempEvent.getMinute() > startTime){
               tempPlayer1 = getPlayer(getTeam(fixture, tempEvent.getTeamId()), tempEvent.getPlayerId());
               tempPlayer2 = getPlayer(getTeam(fixture, tempEvent.getTeamId()), tempEvent.getRelatedPlayerId());

               position = tempPlayer1.getFormationPosition();
               tempPlayer1.setFormationPosition(-1);
               tempPlayer2.setFormationPosition(position);
            }

        }

    }

    public Team getTeam(Fixture fixture, int teamId){

       if(fixture.getHomeTeam().getTeamId() == teamId)
           return fixture.getHomeTeam();
       else
           return fixture.getAwayTeam();

    }

    public Player getPlayer(Team team, int playerId){

        Player tempPlayer;
        for(int i = 0;i < team.getPlayers().size();i++){
            tempPlayer = team.getPlayers().get(i);

            if(tempPlayer.getId() == playerId)
                return team.getPlayers().get(i);

        }

        return new Player();

    }
    
    public void reverseScores(Fixture fixture){
            
        Event tempEvent;

        Team tempTeam;
        for(int i = fixture.getEvents().size();i > 0;i--){
            tempEvent = fixture.getEvents().get(i-1);
            if(tempEvent.getEventId() == 1 && tempEvent.getMinute() > startTime){
               tempTeam = getTeam(fixture, tempEvent.getTeamId());
               tempTeam.setScore(tempTeam.getScore() - 1);


            }

        }

    }
        

}


