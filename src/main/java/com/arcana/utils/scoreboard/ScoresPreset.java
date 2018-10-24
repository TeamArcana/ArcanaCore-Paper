package com.arcana.utils.scoreboard;

import com.arcana.utils.StringUtils;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class ScoresPreset {

    private String displayName;
    private List<ScoreEntry> scores;
    private UUID owner;

    public ScoresPreset(UUID owner, String displayName) {
        this.owner = owner;
        this.displayName = displayName;
        scores = new CopyOnWriteArrayList<>();

        setScores();
    }

    /**
     * This is the method where the scores should be updated.
     * Use add and remove functions.
     */
    public abstract void setScores();

    protected void add(ScoreEntry entry){
        scores.add(entry);
    }

    public void remove(ScoreEntry entry){
        scores.remove(entry);
    }

    public void removeByName(String name){
        for(ScoreEntry entry: scores){
            if(entry.getTeamName().equalsIgnoreCase(name)){
                scores.remove(entry);
            }
        }
    }

    public void removeByText(String text){
        for(ScoreEntry entry: scores){
            if(entry.getText().equalsIgnoreCase(text)){
                scores.remove(entry);
            }
        }
    }

    public void removeByScore(int score){
        for(ScoreEntry entry: scores){
            if(entry.getScore() == score){
                scores.remove(score);
            }
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<ScoreEntry> getScores() {
        return scores;
    }

    public UUID getOwner() {
        return owner;
    }

    public void generateTeams(Scoreboard scoreboard, Objective objective) {
        for(ScoreEntry scoreEntry: scores){
            scoreEntry.setObjective(objective);
            scoreEntry.generateTeam(scoreboard);
        }
    }

    /**
     * ============================================
     */

    /**
     * These entries have a lot of flexibility. For example:
     * We could create an EcoScoreEntry that registers a new listener, waiting for an eco balance change event.
     * On the event, we can call update and change the amount.
     * On removeScore(), we can unregister the listener
     */
    public static abstract class ScoreEntry{

        private String teamName, entryName, text;
        private int score;
        private Team team;
        private Objective objective;

        public ScoreEntry(String teamName, String text, int score) {
            this.teamName = teamName;
            this.text = text;
            this.score = score;
        }

        public abstract void removeScore();

        public void setObjective(Objective objective) {
            this.objective = objective;
        }

        public void update(String text){
            this.text = text;
            team.setPrefix(text);
            objective.getScore(entryName).setScore(score);
        }

        public void generateTeam(Scoreboard scoreboard){
            entryName = StringUtils.getRandomEmptyString();

            this.team = scoreboard.registerNewTeam(teamName);
            team.addEntry(entryName);
            update(text);
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getText() {
            return text;
        }

        public int getScore() {
            return score;
        }

        public Team getTeam() {
            return team;
        }

        public String getTeamName() {
            return teamName;
        }
    }
}
