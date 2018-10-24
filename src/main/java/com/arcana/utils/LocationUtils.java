package com.arcana.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LocationUtils {

    /**
     * This will get the exact middle location between two Locations
     * @param firstCorner
     * @param secondCorner
     * @return
     */
    public static Location middleLocation(Location firstCorner, Location secondCorner){
        Vector temp = middleLocation(firstCorner.toVector(), secondCorner.toVector());
        return new Location(firstCorner.getWorld(), temp.getX(), temp.getY(), temp.getZ());
    }

    /**
     * This will get the exact middle location between two Vectors
     * @param firstCorner
     * @param secondCorner
     * @return
     */
    public static Vector middleLocation(Vector firstCorner, Vector secondCorner){
        int x = Math.max(firstCorner.getBlockX(), secondCorner.getBlockX()) - Math.min(firstCorner.getBlockX(), secondCorner.getBlockX());
        int y = Math.max(firstCorner.getBlockY(), secondCorner.getBlockY()) - Math.min(firstCorner.getBlockY(), secondCorner.getBlockY());
        int z = Math.max(firstCorner.getBlockZ(), secondCorner.getBlockZ()) - Math.min(firstCorner.getBlockZ(), secondCorner.getBlockZ());
        x /= 2;
        y /= 2;
        z /= 2;
        return new Vector(firstCorner.getBlockX() + x + 0.5, firstCorner.getBlockY() + y + 0.5, firstCorner.getBlockZ() + z + 0.5);
    }

    /**
     * This will return true if check is between the bounds
     * @param bound1
     * @param bound2
     * @param check
     * @return
     */
    public static boolean isWithinBounds(Location bound1, Location bound2, Location check){
        return isWithinBounds(bound1.toVector(), bound2.toVector(), check.toVector());
    }

    /**
     * This will return true if check is between the bounds
     * @param first
     * @param second
     * @param check
     * @return
     */
    public static boolean isWithinBounds(Vector first, Vector second, Vector check){
        int fx = first.getBlockX(), fy = first.getBlockY(), fz = first.getBlockZ();
        int sx = second.getBlockX(), sy = second.getBlockY(), sz = second.getBlockZ();

        return Math.min(fx, sx) <= check.getBlockX() && check.getBlockX() <= Math.max(fx, sx) &&
                Math.min(fy, sy) <= check.getBlockY() && check.getBlockY() <= Math.max(fx, sx) &&
                Math.min(fz, sz) <= check.getBlockZ() && check.getBlockZ() <= Math.max(fz, sz);
    }

    /**
     * This will return the location as a string with the Vector separated by commas
     * @param location
     * @return
     */
    public static String locationToString(Location location){
        String give = location.getWorld().getName();
        give += "," + String.valueOf(location.getX());
        give += "," + String.valueOf(location.getY());
        give += "," + String.valueOf(location.getZ());
        return give;
    }

    /**
     * This will create a location from a string formatted as return product of 'locationToString'
     * @param loc
     * @return
     */
    public static Location locationFromString(String loc){
        String[] temp = loc.split(",");
        return new Location(Bukkit.getWorld(temp[0]), Double.valueOf(temp[1]), Double.valueOf(temp[2]), Double.valueOf(temp[3]));
    }

    /**
     * This will get the offset from source to target
     * @param source
     * @param target
     * @return
     */
    public static Vector offset(Vector source, Vector target){
        return reflect(target.subtract(source));
    }

    /**
     * This will get the direction (a normalized vector) vector from source to target
     * @param source
     * @param target
     * @return
     */
    public static Vector direction(Vector source, Vector target){
        return reflect(offset(source, target)).normalize();
    }

    /**
     * This will reflect the given vector
     * @param vector
     * @return
     */
    public static Vector reflect(Vector vector){
        return vector.multiply(-1);
    }

    /**
     * This will get a connecting line of Locations from start to end, amplified by scale
     * @param start
     * @param end
     * @param scale
     * @return
     */
    public static List<Location> connectingLine(Location start, Location end, double scale){
        List<Location> give = new CopyOnWriteArrayList<>();
        give.add(start);


        Vector offset = offset(start.toVector(), end.toVector());
        Vector direction = direction(start.toVector(), end.toVector());

        Location temp = start.clone();
        while(offset.getX() > 0 && offset.getY() > 0 && offset.getZ() > 0){
            temp = temp.add(direction.getX() * scale, direction.getY() * scale, direction.getZ() * scale);
            give.add(temp);

            offset.subtract(direction.multiply(scale));
        }

        give.add(end);

        return give;
    }

    /**
     * Checks to see if two given locations are equal
     * @param one
     * @param two
     * @param ignoreX
     * @param ignoreY
     * @param ignoreZ
     * @return
     */
    public static boolean equals(Location one, Location two, boolean ignoreX, boolean ignoreY, boolean ignoreZ){
        return one.getWorld() == two.getWorld() && equals(one.toVector(), two.toVector(), ignoreX, ignoreY, ignoreZ);
    }

    /**
     * Checks to see if two given vectors are equal
     * @param one
     * @param two
     * @param ignoreX
     * @param ignoreY
     * @param ignoreZ
     * @return
     */
    public static boolean equals(Vector one, Vector two, boolean ignoreX, boolean ignoreY, boolean ignoreZ){
        if(!ignoreX){
            if(one.getBlockX() != two.getBlockX()){
                return false;
            }
        }

        if(!ignoreY){
            if(one.getBlockY() != two.getBlockY()){
                return false;
            }
        }

        if(!ignoreZ){
            if(one.getBlockZ() == two.getBlockZ()){
                return false;
            }
        }

        return true;
    }

}
