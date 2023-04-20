package remielregion.remielregion.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import remielregion.remielregion.ItemHandler;
import remielregion.remielregion.Main;

public class ItemCommand implements CommandExecutor {

    private Main main;

    private ItemHandler items;

    public ItemCommand(Main main) {
        items = main.getItems();
        this.main = main;
        main.getCommand("regionitem").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.getInventory().addItem(items.positionItem);
        }

        return false;
    }
}
