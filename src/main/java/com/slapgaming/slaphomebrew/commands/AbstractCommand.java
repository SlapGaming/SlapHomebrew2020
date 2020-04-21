package com.slapgaming.slaphomebrew.commands;

import com.earth2me.essentials.User;
import com.slapgaming.slaphomebrew.SlapPlugin;
import com.slapgaming.slaphomebrew.exceptions.CommandException;
import com.slapgaming.slaphomebrew.exceptions.ErrorMsg;
import com.slapgaming.slaphomebrew.util.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

public abstract class AbstractCommand {

    protected CommandSender sender;
    protected String[] args;
    protected SlapPlugin plugin;

    public AbstractCommand(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
        this.plugin = SlapPlugin.getInstance();
    }

    /**
     * Execute the command.
     *
     * @return correct usage
     *
     * @throws CommandException permission or other issue
     */
    abstract public boolean handle() throws CommandException;

    /**
     * Test if the CommandSender of this command has the specified permission
     * @param perm The permission starting from slaphomebrew.[perm]
     * @throws CommandException if no permission
     */
    protected void testPermission(String perm) throws CommandException {
        if (!Util.testPermission(sender, perm)) {
            throw new CommandException(ErrorMsg.noPermission);
        }
    }


    /**
     * Cast the CommandSender to player
     * @return the player
     * @throws CommandException if the CommandSender is not a player
     */
    protected Player getPlayer() throws CommandException {
        if (!(sender instanceof Player)) {
            throw new CommandException(ErrorMsg.notAPlayer);
        }
        return (Player) sender;
    }

    /**
     * Get an online player by their (nick)name
     *
     * @param playername The player's name or nickname (case insensitive)
     * @param exact The name has to be an exact match
     * @return The found player
     * @throws CommandException if player is not online/found
     */
    protected Player getOnlinePlayer(final String playername, final boolean exact) throws CommandException {
        final String lcPlayername = playername.toLowerCase();

        Function<String, Boolean> matchMethod = exact ?
                s -> s != null && s.toLowerCase().equals(lcPlayername) :
                s -> s != null && s.toLowerCase().startsWith(lcPlayername);

        Iterable<User> onlineUsers = plugin.getEssentials().getOnlineUsers();
        Optional<User> foundUser = StreamSupport.stream(onlineUsers.spliterator(), false)
                .filter(user -> matchMethod.apply(user.getName()) || matchMethod.apply(user.getNickname()))
                .findFirst();

        if (foundUser.isPresent()) {
            return foundUser.get().getBase();
        } else {
            throw new CommandException("There is no player with the name '" + playername + "' online!");
        }
    }



    /**
     * Try to parse a string to integer. The integer must be positive.
     * @param arg The string that needs to be parsed
     * @return the int
     * @throws CommandException if the arg is not a valid int or negative/zero
     */
    protected static int parseIntPositive(String arg) throws CommandException {
        int nr = parseInt(arg);
        if (nr <= 0) throw new CommandException(arg + " is not a valid number. It needs to be positive (1+).");
        return nr;
    }

    /**
     * Try to parse a string to integer
     * @param arg The string that needs to be parsed
     * @return The int
     * @throws CommandException if the arg is not a valid int
     */
    protected static int parseInt(String arg) throws CommandException {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new CommandException(arg + " is not a valid number.");
        }
    }

    /**
     * Try to parse a String to EntityType
     * @param arg The string
     * @return The EntityType
     * @throws CommandException if not a valid EntityType
     */
    protected EntityType parseEntityType(String arg) throws CommandException {
        try {
            return EntityType.valueOf(arg); //Try to get the MobType
        } catch (IllegalArgumentException e) {
            throw new CommandException(arg + " is not a valid EntityType.");
        }
    }

    /**
     * Message the CommandSender of this command
     * @param msg The message
     */
    protected void msg(String msg) {
        sender.sendMessage(msg);
    }

    /**
     * Message the CommandSender of this command. Prepend the [SLAP] header.
     * @param msg The message
     */
    protected void hMsg(String msg) {
        Util.msg(sender, msg);
    }

}
