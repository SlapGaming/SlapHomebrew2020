package com.slapgaming.slaphomebrew.util;

import com.earth2me.essentials.User;
import com.slapgaming.slaphomebrew.SlapPlugin;
import com.slapgaming.slaphomebrew.exceptions.CommandException;
import org.apache.commons.lang.StringUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Util {

    /**
     * Get a list of all online player (nick)names.
     * @return list of names
     */
    public static List<String> getOnlinePlayerNames() {
        Iterable<User> onlineUsers = SlapPlugin.getInstance().getEssentials().getOnlineUsers();
        return StreamSupport.stream(onlineUsers.spliterator(), false)
                .map(user -> StringUtils.isEmpty(user.getNickname()) ? user.getName() : user.getNickname())
                .collect(Collectors.toList());
    }

    /**
     * Send a "Bad" message to the player indicating they did something wrong.
     * @param sender The sender/player
     * @param msg The message to send
     */
    public static void badMsg(CommandSender sender, String msg) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + msg);
        } else {
            sender.sendMessage(msg);
        }
    }

    public static void msg(CommandSender sender, String msg) {
        sender.sendMessage(Util.getHeader() + msg);
    }

    /**
     * Send a message to all players with the specified permission
     * @param permission The permission
     * @param message The message
     */
    public static void messagePermissionHolders(String permission, String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (testPermission(p, permission)) {
                p.sendMessage(message);
            }
        }
    }

    public static String getHeader() {
        return ChatColor.GOLD + "[SLAP] " + ChatColor.WHITE;
    }

    /**
     * Broadcast a message to all players.
     * @param message The message
     */
    public static void broadcast(String message) {
        Bukkit.broadcastMessage(message);
    }

    /**
     * Test if a CommandSender has a certain permission. Prepend SlapPlugin.[perm]
     *
     * @param sender The sender
     * @param perm The permission without SlapPlugin. prefix
     *
     * @return has permission
     */
    public static boolean testPermission(CommandSender sender, String perm) {
        return !(sender instanceof Player) || sender.hasPermission("SlapPlugin." + perm);
    }

    /**
     * Build a string from a string array.
     * @param split The String array
     * @param splitChar The string that should be added between each String from the array
     * @param begin The index it should start on
     * @return The combined string
     */
    public static String buildString(String[] split, String splitChar, int begin) {
        return buildString(split, splitChar, begin, Integer.MAX_VALUE);
    }

    /**
     * Build a string from a string array.
     * @param split The String array
     * @param splitChar The string that will be added between each String from the array
     * @param begin The index it should start on
     * @param end The index it should end with
     * @return The combined string
     */
    public static String buildString(String[] split, String splitChar, int begin, int end) {
        StringBuilder combined = new StringBuilder();
        while (begin <= end && begin < split.length) {
            if (combined.length() > 0) {
                combined.append(splitChar);
            }
            combined.append(split[begin]);
            begin++;
        }
        return combined.toString();
    }

    /**
     * Build a string from a string collection
     * @param strings The collection of strings
     * @param splitChar The string that will be added between each string from the list
     * @return The combined string
     */
    public static String buildString(Collection<String> strings, String splitChar) {
        StringBuilder combined = new StringBuilder();
        for (String s : strings) {
            if (combined.length() > 0) {
                combined.append(splitChar);
            }
            combined.append(s);
        }
        return combined.toString();
    }

    /**
     * Build a string from a List of strings
     * This one can be used if there last splitChar should be different.
     *
     * Example: buildString(list, ", ", " and ");
     * Will return: "One, Two, Three and Four"
     *
     * @param strings The list of strings
     * @param splitChar The string that splits the strings
     * @param finalSplitChar The string that splits the last strings
     * @return The combined string
     */
    public static String buildString(List<String> strings, String splitChar, String finalSplitChar) {
        StringBuilder combined = new StringBuilder();
        //Length of the list of strings
        int nrOfStrings = strings.size();
        //Loop through strings
        for (int i = 0; i < nrOfStrings; i++) {
            if (i == (nrOfStrings - 1) && i != 0) { //Last one, add the finalSplitChar
                combined.append(finalSplitChar);
            } else if (i > 0) { //If not the first nor the last, add the splitChar
                combined.append(splitChar);
            }
            combined.append(strings.get(i)); //Add the String
        }
        return combined.toString();
    }

    public static String colorize(String s){
        if(s == null) return null;
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String decolorize(String s){
        if(s == null) return null;
        return s.replaceAll("&([0-9a-f])", "");
    }


    /**
     * Get all the online players
     * @return player array
     */
    public static Collection<? extends Player> getOnlinePlayers() {
        return Bukkit.getServer().getOnlinePlayers();
    }

    /**
     * Safely teleport to another player
     * @param toBeTeleported The player who is going to teleport
     * @param toTeleport The locaction to be teleported to
     * @param isFlying if the location is in the (use true if in doubt).
     * @param registerBackLocation will register the location the player is leaving as /back location
     * @param ignoreCooldown Will ignore the cooldown
     * @return has teleported under the target player
     * @throws CommandException if teleport into lava or if target is above the void
     */
    public static boolean safeTeleport(Player toBeTeleported, Location toTeleport, boolean isFlying, boolean registerBackLocation, boolean... ignoreCooldown) throws CommandException {
        //Get SlapPlayer
//        SlapPlayer sp = PlayerControl.getPlayer(toBeTeleported);
//
//        if ((ignoreCooldown.length > 0 && !ignoreCooldown[0]) || ignoreCooldown.length == 0) {
//            if (!sp.getTeleporter().canTeleport()) { //Check if able to teleport if cooldown
//                if (!Util.testPermission(toBeTeleported, "tp.cooldownoverride")) {
//                    throw new CommandException("You'll need to wait a bit before teleporting agian!");
//                }
//            }
//        }


        Location teleportTo = null;
        boolean tpUnder = false;

        Location fromLocation = toBeTeleported.getLocation();

        if (isFlying && !toBeTeleported.isFlying()) { //Target is flying while the player is not flying -> Find first block under target
            tpUnder = true;
            boolean creative = (toBeTeleported.getGameMode() == GameMode.CREATIVE); //Check if in creative
            for (Location loc = toTeleport; loc.getBlockY() > 0; loc.add(0, -1, 0)) { //Loop thru all blocks under target's location
                Material m = loc.getBlock().getType();
                if (m == Material.AIR) continue; //Looking for first solid
                if (m == Material.LAVA && !creative) { //If teleporting into lava && not in creative
                    throw new CommandException("You would be teleported into Lava!");
                }
                teleportTo = loc.add(0, 1, 0); //Set loc + 1 block above
                break;
            }
        } else { //Not flying
            teleportTo = toTeleport;
        }

        if (teleportTo == null) { //Check if location found
            throw new CommandException("Cannot teleport! Player above void!");
        }

        toBeTeleported.teleport(teleportTo); //Teleport
        toBeTeleported.setVelocity(new Vector(0, 0, 0)); //Reset velocity

//        if (registerBackLocation) { //If registering back location
//            sp.getTeleporter().setBackLocation(fromLocation); //Set back location
//        }

        //Register teleport
//        sp.getTeleporter().teleported();

        return tpUnder;
    }


    public static BukkitTask runASync(Runnable runnable) {
        SlapPlugin plugin = SlapPlugin.getInstance();
        if (!plugin.isEnabled()) {
            runnable.run();
            return null;
        }
        return getScheduler(plugin).runTaskAsynchronously(plugin, runnable);
    }

    public static BukkitTask runASyncLater(Runnable runnable, int delay) {
        SlapPlugin plugin = SlapPlugin.getInstance();
        if (!plugin.isEnabled()) {
            runnable.run();
            return null;
        }
        return getScheduler(plugin).runTaskLaterAsynchronously(plugin, runnable, delay);
    }

    public static BukkitTask runASyncTimer(Runnable runnable, int delay, int period) {
        SlapPlugin plugin = SlapPlugin.getInstance();
        if (!plugin.isEnabled()) {
            runnable.run();
            return null;
        }
        return getScheduler(plugin).runTaskTimerAsynchronously(plugin, runnable, delay, period);
    }

    public static BukkitTask run(Runnable runnable) {
        SlapPlugin plugin = SlapPlugin.getInstance();
        if (!plugin.isEnabled()) {
            runnable.run();
            return null;
        }
        return getScheduler(plugin).runTask(plugin, runnable);
    }

    public static BukkitTask runLater(Runnable runnable, int delay) {
        SlapPlugin plugin = SlapPlugin.getInstance();
        if (!plugin.isEnabled()) {
            runnable.run();
            return null;
        }
        return getScheduler(plugin).runTaskLater(plugin, runnable, delay);
    }

    public static BukkitTask runTimer(Runnable runnable, int delay, int period) {
        SlapPlugin plugin = SlapPlugin.getInstance();
        if (!plugin.isEnabled()) {
            runnable.run();
            return null;
        }
        return getScheduler(plugin).runTaskTimer(plugin, runnable, delay, period);
    }

    public static BukkitScheduler getScheduler(SlapPlugin plugin) {
        return plugin.getServer().getScheduler();
    }

    public static BukkitScheduler getScheduler() {
        return SlapPlugin.getInstance().getServer().getScheduler();
    }

    public static Logger getLogger() {
        return SlapPlugin.getInstance().getLogger();
    }

    /**
     * Get the target block that is NOT air in the line of sight of a player
     * @param entity The entity
     * @param maxDistance Max distance to block
     * @return The block or null
     * @throws CommandException if no block found
     */
    public static Block getTargetBlock(LivingEntity entity, int maxDistance) throws CommandException {
        BlockIterator iterator = new BlockIterator(entity, maxDistance);
        while (iterator.hasNext()) {
            Block foundBlock = iterator.next();
            if (foundBlock.getType() != Material.AIR) {
                return foundBlock;
            }
        }
        throw new CommandException("You aren't looking at a block (or out of range)");
    }

    /**
     * Get all blocks in the line of sight of an entity
     * @param entity The entity
     * @param maxDistance The max distance line
     * @return The list with all the blocks
     */
    public static ArrayList<Block> getBlocksInLineOfSight(LivingEntity entity, int maxDistance) {
        BlockIterator iterator = new BlockIterator(entity, maxDistance);
        ArrayList<Block> blocks = new ArrayList<>();
        while (iterator.hasNext()) {
            blocks.add(iterator.next());
        }
        return blocks;
    }

}
