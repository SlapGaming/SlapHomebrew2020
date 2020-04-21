package com.slapgaming.slaphomebrew.listeners;

import com.slapgaming.slaphomebrew.SlapPlugin;
import org.bukkit.event.Listener;

public abstract class AbstractListener implements Listener {

    protected SlapPlugin plugin;

    public AbstractListener() {
        plugin = SlapPlugin.getInstance();
    }

    /**
     * Disable any functionalties in this listener
     * This does NOT unregister it
     *
     * Standard implementation is nothing
     */
    public void disable() {}

}
