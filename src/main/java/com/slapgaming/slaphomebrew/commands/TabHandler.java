package com.slapgaming.slaphomebrew.commands;

import com.slapgaming.slaphomebrew.commands.fun.RainbowCommand;
import com.slapgaming.slaphomebrew.commands.fun.RideCommand;
import com.slapgaming.slaphomebrew.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TabHandler {

    /**
     *
     * @param sender
     * @param command
     * @param alias
     * @param args
     * @return List of options
     */
    public List<String> handle(CommandSender sender, Command command, String alias, String[] args) {
        switch(command.getName().toLowerCase()) {
            case "admin": /* No futher usage */
                break;
//            case "afk":                /* No futher usage */
//                break;
//            case "afkinfo":
//                l = AfkInfoCommand.tabComplete(sender, args);
//                break;
//            case "afklist":            /* No futher usage */
//                break;
//            case "back":            /* No futher usage */
//                break;
//            case "backdeath":        /* No futher usage */
//                break;
//            case "boat":            /* No futher usage */
//                break;
//            case "bumpdone":        /* No futher usage */
//                break;
//            case "changelog":
//                l = ChangeLogCommand.tabComplete(sender, args);
//                break;
//            case "chattoggle":
//                l = ChatToggleCommand.tabComplete(sender, args);
//                break;
//            case "creativeextra":    /* No futher usage */
//                break;
//            case "deaths":            /* No futher usage */
//                break;
//            case "fancymessage":    /* Usage here */
//                break;
//            case "fireworkshow":
//                l = FireworkCommand.tabComplete(sender, args);
//                break;
//            case "flyspeed":
//                l = FlySpeedCommand.tabComplete(sender, args);
//                break;
//            case "group":            /* No futher usage */
//                break;
            case "guide":            /* No futher usage */
                break;
//            case "head":            /* No futher usage */
//                break;
//            case "home":
//                l = HomeCommand.tabComplete(sender, args);
//                break;
//            case "homemenu":        /* No futher usage */
//                break;
//            case "homeother":
//                l = HomeOtherCommand.tabComplete(sender, args);
//                break;
//            case "homes":            /* No futher usage */
//                break;
//            case "horse":            /* Usage here */
//                break;
//            case "improvedregion":
//                l = ImprovedRegionCommand.tabComplete(sender, args);
//                break;
//            case "jail":
//                l = JailCommand.tabComplete(sender, args);
//                break;
//            case "jails":            /* No futher usage */
//                break;
//            case "kills":            /* No futher usage */
//                break;
//            case "leave":            /* No futher usage */
//                break;
//            case "links":            /* No futher usage */
//                break;
//            case "list":            /* No futher usage */
//                break;
//            case "mail":
//                l = MailCommand.tabComplete(sender, args);
//                break;
//            case "me":                /* No futher usage */
//                break;
//            case "mention":
//                l = MentionCommand.tabComplete(sender, args);
//                break;
//            case "message":
//                l = MessageCommand.tabComplete(sender, args);
//                break;
//            case "minecart":        /* No futher usage */
//                break;
//            case "mobcheck":        /* Going to redo the command first */
//                break;
            case "mod":                /* No futher usage */
                break;
//            case "msg":                /* No futher usage */
//                break;
//            case "mute":            /* TODO */
//                break;
//            case "note":            /* Usage here */
//                break;
//            case "onlinetime":
//                l = OnlineTimeCommand.tabComplete(sender, args);
//                break;
//            case "pay":                /* No futher usage */
//                break;
//            case "ping":            /* No futher usage */
//                break;
//            case "plot":
//                l = PlotCommand.tabComplete(sender, args);
//                break;
            case "potato":            /* No futher usage */
                break;
//            case "potion":            /* Usage here */
//                break;
//            case "profiler":        /* TODO */
//                break;
//            case "promotion":
//                l = PromotionCommand.tabComplete(sender, args);
//                break;
//            case "ragequit":        /* No futher usage */
//                break;
            case "rainbow":
                return RainbowCommand.tabComplete(sender, args);
//            case "reply":            /* No futher usage */
//                break;
            case "ride":
                return RideCommand.tabComplete(sender, args);
//            case "roll":            /* No futher usage */
//                break;
//            case "searchregion":
//                l = SearchregionCommand.tabComplete(sender, args);
//                break;
//            case "semiafk":         /* No futher usage */
//                break;
//            case "sethome":            /* No futher usage */
//                break;
//            case "sgm":                /* No futher usage */
//                break;
//            case "skick":            /* No futher usage */
//                break;
            case "slap":            /* Usage here */
                break;
            case "sparta":
                return Util.getOnlinePlayerNames();
//            case "spartapads":        /* TODO */
//                break;
//            case "spawn":
//                l = SpawnCommand.tabComplete(sender, args);
//                break;
//            case "splugins":        /* No futher usage */
//                break;
//            case "stafflist":
//                l = StaffListCommand.tabComplete(sender, args);
//                break;
//            case "suicide":            /* No futher usage */
//                break;
//            case "te":                /* No futher usage */
//                break;
//            case "teleportmob":        /* Usage here */
//                break;
//            case "timecheck":
//                l = TimecheckCommand.tabComplete(sender, args);
//                break;
//            case "tp":                /* No futher usage */
//                break;
//            case "tpaccept":
//                l = TeleportAcceptCommand.tabComplete(sender, args);
//                break;
//            case "tpallow":            /* No futher usage */
//                break;
//            case "tpask":            /* No futher usage */
//                break;
//            case "tpaskhere":        /* No futher usage */
//                break;
//            case "tpblock":            /* No futher usage */
//                break;
//            case "tpcancel":        /* No futher usage */
//                break;
//            case "tpdeny":
//                l = TeleportAcceptCommand.tabComplete(sender, args);
//                break; //Exact same Tab completion as Accept command
//            case "tphere":            /* No futher usage */
//                break;
//            case "tprequests":        /* No futher usage */
//                break;
//            case "unjail":            /* No futher usage */
//                break;
//            case "unmute":          /* TODO */
//                break;
//            case "vip":
//                l = VipCommand.tabComplete(sender, args);
//                break;
//            case "vipforum":
//                l = VipForumCommand.tabComplete(sender, args);
//                break;
            case "wave":
                return Util.getOnlinePlayerNames();
//            case "whitelist":
//                l = WhitelistCommand.tabComplete(sender, args);
//                break;
//            case "world":            /* No futher usage */
//                break;
//            case "worldguards":        /* No futher usage */
//                break;
//            case "worthlist":        /* Usage here */
//                break;
//            case "xray":
//                l = XRayCommand.tabComplete(sender, args);
//                break;
        }

        return null;
    }






}
