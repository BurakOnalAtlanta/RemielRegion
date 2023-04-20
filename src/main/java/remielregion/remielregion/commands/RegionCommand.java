package remielregion.remielregion.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import remielregion.remielregion.Bound;
import remielregion.remielregion.Main;
import remielregion.remielregion.manager.RegionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static remielregion.remielregion.Utils.decolor;

public class RegionCommand extends Command{

    private Map<UUID, Bound> regionSetup = new HashMap<>();

    private RegionManager regionManager;

    public RegionCommand(Main main) {
        super(main, "setregion");
        regionManager = main.getRegionManager();
    }

    @Override
    protected void execute(Player player, String[] args) {
        if(args.length == 0) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<gold>Geçersiz komut kullanımı " + "<yellow>" + player.getName()));
            return;
        }
        switch (args[0].toLowerCase()) {
            case "create":
                if(!(regionSetup.containsKey(player.getUniqueId()))) {
                    regionSetup.put(player.getUniqueId(), new Bound());
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<aqua>1. pozisyonu ayarlamak için sol tık yapınız ve ardından 2. pozisyonu ayarlamak için sağ tık yapınız. <gray>/setregion create <bölge ismi> <true/false = pvp ayarı> <açıklama (opsiyonel)>"));
                } else if (args.length == 1) {
                    regionSetup.remove(player.getUniqueId());
                    player.sendMessage(MiniMessage.miniMessage().deserialize("İptal edilen bölge oluşturma"));
                } else if (args.length < 3) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Geçersiz komut kullanımı. Geçerli kullanım; <gray>/setregion create <bölge ismi> <true/false = pvp ayarı> <açıklama (opsiyonel)>"));
                } else {
                    Bound bound = regionSetup.get(player.getUniqueId());
                    if(!(bound.isComplate())) {
                        player.sendMessage(MiniMessage.miniMessage().deserialize("<light_purple>Lütfen bölgenin sınırlarının karşılıklı iki köşesini seçiniz"));
                        return;
                    }
                    String name = args[1], safeInput= args[2].toLowerCase();
                    boolean safezone;
                    if (safeInput.equalsIgnoreCase("true")) {
                        safezone = true;
                    } else if (safeInput.equalsIgnoreCase("false")) {
                        safezone = false;
                    } else {
                        player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Lütfen Geçerli Kullanımı giriniz. true/false"));
                        return;
                    }
                    StringBuilder builder = new StringBuilder();
                    for (int i = 4; i <= args.length; i++) {
                        builder.append(args[i - 1]).append(" ");
                    }
                    String aciklama = builder.toString().trim();
                    bound.sınırAtama();
                    if (regionManager.getRegions().containsKey(decolor(MiniMessage.miniMessage().deserialize(name)).toLowerCase())) {
                        player.sendMessage(MiniMessage.miniMessage().deserialize("<red><bold>" + name + "<red>İsimli bölge zaten var"));
                        return;
                    }
                    regionManager.createNewRegion(MiniMessage.miniMessage().deserialize(name), MiniMessage.miniMessage().deserialize(aciklama), safezone, bound);
                    regionSetup.remove(player.getUniqueId());
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<red> As"));
                }

                break;
            default:
                player.sendMessage(MiniMessage.miniMessage().deserialize("<red>Bilinmeyen komut kullanımı " + args[0] + "!"));
                break;
        }
    }

    public Map<UUID, Bound> getRegionSetup() {
        return regionSetup;
    }
}
