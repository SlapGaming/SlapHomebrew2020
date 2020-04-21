package com.slapgaming.slaphomebrew.commands.fun;

import com.slapgaming.slaphomebrew.commands.AbstractCommand;
import com.slapgaming.slaphomebrew.exceptions.CommandException;
import com.slapgaming.slaphomebrew.util.Util;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class WaveCommand extends AbstractCommand {

    public final String CMD = "wave";

    public WaveCommand(CommandSender sender, String[] args) {
        super(sender, args);
    }

    @Override
    public boolean handle() throws CommandException {
        Player fromPlayer = getPlayer();
        testPermission("wave");

        if (args.length == 0) return false;

        String gray = ChatColor.GRAY.toString(); //Short for grays due to heavy usage

        if (args[0].equalsIgnoreCase("everyone") || args[0].equalsIgnoreCase("all")) {
            // If waving to everyone
            Util.broadcast(gray + " ** " + getName(fromPlayer) + gray + " waves to " + ChatColor.GOLD + "Everyone" + gray + " **");
        } else { // Waving to player
            ArrayList<String> toNames = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                //=> Get online player. Throw error if not found
                Player toPlayer = getOnlinePlayer(args[i], false); //Get target
                String toName = toPlayer.getName();

                //Check if not waving to self (if so, check if allowed to wave to self)
                if (fromPlayer.getName().equals(toName) && !Util.testPermission(fromPlayer, "wave.self")) {
                    throw new CommandException("You cannot wave to yourself..");
                }

                //Get the colored name
                String toColoredName = getName(toPlayer);

                //Check if the name hasn't been added already
                if (toNames.contains(toColoredName)) {
                    continue;
                }

                //Add the name
                toNames.add(toColoredName);
            }

            //Combine the names into one string
            String toPeople;
            int length = toNames.size();
            if (length == 1) {
                //=> Only one person to wave to
                toPeople = toNames.get(0);
            } else {
                //=> Multiple people to wave to
                toPeople = Util.buildString(toNames, ChatColor.GRAY + ", ", ChatColor.GRAY + " & ");
            }

            //Broadcast
            Util.broadcast(gray + " ** " + getName(fromPlayer) + gray + " waves to " + toPeople + gray + " **");
        }
        return true;
    }

    private String getName(Player p) {
        return ChatColor.WHITE + p.getDisplayName();
    }

}
