/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Event;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author anyone
 */
public class DBEvents {
    
     public static int determineEventId(Connection db) throws Exception{

        int newId = -1;
        try {

            // the mysql insert statement
            String query = " SELECT COUNT(*) from fixtures_events where id < 50000";

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
                throw new Exception("Invalid, unable to generate event id");


        } catch (SQLException ex) {
            System.out.println(ex);
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
            if(event.getEventType().equals("substitution"))
            {

                int position = DBPlayers.getPlayerFormationPosition(db, event.getPlayerId(), event.getFixtureId());
                System.out.println("Position: " + position);
                System.out.println("Player id: " + event.getRelatedPlayerId());
                System.out.println("Fixture id: " +event.getFixtureId());
                DBPlayers.setPlayerFormationPosition(db,event.getPlayerId(), event.getFixtureId(), position);
                DBPlayers.setPlayerFormationPosition(db,event.getRelatedPlayerId(), event.getFixtureId(), 0);
                
                
            }
        
    }
    
}
