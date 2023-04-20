package remielregion.remielregion;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;

public class Utils {

    public static Component color(String string) {
        //return ChatColor.translateAlternateColorCodes('&', string);
        return MiniMessage.miniMessage().deserialize("<green>" + string);
    }

    public static String decolor(Component component) {
        return PlainTextComponentSerializer.plainText().serialize(component);
    }

}
