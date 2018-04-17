package no.woact.jeykis16.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import no.woact.jeykis16.db.entity.Player;

@Dao
public interface PlayerDao {
    @Query("SELECT * FROM player")
    List<Player> getAll();

    @Query("SELECT * FROM player ORDER BY wins DESC")
    List<Player> getAllSortedByWinsDesc();

    @Query("SELECT * FROM player WHERE username LIKE :userName LIMIT 1")
    Player findByName(String userName);

    @Insert
    void insertAll(Player... players);

    @Insert
    void insert(Player player);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePlayers(Player... players);

    @Update
    void updatePlayer(Player player);
}
