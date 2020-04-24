/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author anyone
 */
public class Corner {
    
    private int id;
    private int minute;
    private String teamName;
    private int teamId;
    private int fixtureId;
    
    public Corner(){}

    public Corner(int id, int minute, String teamName, int teamId, int fixtureId) {
        this.id = id;
        this.minute = minute;
        this.teamName = teamName;
        this.teamId = teamId;
        this.fixtureId = fixtureId;
    }

    public int getId() {
        return id;
    }

    public int getMinute() {
        return minute;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getFixtureId() {
        return fixtureId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setFixtureId(int fixtureId) {
        this.fixtureId = fixtureId;
    }

    @Override
    public String toString() {
        return "Corner{" + "id=" + id + ", minute=" + minute + ", teamName=" + teamName + ", teamId=" + teamId + ", fixtureId=" + fixtureId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
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
        final Corner other = (Corner) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
