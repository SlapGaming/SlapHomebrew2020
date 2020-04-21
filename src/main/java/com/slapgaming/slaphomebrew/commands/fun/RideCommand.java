package com.slapgaming.slaphomebrew.commands.fun;

import com.slapgaming.slaphomebrew.commands.AbstractCommand;
import com.slapgaming.slaphomebrew.exceptions.CommandException;
import com.slapgaming.slaphomebrew.exceptions.ErrorMsg;
import com.slapgaming.slaphomebrew.util.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RideCommand extends AbstractCommand {

    public RideCommand(CommandSender sender, String[] args) {
        super(sender, args);
    }

    @Override
    public boolean handle() throws CommandException {
        Player player = getPlayer();
        testPermission("ride");

        if (player.isInsideVehicle()) player.getVehicle().eject(); //Check if in a vehicle
        if (args.length != 1) return false; //Check usage

//        if (args[0].equalsIgnoreCase("click")) { //Click modus
//            PlayerControl.getPlayer(player).setRideOnRightClick(true);
//            hMsg("Right-click an entity to ride it.");
//        } else { //Playername

        Player targetPlayer = getOnlinePlayer(args[0], false);
        if (targetPlayer.getName().equals(player.getName())) throw new CommandException(ErrorMsg.breakingServer); //Trying to ride itself
        targetPlayer.addPassenger(player);

//        }

        return true;
    }

    /**
     * TabComplete on this command
     * @param sender The sender of the command
     * @param args given arguments
     * @return List of options
     */
    public static List<String> tabComplete(CommandSender sender, String[] args) {
        if (!Util.testPermission(sender, "ride") || args.length > 1) return new ArrayList<>(); //No permission

        return Util.getOnlinePlayerNames();
    }

}