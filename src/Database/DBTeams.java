/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Classes.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
}
