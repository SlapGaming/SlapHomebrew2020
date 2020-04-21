package com.slapgaming.slaphomebrew.listeners.player;

import com.slapgaming.slaphomebrew.controller.PlayerTracker;
import com.slapgaming.slaphomebrew.listeners.AbstractListener;
import com.slapgaming.slaphomebrew.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandListener extends AbstractListener {

//    private AwayFromKeyboard afk;
//    private Jails jails;
    private PlayerTracker playerTracker;
//    private MuteController muteController;

    public PlayerCommandListener(PlayerTracker playerTracker){
//        this.afk = afk;
//        this.jails = jails;
        this.playerTracker = playerTracker;
//        this.muteController = muteController;
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
//        SlapPlayer slapPlayer = PlayerControl.getPlayer(player);

//        //Block commands if not moved yet
//        if (!slapPlayer.hasMoved()) {
//            event.setCancelled(true);
//            slapPlayer.sendNotMovedMessage();
//            return;
//        }

        String message = event.getMessage().toLowerCase().trim();
        String[] commandMessage = message.split(" ");
        String playerName = player.getName();

        //Block ryuuga from saying :S
        if (playerName.equals("ryuuga")) {
            if (message.contains(":s")) {
                player.kickPlayer(":S");
                event.setCancelled(true);
                return;
            }
        }


        switch (commandMessage[0]) {//Morph commands
//            case "/w": case "/whisper": case "/tell": //Morph all chat commands -> /message
//                player.chat(event.getMessage().replaceFirst("(?i)"+ commandMessage[0], "/m"));
//                event.setCancelled(true);
//                return;

//            case "/plugins": case "/pl": //Plugins -> /sPlugins
//                if (!Util.testPermission(player, "spluginsoverride")) {
//                    player.chat("/splugins");
//                    event.setCancelled(true);
//                    return;
//                }
//                break;

//            case "/leave": //Leave -> /gleave
//                if (player.getWorld().getName().equals("world_sonic")) {
//                    event.setCancelled(true);
//                    player.chat("/gleave");
//                    return;
//                }
//                break;

//            case "/?": //? -> Help
//                if (!Util.testPermission(player, "spluginsoverride")) {
//                    player.chat(event.getMessage().replaceFirst("(?i)" + commandMessage[0], "/help"));
//                    event.setCancelled(true);
//                    return;
//                }
//                break;

//            case "/region": case "/rg": //Region -> ImprovedRegion
//                if (!slapPlayer.hasToggledRegion()) { //If not toggled region
//                    player.chat(event.getMessage().replaceFirst("(?i)" + commandMessage[0], "/irg")); //Use /irg
//                    event.setCancelled(true);
//                    return;
//                }
//                break;

//            case "/ac": case "/helpop": //Whine at them for not using /g or /gc
//                if (Util.testPermission(player, "guidechat")) {
//                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&l&nSTOP USING &b&l&n/AC &f&l&n| &c&l&nALSO COLORS &f&l&n| &e&l&nUSE /G"));
//                }
//                break;

            case "/guidechat": //Guidechat -> /gc
                if (Util.testPermission(player, "guidechat")) {
                    event.setMessage(event.getMessage().replaceFirst("(?i)" + commandMessage[0], "/gc"));
                }
                break;

            case "/modchat": //Modchat -> /mc
                if (Util.testPermission(player, "modchat")) {
                    event.setMessage(event.getMessage().replaceFirst("(?i)" + commandMessage[0], "/mc"));
                }
                break;

            case "/modlist": //Modlist -> Stafflist
                player.chat(event.getMessage().replaceFirst("(?i)" + commandMessage[0], "/stafflist"));
                event.setCancelled(true);
                return;
        }

        //Set last activity
//        slapPlayer.active();

        //Cancel commands in Jail
//        String UUID = player.getUniqueId().toString();
//        if (jails.isJailed(UUID)) {
//            if (commandMessage[0].equalsIgnoreCase("/timeleft")) {
//                jails.sendPlayerJailInfo(player, UUID);
//                event.setCancelled(true);
//            } else if (commandMessage[0].equalsIgnoreCase("/unjail")) {
//                if (!player.hasPermission("slaphomebrew.jail")) {
//                    event.setCancelled(true);
//                    player.sendMessage(ChatColor.GRAY + "You are jailed. Use /timeleft to check your time left in jail.");
//                }
//            } else if (!commandMessage[0].equalsIgnoreCase("/modreq") && !commandMessage[0].equalsIgnoreCase("/ping")) {
//                if (commandMessage[0].equalsIgnoreCase("/msg") || commandMessage[0].equalsIgnoreCase("/m") || commandMessage[0].equalsIgnoreCase("/message") || commandMessage[0].equalsIgnoreCase("/r") || commandMessage[0].equalsIgnoreCase("/reply")) {
//                    if (!jails.isAllowedToMsg(UUID)) {
//                        event.setCancelled(true);
//                        player.sendMessage(ChatColor.GRAY + "You are jailed. Use /timeleft to check your time left in jail.");
//                    }
//                } else {
//                    event.setCancelled(true);
//                    player.sendMessage(ChatColor.GRAY + "You are jailed. Use /timeleft to check your time left in jail.");
//                }
//            }
//        }

        //Cancel certain commands if muted
//        if (muteController.isMuted(player.getUniqueId().toString())) {
//            switch(commandMessage[0].substring(1)) {
//                case "msg":case "m":case "tell":case "r":case "reply":
//                case "me":case "action":
//                case "wave":case "waves":
//                case "afk":
//                case "mail":
//                    event.setCancelled(true);
//                    player.sendMessage(ChatColor.GRAY + "You are muted. Use /muted for more info.");
//                    return;
//            }
//
//        }

        //Leave AFK on certain Commands
//        String[] command = event.getMessage().toLowerCase().split(" ");
//        if (afk.isAfk(player)) {
//            switch(command[0]) {
//                case "/me": case "/pay": case "/modreq": case "/r": case "/msg":
//                    afk.leaveAfk(player);
//                    break;
//            }
//        }
//
//        if (commandMessage.length > 1) {
//            switch (commandMessage[0]) {
//                //AFK response
//                case "/tpa": case "/tpahere":
//                    Player tempPlayer = Bukkit.getPlayer(commandMessage[1]);
//                    if (tempPlayer != null) {
//                        if (afk.isAfk(tempPlayer)){
//                            afk.sendAfkReason(player, tempPlayer);
//                        }
//                    }
//                    break;
//            }
//        }
//
//        if (commandMessage.length > 2) {
//            if (commandMessage[0].equals("/msg")) { //AFK response
//                Player tempPlayer = Bukkit.getPlayer(commandMessage[1]);
//                if (tempPlayer != null) {
//                    if (afk.isAfk(tempPlayer)){
//                        afk.sendAfkReason(player, tempPlayer);
//                    }
//                }
//            }
//        }

        //Send commandspy message
        if (!event.isCancelled()) {
            switch (commandMessage[0].substring(1).toLowerCase()) {
                case "roll": case "afk": case "suicide": case "me": case "j": case "jumpto": //Standard ignored
                case "g": case "gc": case "helpop": case "guide": case "mod": case "mc": case "a": case "amsg": case "ac": case "admin": //Guide, Mod & Admin Chat
                case "potato": case "x": case "pc": //PotatoChat
                    //Ignore these commands
                    break;
                case "mail":
                    playerTracker.sendToCommandSpies(playerName, event.getMessage(), true);
                    break;
                default:
                    playerTracker.sendToCommandSpies(playerName, event.getMessage(), false);
            }
        }
    }

}
