package remielregion.remielregion;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import remielregion.remielregion.commands.ItemCommand;
import remielregion.remielregion.commands.RegionCommand;
import remielregion.remielregion.listener.RegionListener;
import remielregion.remielregion.manager.RegionManager;

public final class Main extends JavaPlugin {

    private RegionManager regionManager = new RegionManager();
    private RegionCommand regionCommand;
    public ItemHandler getItems() {
        return items;
    }
    private ItemHandler items;
    public RegionCommand getRegionCommand() {
        return regionCommand;
    }

    public RegionManager getRegionManager() {
        return regionManager;
    }

    @Override
    public void onEnable() {
        items = new ItemHandler(this);
        items.init();
        regionCommand = new RegionCommand(this);
        new RegionListener(this);
        new ItemCommand(this);
        send("Region Çalışıyor");
    }

    @Override
    public void onDisable() {
        send("Region Çalışmıyor");
    }
    public void send(String string) {
        getServer().getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize("<dark_green>" + string));
    }
}
