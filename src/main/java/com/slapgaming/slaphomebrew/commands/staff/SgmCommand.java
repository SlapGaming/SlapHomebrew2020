package com.slapgaming.slaphomebrew.commands.staff;

import com.slapgaming.slaphomebrew.commands.AbstractCommand;
import com.slapgaming.slaphomebrew.exceptions.CommandException;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SgmCommand extends AbstractCommand {

    public SgmCommand(CommandSender sender, String[] args) {
        super(sender, args);
    }

    public boolean handle() throws CommandException {
        Player player = getPlayer(); //Get player
        testPermission("sgm"); //Test perm

        GameMode mode = player.getGameMode();
        if (mode == GameMode.SURVIVAL || mode == GameMode.ADVENTURE) {
            player.setGameMode(GameMode.CREATIVE);
        } else {
            player.setGameMode(GameMode.SURVIVAL);
        }

        return true;
    }
}
