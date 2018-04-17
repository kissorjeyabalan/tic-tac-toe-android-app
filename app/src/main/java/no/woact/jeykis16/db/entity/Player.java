package no.woact.jeykis16.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Player {
    @NonNull
    @PrimaryKey
    private String username;
    private int wins;
    private int defeats;

    public Player() {
    }

    public Player(String username) {
        setUsername(username);
        setWins(0);
        setDefeats(0);
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
