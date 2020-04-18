/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Fixture;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 *
 * @author anyone
 */
public class DBFixture {
    
    public static int determineFixtureId(Connection db) throws Exception{

        int newId = -1;
        try {

            // the mysql insert statement
            String query = " SELECT COUNT(*) from fixtures where id < 1000";

            PreparedStatement preparedStmt = db.prepareStatement(query);

            // execute the query, and get a java resultset
            ResultSet rs = preparedStmt.executeQuery();

            // iterate through the java resultset
              // iterate through the java resultset
            if (rs.isBeforeFirst()) {

                while (rs.next()) {
                    int count = rs.getInt("COUNT(*)");

                   newId = count;

                }
                preparedStmt.close();

            }

            if(newId == -1)
                throw new Exception("Invalid, unable to generate fixture id");


        } catch (SQLException ex) {
            System.out.println(ex);
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
        
        public static void updateStatus(Connection db ,int fixtureId, String status){
        try {
            System.out.println("inside updatestatus");
            System.out.println("fixtureid: " + fixtureId + " status: " + status);
            // the mysql insert statement
            String query = "update fixtures set fixture_status = ? where id = ?";

            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setString(1, status);
            preparedStmt.setInt(2, fixtureId);

             preparedStmt.execute();
            
        } catch (SQLException ex) {
        }
    }
    
}
