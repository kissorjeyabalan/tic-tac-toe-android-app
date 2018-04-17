package no.woact.jeykis16.db.entity;

import android.widget.TextView;

public class Player {
    private String username;
    private int wins;
    private int defeats;

    public Player() {
    }

    public String getUsername() {
        return username;
    }

    public int getWins() {
        return wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }
}
