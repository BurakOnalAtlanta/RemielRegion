package remielregion.remielregion.manager;

import net.kyori.adventure.text.Component;
import remielregion.remielregion.Bound;
import remielregion.remielregion.Region;

import java.util.HashMap;
import java.util.Map;

import static remielregion.remielregion.Utils.decolor;

public class RegionManager {

    private Map<String, Region> regions = new HashMap<>();

    public void createNewRegion(Component name, Component description, boolean isSafezone, Bound bound) {
        Region region = new Region(name, description, bound, isSafezone);
        regions.put(decolor(name), region);
    }

    public Map<String, Region> getRegions() {
        return regions;
    }


}
