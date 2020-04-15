/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import org.json.JSONArray;

/**
 *
 * @author anyone
 */
public class Fixture {
    
    private int id;
    private int leagueId;
    private int seasonId;
    private int stageId;
    private int roundId;
    private int venueId;
    private String weatherCode;
    private String weatherType;
    private String weatherImage;
    private int temp;
    private String status;
    private LocalTime startTime;
    private LocalDate startDate;
    private String timezone;
    private int timeMinute;
    private int timeSecond;
    private int addedTime;
    private int extraTime;
    private int injuryTime;
    private Team homeTeam;
    private Team awayTeam;
    private JSONArray events;
    private JSONArray corners;
    
    
    public Fixture(){}

    public Fixture(int id, int leagueId, int seasonId, int stageId, int roundId, int venueId, String weatherCode, String weatherType, String weatherImage, int temp, String status, LocalTime startTime, LocalDate startDate, String timezone, int timeMinute, int timeSecond, int addedTime, int extraTime, int injuryTime, Team homeTeam, Team awayTeam, JSONArray events, JSONArray corners) {
        this.id = id;
        this.leagueId = leagueId;
        this.seasonId = seasonId;
        this.stageId = stageId;
        this.roundId = roundId;
        this.venueId = venueId;
        this.weatherCode = weatherCode;
        this.weatherType = weatherType;
        this.weatherImage = weatherImage;
        this.temp = temp;
        this.status = status;
        this.startTime = startTime;
        this.startDate = startDate;
        this.timezone = timezone;
        this.timeMinute = timeMinute;
        this.timeSecond = timeSecond;
        this.addedTime = addedTime;
        this.extraTime = extraTime;
        this.injuryTime = injuryTime;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.events = events;
        this.corners = corners;
    }

    public int getId() {
        return id;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public int getStageId() {
        return stageId;
    }

    public int getRoundId() {
        return roundId;
    }

    public int getVenueId() {
        return venueId;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public String getWeatherImage() {
        return weatherImage;
    }

    public int getTemp() {
        return temp;
    }

    public String getStatus() {
        return status;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getTimezone() {
        return timezone;
    }

    public int getTimeMinute() {
        return timeMinute;
    }

    public int getTimeSecond() {
        return timeSecond;
    }

    public int getAddedTime() {
        return addedTime;
    }

    public int getExtraTime() {
        return extraTime;
    }

    public int getInjuryTime() {
        return injuryTime;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public JSONArray getEvents() {
        return events;
    }

    public JSONArray getCorners() {
        return corners;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public void setVenueId(int veneueId) {
        this.venueId = veneueId;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public void setWeatherImage(String weatherImage) {
        this.weatherImage = weatherImage;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setTimeMinute(int timeMinute) {
        this.timeMinute = timeMinute;
    }

    public void setTimeSecond(int timeSecond) {
        this.timeSecond = timeSecond;
    }

    public void setAddedTime(int addedTime) {
        this.addedTime = addedTime;
    }

    public void setExtraTime(int extraTime) {
        this.extraTime = extraTime;
    }

    public void setInjuryTime(int injuryTime) {
        this.injuryTime = injuryTime;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setEvents(JSONArray events) {
        this.events = events;
    }

    public void setCorners(JSONArray corners) {
        this.corners = corners;
    }

    @Override
    public String toString() {
        return "Fixture{" + "id=" + id + ", leagueId=" + leagueId + ", seasonId=" + seasonId + ", stageId=" + stageId + ", roundId=" + roundId + ", veneueId=" + venueId + ", weatherCode=" + weatherCode + ", weatherType=" + weatherType + ", weatherImage=" + weatherImage + ", temp=" + temp + ", status=" + status + ", startTime=" + startTime + ", startDate=" + startDate + ", timezone=" + timezone + ", timeMinute=" + timeMinute + ", timeSecond=" + timeSecond + ", addedTime=" + addedTime + ", extraTime=" + extraTime + ", injuryTime=" + injuryTime + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + ", events=" + events + ", corners=" + corners + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.id;
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
        final Fixture other = (Fixture) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
