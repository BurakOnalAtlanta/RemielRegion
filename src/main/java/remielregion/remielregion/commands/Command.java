package remielregion.remielregion.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import remielregion.remielregion.Main;

public abstract class Command implements CommandExecutor {

    protected Main main;

    public Command(Main main, String name) {
        this.main = main;
        main.getCommand(name).setExecutor(this);
    }

    protected abstract void execute(Player player, String[] args);


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String alias, @NotNull String[] args) {

        if (sender instanceof Player) execute((Player) sender, args);
        return true;
    }
}
