package remielregion.remielregion;


import org.bukkit.Location;

public class Bound {

    public Bound() {

    }

    public Bound(Location min, Location max) {
        this.min = min;
        this.max = max;
    }

    public boolean isComplate() {
        return min != null & max != null;
    }

    public boolean isWithinBounds(Location location) {
        int minX = min.getBlockX(), minY = min.getBlockY(), minZ = min.getBlockZ();
        int maxX = max.getBlockX() + 1, maxY = max.getBlockY() + 1, maxZ = max.getBlockZ() + 1;
        double x = location.getX(), y = location.getY(), z = location.getZ();
        return x >= minX && x <= maxX && y >= minY && y <= maxY && z  >= minZ && z  <= maxZ;
    }

    public void sınırAtama() {
        int minX = min.getBlockX(), minY = min.getBlockY(), minZ = min.getBlockZ();
        int maxX = max.getBlockX(), maxY = max.getBlockY(), maxZ = max.getBlockZ();
        if (minX > maxX) {
            int tempX = minX;
            minX = maxX;
            maxX = tempX;
        }
        if (minY > maxY) {
            int tempY = minY;
            minY = maxY;
            maxY = tempY;
        }
        if (minZ > maxZ) {
            int tempZ = minZ;
            minZ = maxZ;
            maxZ = tempZ;
        }
        min = new Location(min.getWorld(), minX, minY, minZ);
        max = new Location(max.getWorld(), maxX, maxY, maxZ);
    }




    private Location min, max;

    public Location getMax() {
        return max;
    }

    public void setMax(Location max) {
        this.max = max;
    }

    public Location getMin() {
        return min;
    }

    public void setMin(Location min) {
        this.min = min;
    }
}
