/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;

/**
 *
 * @author anyone
 */
public class Team {
    
    private int teamId;
    private String teamName;
    private String managerName;
    private boolean homeTeam;
    private String logo;
    private boolean winningTeam;
    private int score;
    private int penScore;
    private int yellowcards;
    private int redcards;
    private String colour;
    private String formation;
    private int venueId;
    private ArrayList<Player> players = new ArrayList<>();




    public Team(){}

    public Team(int teamId, String teamName, String managerName, boolean homeTeam, String logo, boolean winningTeam, int score, int penScore, int yellowcards, int redcards, String colour, String formation, int venueId) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.managerName = managerName;
        this.homeTeam = homeTeam;
        this.logo = logo;
        this.winningTeam = winningTeam;
        this.score = score;
        this.penScore = penScore;
        this.yellowcards = yellowcards;
        this.redcards = redcards;
        this.colour = colour;
        this.formation = formation;
        this.venueId = venueId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public boolean getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(boolean homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(boolean winningTeam) {
        this.winningTeam = winningTeam;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPenScore() {
        return penScore;
    }

    public void setPenScore(int penScore) {
        this.penScore = penScore;
    }

    public int getYellowcards() {
        return yellowcards;
    }

    public void setYellowcards(int yellowcards) {
        this.yellowcards = yellowcards;
    }

    public int getRedcards() {
        return redcards;
    }

    public void setRedcards(int redcards) {
        this.redcards = redcards;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int vanueId) {
        this.venueId = vanueId;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" + "teamId=" + teamId + ", teamName=" + teamName + ", managerName=" + managerName + ", homeTeam=" + homeTeam + ", logo=" + logo + ", winning_team=" + winningTeam + ", score=" + score + ", penScore=" + penScore + ", yellowcards=" + yellowcards + ", redcards=" + redcards + ", colour=" + colour + ", formation=" + formation + ", vanueId=" + venueId + ", players=" + players + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.teamId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        if (this.teamId != other.teamId) {
            return false;
        }
        return true;
    }
    
    




}
