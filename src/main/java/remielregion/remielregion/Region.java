package remielregion.remielregion;

import net.kyori.adventure.text.Component;

import static remielregion.remielregion.Utils.decolor;

public class Region {

    private Component name, description;
    private String rawName;
    private Bound bound;
    private boolean isSafezone;

    public Region(Component name, Component description, Bound bound, boolean isSafezone) {
        this.name = name;
        this.description = description;
        rawName = decolor(name);
        this.bound = bound;
        this.isSafezone = isSafezone;
    }

    public Component getName() {
        return name;
    }

    public void setName(Component name) {
        this.name = name;
    }

    public Component getDescription() {
        return description;
    }

    public void setDescription(Component description) {
        this.description = description;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }

    public boolean isSafezone() {
        return isSafezone;
    }

    public void setSafezone(boolean safezone) {
        isSafezone = safezone;
    }
}
