package remielregion.remielregion;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {

    public ItemStack positionItem;

    private Main main;

    public ItemHandler(Main main) {
        this.main = main;
    }

    public static ItemStack createCustomItem(Material material, String name, List<Component> lore, String renk) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (name != null) {
            itemMeta.displayName(MiniMessage.miniMessage().deserialize("<"+renk+">"  + name));
        }


        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public void setPositionİtem() {
        List<Component> lore = new ArrayList<>();
        lore.add(MiniMessage.miniMessage().deserialize("<aqua>Remiel Bölge Belirleyici"));
        ItemStack bölgeItem = createCustomItem(Material.NETHERITE_AXE, "Bölge Belirleyici", lore, "yellow");
        this.positionItem = bölgeItem;
    }

    public void init() {
        this.setPositionİtem();
    }

}
