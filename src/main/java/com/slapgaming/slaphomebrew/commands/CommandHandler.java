package com.slapgaming.slaphomebrew.commands;

import com.slapgaming.slaphomebrew.commands.basics.SuicideCommand;
import com.slapgaming.slaphomebrew.commands.chat.ChatCommand;
import com.slapgaming.slaphomebrew.commands.fun.RainbowCommand;
import com.slapgaming.slaphomebrew.commands.fun.RideCommand;
import com.slapgaming.slaphomebrew.commands.fun.SpartaCommand;
import com.slapgaming.slaphomebrew.commands.fun.WaveCommand;
import com.slapgaming.slaphomebrew.commands.staff.SgmCommand;
import com.slapgaming.slaphomebrew.controller.ChatChannels;
import com.slapgaming.slaphomebrew.exceptions.CommandException;
import com.slapgaming.slaphomebrew.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandHandler {

    public boolean handle(CommandSender sender, Command cmd, String usedAlias, String[] args) {
        AbstractCommand command = this.resolveCommand(
                sender, cmd.getName().toLowerCase(), args
        );

        if (command != null) {
            try {
                boolean handled = command.handle();
                if (!handled) {
                    Util.badMsg(sender, cmd.getUsage());
                }
            } catch (CommandException e) {
                Util.badMsg(sender, e.getMessage());
            }
        }

        return true;
    }

    private AbstractCommand resolveCommand(CommandSender sender, String command, String[] args) {
        switch (command) {
            case "admin":
                return new ChatCommand(sender, "adminchat", args);
//            case "afk":
//                return new AfkCommand(sender, args);
//            case "afkinfo":
//                return new AfkInfoCommand(sender, args);
//            case "afklist":
//                return new AfkInfoCommand(sender, new String[]{"list"});
//            case "back":
//                return new BackCommand(sender, args);
//            case "backdeath":
//                return new BackdeathCommand(sender, args);
//            case "boat":
//                return new BoatCommand(sender, args);
//            case "bumpdone":
//                return new BumpdoneCommand(sender, args);
//            case "changelog":
//                return new ChangeLogCommand(sender, args);
//            case "chattoggle":
//                return new ChatToggleCommand(sender, args);
//            case "creativeextra":
//                return new CreativeextraCommand(sender, args);
//            case "deaths":
//                return new DeathsCommand(sender, args);
//            case "emptybucket":
//                return new EmptyBucketCommand(sender, args);
//            case "fancymessage":
//                return new FancyChatCommand(sender, args);
//            case "fireworkshow":
//                return new FireworkCommand(sender, args);
//            case "flyspeed":
//                return new FlySpeedCommand(sender, args);
//            case "group":
//                return new GroupCommand(sender, args);
            case "guide":
                return new ChatCommand(sender, "guidechat", args);
//            case "head":
//                return new HeadCommand(sender, args);
//            case "home":
//                return new HomeCommand(sender, args);
//            case "homemenu":
//                return new HomeMenuCommand(sender, args);
//            case "homeother":
//                return new HomeOtherCommand(sender, args);
//            case "homes":
//                return new HomesCommand(sender, args);
//            case "horse":
//                return new HorseCommand(sender, args);
//            case "improvedregion":
//                return new ImprovedRegionCommand(sender, args);
//            case "jail":
//                return new JailCommand(sender, args);
//            case "jails":
//                return new JailCommand(sender, new String[]{"list"});
//            case "kills":
//                return new KillsCommand(sender, args);
//            case "leave":
//                return new LeaveCommand(sender, args);
//            case "links":
//                return new LinksCommand(sender, args);
//            case "list":
//                return new ListCommand(sender, args);
//            case "mail":
//                return new MailCommand(sender, args);
//            case "me":
//                return new MeCommand(sender, args);
//            case "mention":
//                return new MentionCommand(sender, args);
//            case "message":
//                return new MessageCommand(sender, args);
//            case "minecart":
//                return new MinecartCommand(sender, args);
//            case "mobcheck":
//                return new MobcheckCommand(sender, args);
            case "mod":
                return new ChatCommand(sender, "modchat", args);
//            case "msg":
//                return new MsgCommand(sender, args);
//            case "mute":
//                return new MuteCommand(sender, args);
//            case "names":
//                return new NamesCommand(sender, args);
//            case "note":
//                return new NoteCommand(sender, args);
//            case "onlinetime":
//                return new OnlineTimeCommand(sender, args);
//            case "pay":
//                return new PayCommand(sender, args);
//            case "ping":
//                return new PingCommand(sender, args);
//            case "plot":
//                return new PlotCommand(sender, args);
            case "potato":
                return new ChatCommand(sender, "potatochat", args);
//            case "potion":
//                return new PotionCommand(sender, args);
//            case "profiler":
//                return new ProfilerCommand(sender, args);
//            case "promotion":
//                return new PromotionCommand(sender, args);
//            case "ragequit":
//                return new RageQuitCommand(sender, args);
            case "rainbow":
                return new RainbowCommand(sender, args);
//            case "reply":
//                return new ReplyCommand(sender, args);
            case "ride":
                return new RideCommand(sender, args);
//            case "roll":
//                return new RollCommand(sender, args);
//            case "searchregion":
//                return new SearchregionCommand(sender, args);
//            case "semiafk":
//                return new SemiAFKCommand(sender, args);
//            case "serverbroadcast":
//                return new ServerBroadcastCommand(sender, args);
//            case "sethome":
//                return new SetHomeCommand(sender, args);
            case "sgm":
                return new SgmCommand(sender, args);
//            case "skick":
//                return new SKickCommand(sender, args);
            case "slap":
                return new SlapCommand(sender, args);
            case "sparta":
                return new SpartaCommand(sender, args);
//            case "spartapads":
//                return new SpartaPadsCommand(sender, args);
//            case "spawn":
//                return new SpawnCommand(sender, args);
//            case "splugins":
//                return new PluginsCommand(sender, args);
//            case "stafflist":
//                return new StaffListCommand(sender, args);
            case "suicide":
                return new SuicideCommand(sender, args);
//            case "te":
//                return new TeCommand(sender, args);
//            case "teleportmob":
//                return new TeleportMobCommand(sender, args);
//            case "timecheck":
//                return new TimecheckCommand(sender, args);
//            case "tp":
//                return new TeleportCommand(sender, args);
//            case "tpaccept":
//                return new TeleportAcceptCommand(sender, args);
//            case "tpallow":
//                return new TpallowCommand(sender, args);
//            case "tpask":
//                return new TeleportAskCommand(sender, args);
//            case "tpaskhere":
//                return new TeleportAskHereCommand(sender, args);
//            case "tpblock":
//                return new TpBlockCommand(sender, args);
//            case "tpcancel":
//                return new TeleportCancelCommand(sender, args);
//            case "tpdeny":
//                return new TeleportDenyCommand(sender, args);
//            case "tphere":
//                return new TeleportHereCommand(sender, args);
//            case "tprequests":
//                return new TeleportRequests(sender, args);
//            case "unjail":
//                return new UnjailCommand(sender, args);
//            case "unmute":
//                return new UnmuteCommand(sender, args);
//            case "vip":
//                return new VipCommand(sender, args);
//            case "vipforum":
//                return new VipForumCommand(sender, args);
            case "wave":
                return new WaveCommand(sender, args);
//            case "whitelist":
//                return new WhitelistCommand(sender, args);
//            case "world":
//                return new WorldCommand(sender, args);
//            case "worldguards":
//                return new WorldguardsCommand(sender, args);
//            case "worthlist":
//                return new WorthListCommand(sender, args);
//            case "xray":
//                return new XRayCommand(sender, args);

            default:
                return null;
        }
    }


}
