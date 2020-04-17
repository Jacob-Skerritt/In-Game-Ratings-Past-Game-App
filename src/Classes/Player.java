/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.time.LocalDate;


/**
 *
 * @author anyone
 */
public class Player {
    
    private int id;
    private String firstname;
    private String lastname;
    private String playerName;
    private String displayName;
    private int teamId;
    private char position;
    private String type;
    private int formationPosition;
    private boolean captain;
    private int countryId;
    private String nationality;
    private LocalDate dateOfBirth;
    private String image;
    private String height;
    private String weight;
    private int minutesPlayed;
    private int passAccuracy;
    private int totalShots;
    private int shotsOnGoal;;
    private int saves;
    private int goalScores;
    private int goalAssists;
    private int totalCrosses;
    private int crossAccuracy;
    private int yellowcards;
    private int redcards;
    private int yellowRedcards;
    private int offsides;
    private int penSaved;
    private int penMissed;
    private int tackles;
    private int blocks;
    private int intercepts;
    private int clearances;
    
    public Player(){}
    
        public Player(int id, String firstname, String lastname, String playerName, String displayName, int teamId, char position, String type, int formationPosition, boolean captain, int countryId, String nationality, LocalDate dateOfBirth, String image, String height, String weight, int minutesPlayed, int passAccuracy, int totalShots, int shotsOnGoal, int saves, int goalScores, int goalAssists, int totalCrosses, int crossAccuracy, int yellowcards, int redcards, int yellowRedcards, int offsides, int penSaved,int penMissed, int tackles, int blocks, int intercepts, int clearances) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.playerName = playerName;
        this.displayName = displayName;
        this.teamId = teamId;
        this.position = position;
        this.type = type;
        this.formationPosition = formationPosition;
        this.captain = captain;
        this.countryId = countryId;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
        this.height = height;
        this.weight = weight;
        this.minutesPlayed = minutesPlayed;
        this.passAccuracy = passAccuracy;
        this.totalShots = totalShots;
        this.shotsOnGoal = shotsOnGoal;
        this.saves = saves;
        this.goalScores = goalScores;
        this.goalAssists = goalAssists;
        this.totalCrosses = totalCrosses;
        this.crossAccuracy = crossAccuracy;
        this.yellowcards = yellowcards;
        this.redcards = redcards;
        this.yellowRedcards = yellowRedcards;
        this.offsides = offsides;
        this.penSaved = penSaved;
        this.penMissed = penMissed;
        this.tackles = tackles;
        this.blocks = blocks;
        this.intercepts = intercepts;
        this.clearances = clearances;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public char getPosition() {
        return position;
    }

    public void setPosition(char position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFormationPosition() {
        return formationPosition;
    }

    public void setFormationPosition(int formationPosition) {
        this.formationPosition = formationPosition;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public int getPassAccuracy() {
        return passAccuracy;
    }

    public void setPassAccuracy(int passAccuracy) {
        this.passAccuracy = passAccuracy;
    }

    public int getTotalShots() {
        return totalShots;
    }

    public void setTotalShots(int totalShots) {
        this.totalShots = totalShots;
    }

    public int getShotsOnGoal() {
        return shotsOnGoal;
    }

    public void setShotsOnGoal(int shotsOnGoal) {
        this.shotsOnGoal = shotsOnGoal;
    }

    public int getSaves() {
        return saves;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }

    public int getGoalScores() {
        return goalScores;
    }

    public void setGoalScores(int goalScores) {
        this.goalScores = goalScores;
    }

    public int getGoalAssists() {
        return goalAssists;
    }

    public void setGoalAssists(int goalAssists) {
        this.goalAssists = goalAssists;
    }

    public int getTotalCrosses() {
        return totalCrosses;
    }

    public void setTotalCrosses(int totalCrosses) {
        this.totalCrosses = totalCrosses;
    }

    public int getCrossAccuracy() {
        return crossAccuracy;
    }

    public void setCrossAccuracy(int crossAccuracy) {
        this.crossAccuracy = crossAccuracy;
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

    public int getYellowRedcards() {
        return yellowRedcards;
    }

    public void setYellowRedcards(int yellowRedcards) {
        this.yellowRedcards = yellowRedcards;
    }

    public int getOffsides() {
        return offsides;
    }

    public void setOffsides(int offsides) {
        this.offsides = offsides;
    }

    public int getPenSaved() {
        return penSaved;
    }

    public void setPenSaved(int penSaved) {
        this.penSaved = penSaved;
    }

    public int getPenMissed() {
        return penMissed;
    }

    public void setPenMissed(int penMissed) {
        this.penMissed = penMissed;
    }
    
    public int getTackles() {
        return tackles;
    }

    public void setTackles(int tackles) {
        this.tackles = tackles;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getIntercepts() {
        return intercepts;
    }

    public void setIntercepts(int intercepts) {
        this.intercepts = intercepts;
    }

    public int getClearances() {
        return clearances;
    }

    public void setClearances(int clearances) {
        this.clearances = clearances;
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", playerName=" + playerName + ", displayName=" + displayName + ", teamId=" + teamId + ", position=" + position + ", type=" + type + ", formation_position=" + formationPosition + ", captain=" + captain + ", countryId=" + countryId + ", nationality=" + nationality + ", dateOfBirth=" + dateOfBirth + ", image=" + image + ", height=" + height + ", weight=" + weight + ", minutesPlayed=" + minutesPlayed + ", passAccuracy=" + passAccuracy + ", totalShots=" + totalShots + ", shotsOnGoal=" + shotsOnGoal + ", saves=" + saves + ", goalScores=" + goalScores + ", goalAssists=" + goalAssists + ", totalCrosses=" + totalCrosses + ", crossAccuracy=" + crossAccuracy + ", yellowcards=" + yellowcards + ", redcards=" + redcards + ", yellowRedcards=" + yellowRedcards + ", offsides=" + offsides + ", penSaved=" + penSaved + ", penMissed=" + penMissed + ", tackles=" + tackles + ", blocks=" + blocks + ", intercepts=" + intercepts + ", clearances=" + clearances + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
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
        final Player other = (Player) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    

    
}
