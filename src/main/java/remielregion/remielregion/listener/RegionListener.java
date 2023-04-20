package remielregion.remielregion.listener;

import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import remielregion.remielregion.Bound;
import remielregion.remielregion.ItemHandler;
import remielregion.remielregion.Main;
import remielregion.remielregion.Region;
import remielregion.remielregion.manager.RegionManager;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class RegionListener implements Listener {

    private Map<UUID, Bound> regionSetup;
    private Map<String, Region> regions;
    private Map<UUID, Region> playerRegion = new HashMap<>();
    private ItemHandler items;

    public RegionListener(Main main) {
        items = main.getItems();
        regions = main.getRegionManager().getRegions();
        regionSetup = main.getRegionCommand().getRegionSetup();
        main.getServer().getPluginManager().registerEvents(this, main);
        new BukkitRunnable() {

            @Override
            public void run() {
                for (UUID uuid : regionSetup.keySet()) {
                    Player player = Bukkit.getPlayer(uuid);
                    if (player == null) continue;
                    Bound bound = regionSetup.get(uuid);
                    if (!(bound.isComplate())) continue;
                    Bound newBound = new Bound(bound.getMin(), bound.getMax());
                    newBound.sınırAtama();
                    Location min = newBound.getMin(), max = newBound.getMax();
                    for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
                        for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
                            for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++ ) {
                                if (x == min.getBlockX() || x == max.getBlockX() + 1 || y == min.getBlockY() || y == max.getBlockY() + 1 || z == min.getBlockZ() || z == max.getBlockZ() + 1) player.spawnParticle(Particle.VILLAGER_HAPPY, x, y, z, 1); {

                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(main, 0L, 5L);
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(regionSetup.containsKey(player.getUniqueId()))) return;
        Bound bound = regionSetup.get(player.getUniqueId());
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().isSimilar(items.positionItem)) {
                bound.setMin(block.getLocation());
                player.sendMessage(MiniMessage.miniMessage().deserialize("<dark_green>1. pozisyon başarıyla seçildi<gray> " + player.getName()));
            }
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().isSimilar(items.positionItem)) {
                bound.setMax(block.getLocation());
                player.sendMessage(MiniMessage.miniMessage().deserialize("<green>2. pozisyon başarıyla seçildi<gray> " + player.getName()));
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Region current = playerRegion.get(player.getUniqueId()), active = null;
        for (Region region : regions.values()) {
            if (!(region.getBound().isWithinBounds(player.getLocation()))) continue;
            active = region;
            if (current == region) continue;
            player.showTitle(Title.title(region.getName().color(TextColor.color(102, 255, 178)), MiniMessage.miniMessage().deserialize("<gray>" + player.getName())));
            player.playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1f, 1);
            player.sendMessage(MiniMessage.miniMessage().deserialize("<green>Bölge İsmi: ").append(region.getName().color(TextColor.color(255, 128, 0))));
            player.sendMessage(MiniMessage.miniMessage().deserialize("<gold>Bölge Bilgileri: ").append(region.getDescription().color(TextColor.color(255, 255, 102))));
        }
        playerRegion.put(player.getUniqueId(), active);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;
        Region region = playerRegion.get(player.getUniqueId());
        if (region != null && region.isSafezone()) event.setCancelled(true);
    }

}
