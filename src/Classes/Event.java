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
public class Event {
    private int id;
    private int fixtureId;
    private int teamId;
    private int playerId;
    private String playerName;
    private int relatedPlayerId;
    private int eventId;
    private String eventType;
    private int minute;
    
    public Event(){}

    public Event(int id, int fixtureId, int teamId, int playerId, String playerName, int relatedPlayerId, int eventId, String eventType, int minute) {
        this.id = id;
        this.fixtureId = fixtureId;
        this.teamId = teamId;
        this.playerId = playerId;
        this.playerName = playerName;
        this.relatedPlayerId = relatedPlayerId;
        this.eventId = eventId;
        this.eventType = eventType;
        this.minute = minute;
    }

    public int getId() {
        return id;
    }

    public int getFixtureId() {
        return fixtureId;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRelatedPlayerId() {
        return relatedPlayerId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public int getMinute() {
        return minute;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFixtureId(int fixtureId) {
        this.fixtureId = fixtureId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setRelatedPlayerId(int relatedPlayerId) {
        this.relatedPlayerId = relatedPlayerId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", fixtureId=" + fixtureId + ", teamId=" + teamId + ", playerId=" + playerId + ", playerName=" + playerName + ", relatedPlayerId=" + relatedPlayerId + ", eventId=" + eventId + ", eventType=" + eventType + ", minute=" + minute + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
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
        final Event other = (Event) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
