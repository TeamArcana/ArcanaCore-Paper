package com.arcana.utils.hologram;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArcanaHologram {

    public static Builder builder(){return new Builder();}

    private List<ArmorStand> stands;
    private List<String> lines;
    private Location spawnLocation;
    private double spacing;

    protected ArcanaHologram(List<String> lines, Location spawnLocation, double spacing) {
        this.lines = lines;
        this.spawnLocation = spawnLocation;
        this.spacing = spacing;
        this.stands = new CopyOnWriteArrayList<>();
    }

    public void spawn(){
        Location temp = spawnLocation.clone();
        for(String s: lines){
            ArmorStand stand = (ArmorStand) temp.getWorld().spawnEntity(temp, EntityType.ARMOR_STAND);
            stand.setGravity(false);
            stand.teleport(temp);
            stand.setCustomNameVisible(true);
            stand.setCustomName(s);
            stand.setVisible(false);
            stand.setBasePlate(false);
            stands.add(stand);
        }
    }

    public void despawn(){
        for(ArmorStand stand: stands){
            stand.remove();
        }
    }

    public static class Builder{

        private List<String> lines;
        private Location spawnLocation;
        private double spacing = 0.25;

        public Builder(){
            lines = new CopyOnWriteArrayList<>();
        }

        public Builder addLine(String string){
            lines.add(string);
            return this;
        }

        public Builder spawn(Location location){
            this.spawnLocation = location;
            return this;
        }

        public Builder spacing(double spacing){
            this.spacing = spacing;
            return this;
        }

        public ArcanaHologram build(){
            return new ArcanaHologram(lines, spawnLocation, spacing);
        }

    }
}
