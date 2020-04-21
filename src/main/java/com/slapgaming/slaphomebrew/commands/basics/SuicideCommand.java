package com.slapgaming.slaphomebrew.commands.basics;

import com.slapgaming.slaphomebrew.commands.AbstractCommand;
import com.slapgaming.slaphomebrew.exceptions.CommandException;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCommand extends AbstractCommand {


    public SuicideCommand(CommandSender sender, String[] args) {
        super(sender, args);
    }

    @Override
    public boolean handle() throws CommandException {
        Player p = getPlayer();
        testPermission("suicide");

        if (p.getHealth() == 0) {
            throw new CommandException("You are already dead o.O");
        } else {
            p.sendMessage(ChatColor.GRAY + "Goodbye world D:");
            plugin.getPlayerTracker().commitsSuicide(p.getName());
            p.setHealth(0);
        }
        return true;
    }



}

