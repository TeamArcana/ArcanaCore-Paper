package com.arcana.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.UUID;

public class ArcanaScoreboard {

    private UUID owner;
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
            objective.setDisplayName(preset.getDisplayName());
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            player.setScoreboard(scoreboard);

            preset.generateTeams(scoreboard, objective);
        }
    }

    protected Objective getObjective(){
        return objective;
    }
}
