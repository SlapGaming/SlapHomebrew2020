package com.slapgaming.slaphomebrew.commands.chat;

import com.slapgaming.slaphomebrew.commands.AbstractCommand;
import com.slapgaming.slaphomebrew.controller.ChatChannels;
import com.slapgaming.slaphomebrew.exceptions.CommandException;
import org.bukkit.command.CommandSender;

public class ChatCommand extends AbstractCommand {

    private String channel;

    public ChatCommand(CommandSender sender, String channel, String[] args) {
        super(sender, args);
        this.channel = channel;
    }

    @Override
    public boolean handle() throws CommandException {
        ChatChannels channels = plugin.getChatChannels();
        channels.sendToChannel(sender, channel, args);
        return true;
    }

}
