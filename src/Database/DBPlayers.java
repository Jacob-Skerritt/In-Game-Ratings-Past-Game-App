/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public static int getPlayerFormationPosition(Connection db,int playerId, int fixtureId){
        try {
            // the mysql insert statement
            String query = "SELECT formation_position from fixtures_players where player_id = ? AND fixture_id = ?";

            try (PreparedStatement preparedStmt = db.prepareStatement(query)) {
                preparedStmt.setInt(1, playerId);
                preparedStmt.setInt(2, fixtureId);
                
                // execute the query, and get a java resultset
                ResultSet rs = preparedStmt.executeQuery();
                
                while (rs.next()) {               
                    return rs.getInt("formation_position");
                    
                }
            }
  
        } catch (SQLException ex) {
        }
        return 0;
    }
    
    //Method to update a plyaers formation for a particular fixture
    public static void setPlayerFormationPosition(Connection db,int playerId, int fixtureId, int position){
        try {
            // the mysql insert statement
            String query = "update fixtures_players set formation_position = ? where player_id = ? and fixture_id = ?";

            PreparedStatement preparedStmt = db.prepareStatement(query);
            if(position >0)
                preparedStmt.setInt(1, position);
            else
                preparedStmt.setNull(1, java.sql.Types.VARCHAR);
            preparedStmt.setInt(2, playerId);
            preparedStmt.setInt(3, fixtureId);

             preparedStmt.execute();
            
        } catch (SQLException ex) {
        }
    }
    
}
