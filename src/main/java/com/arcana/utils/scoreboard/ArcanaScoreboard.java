package com.arcana.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.UUID;

/**
 * A custom scoreboard that is given to a PlayerBase. This is a no-flicker scoreboard. It will take a given present
 * of Strings and display them on the scoreboard.
 */
public class ArcanaScoreboard {

    private final UUID owner;
    private ScoresPreset preset;
    private Objective objective;

    public ArcanaScoreboard(UUID owner, ScoresPreset preset) {
        this.owner = owner;
        this.preset = preset;
    }

    public void generateScoreboard(){
        Player player = Bukkit.getPlayer(owner);

        if(player != null){
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
            objective = scoreboard.registerNewObjective("Server", "dummy", preset.getDisplayName());
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(preset.getDisplayName());

            player.setScoreboard(scoreboard);
            preset.generateTeams(Bukkit.getPlayer(owner).getScoreboard(), objective);
        }
    }

    protected Objective getObjective(){
        return objective;
    }

    public void changePreset(ScoresPreset preset){
        this.preset.removeScores();
        this.preset = preset;
        update();
    }

    /**
     * This will update each score in the scoreboard. I recommend using a Listener in each ScoreEntry
     * class to update that particular score rather than calling this as a main means to updating the scores.
     */
    public void update(){
        objective.setDisplayName(preset.getDisplayName());
        preset.update();
    }

    /**
     * This could be called when a player leaves or on server stopping.
     */
    public void removeScoreboard(){
        preset.removeScores();
        Bukkit.getPlayer(owner).setScoreboard(null);
    }
}
